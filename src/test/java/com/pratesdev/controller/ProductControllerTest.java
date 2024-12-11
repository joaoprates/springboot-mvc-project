package com.pratesdev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratesdev.dto.ProductCreateRequest;
import com.pratesdev.dto.ProductResponse;
import com.pratesdev.dto.ProductUpdateRequest;
import com.pratesdev.model.Product;
import com.pratesdev.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() throws Exception {
        List<Product> mockProducts = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product2");

        mockProducts.add(product1);
        mockProducts.add(product2);

        when(productService.getAllProducts()).thenReturn(mockProducts);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Product1"))
                .andExpect(jsonPath("$[1].name").value("Product2"));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getProductById_ShouldReturnProduct() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Product1");

        when(productService.getProductById(1L)).thenReturn(mockProduct);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product1"));

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void createProduct_ShouldReturnCreatedProduct() throws Exception {
        ProductCreateRequest createRequest = new ProductCreateRequest();
        createRequest.setName("New Product");

        Product createdProduct = new Product();
        createdProduct.setId(1L);
        createdProduct.setName("New Product");

        when(productService.createProduct(any())).thenReturn(createdProduct);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Product"));

        verify(productService, times(1)).createProduct(any());
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProduct() throws Exception {
        ProductUpdateRequest updateRequest = new ProductUpdateRequest();
        updateRequest.setName("Updated Product");
        updateRequest.setDescription("Updated Description");
        updateRequest.setPrice(150.0);
        updateRequest.setAvailable(true);

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");

        when(productService.getProductById(1L)).thenReturn(updatedProduct);
        when(productService.updateProduct(any())).thenReturn(updatedProduct);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"));

        verify(productService, times(1)).getProductById(1L);
        verify(productService, times(1)).updateProduct(any());
    }

    @Test
    void deleteProduct_ShouldReturnNoContent() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(1L);
    }
}
