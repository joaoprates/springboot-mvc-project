package com.pratesdev.repository;

import com.pratesdev.model.Category;
import com.pratesdev.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use um banco de dados real, se necessário
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        // Clean up the database before each test
        productRepository.deleteAll();
    }
    @Test
    void findByNameContainingIgnoreCase_ShouldReturnProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setName("TestProduct1");
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setName("AnotherProduct");
        entityManager.persist(product2);

        // Act
        List<Product> products = productRepository.findByNameContainingIgnoreCase("test");

        // Assert
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("TestProduct1");
    }

    @Test
    void findByCategoryNameIgnoreCase_ShouldReturnProducts() {
        // Arrange
        Category category = new Category();
        category.setName("Supercars");
        entityManager.persist(category);

        Product product = new Product();
        product.setName("Laptop");
        product.setCategory(category);
        entityManager.persist(product);

        // Act
        List<Product> products = productRepository.findByCategoryNameIgnoreCase("supercars");

        // Assert
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Laptop");
    }
}
