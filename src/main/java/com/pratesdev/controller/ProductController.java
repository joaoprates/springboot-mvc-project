package com.pratesdev.controller;

import com.pratesdev.dto.ProductResponse;
import com.pratesdev.model.Category;
import com.pratesdev.model.Product;
import com.pratesdev.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. List all products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        // Testando diretamente no mapeamento
        for (Product product : products) {
            // Teste do trecho
            Category category = product.getCategory();
            String categoryName = category != null ? category.getNameCategory() : null;

            // Imprimindo o resultado para debug
            System.out.println("Product ID: " + product.getId());
            System.out.println("Category Object: " + category);
            System.out.println("Category Name: " + categoryName);
        }

        // Mapear produtos para ProductResponse
        List<ProductResponse> productResponses = products.stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCategory() != null ? product.getCategory().getNameCategory(): null,
                        product.getAvailable()
                ))
                .toList();

        return ResponseEntity.ok(productResponses);
    }


    // 2. Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        // Converter Product para ProductResponse
        ProductResponse response = new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory() != null ? product.getCategory().getNameCategory() : null,
                product.getAvailable()
        );

        return ResponseEntity.ok(response);
    }

    // 3. Add a new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = productService.addProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    // 4. Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // 5. Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Sorting and filtering (optional)
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        List<Product> products = productService.searchProducts(name, category, available);
        return ResponseEntity.ok(products);
    }
}
