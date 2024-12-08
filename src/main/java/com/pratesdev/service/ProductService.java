package com.pratesdev.service;

import com.pratesdev.dto.ProductCreateRequest;
import com.pratesdev.dto.ProductUpdateRequest;
import com.pratesdev.exception.ResourceNotFoundException;
import com.pratesdev.model.Category;
import com.pratesdev.model.Product;
import com.pratesdev.repository.CategoryRepository;
import com.pratesdev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    // 1. List all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryNameIgnoreCase(categoryName);
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

    public Product createProduct(ProductCreateRequest request) {
        // Buscar a categoria pelo nome
        Category category = categoryRepository.findByName(request.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // Criar o novo produto
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setAvailable(request.getAvailable());
        product.setCategory(category);

        // Salvar no banco de dados
        return productRepository.save(product);
    }


    // 4. Update an existing product
    public void updateCategory(Product product, String categoryName) {
        if (categoryName != null && !categoryName.isEmpty()) {
            Category category = categoryRepository.findByName(categoryName)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryName));
            product.setCategory(category);
        } else {
            product.setCategory(null); // Caso a categoria não seja fornecida
        }
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
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
            return productRepository.findByCategoryNameIgnoreCase(category);
        } else if (available != null) {
            return productRepository.findByAvailable(available);
        }
        return productRepository.findAll();
    }
}