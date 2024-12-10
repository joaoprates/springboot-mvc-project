package com.pratesdev.service;

import com.pratesdev.dto.ProductCreateRequest;
import com.pratesdev.exception.ResourceNotFoundException;
import com.pratesdev.model.Category;
import com.pratesdev.model.Product;
import com.pratesdev.repository.CategoryRepository;
import com.pratesdev.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_ShouldReturnEmptyList() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertTrue(products.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById_ShouldReturnProduct() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Test Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        Product product = productService.getProductById(1L);
        assertNotNull(product);
        assertEquals("Test Product", product.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void getProductById_ShouldThrowExceptionWhenNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void addProduct_ShouldSaveProduct() {
        Product mockProduct = new Product();
        mockProduct.setName("New Product");

        when(productRepository.save(mockProduct)).thenReturn(mockProduct);

        Product savedProduct = productService.addProduct(mockProduct);
        assertNotNull(savedProduct);
        assertEquals("New Product", savedProduct.getName());
        verify(productRepository, times(1)).save(mockProduct);
    }

    @Test
    void createProduct_ShouldSaveProduct() {
        ProductCreateRequest request = new ProductCreateRequest();
        request.setName("Test Product");
        request.setDescription("Description");
        request.setPrice(100.0);
        request.setAvailable(true);
        request.setCategory("TestCategory");

        Category mockCategory = new Category();
        mockCategory.setName("TestCategory");

        when(categoryRepository.findByName("TestCategory")).thenReturn(Optional.of(mockCategory));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product createdProduct = productService.createProduct(request);
        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
        verify(categoryRepository, times(1)).findByName("TestCategory");
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void createProduct_ShouldThrowExceptionWhenCategoryNotFound() {
        ProductCreateRequest request = new ProductCreateRequest();
        request.setCategory("InvalidCategory");

        when(categoryRepository.findByName("InvalidCategory")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.createProduct(request));
        verify(categoryRepository, times(1)).findByName("InvalidCategory");
    }

    @Test
    void deleteProduct_ShouldDeleteProduct() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        doNothing().when(productRepository).delete(mockProduct);

        productService.deleteProduct(1L);
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(mockProduct);
    }

    @Test
    void deleteProduct_ShouldThrowExceptionWhenNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(1L));
        verify(productRepository, times(1)).findById(1L);
    }
}
