package com.pratesdev.service;

import com.pratesdev.exception.ResourceNotFoundException;
import com.pratesdev.model.Product;
import com.pratesdev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 1. List all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. Get a product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    // 3. Add a new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // 4. Update an existing product
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        // Update product details
        existingProduct.setName(productDetails.getName());
        existingProduct.setCategory(productDetails.getCategory());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setAvailable(productDetails.isAvailable());

        return productRepository.save(existingProduct);
    }

    // 5. Delete a product
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    // 6. Search products with filters
    public List<Product> searchProducts(String name, String category, Boolean available) {
        if (name != null) {
            return productRepository.findByNameContainingIgnoreCase(name);
        } else if (category != null) {
            return productRepository.findByCategoryIgnoreCase(category);
        } else if (available != null) {
            return productRepository.findByAvailable(available);
        }
        return productRepository.findAll();
    }
}