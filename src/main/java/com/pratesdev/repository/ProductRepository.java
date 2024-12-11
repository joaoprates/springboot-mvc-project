package com.pratesdev.repository;

import com.pratesdev.model.Category;
import com.pratesdev.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // Search products by name
    List<Product> findByNameContainingIgnoreCase(String name);

    // Search products by category
    List<Product> findByCategory(Category category);

    // Search products by availability
    List<Product> findByAvailable(Boolean available);

    // Search products based on category name
    @Query("SELECT p FROM Product p WHERE LOWER(p.category.name) = LOWER(:categoryName)")
    List<Product> findByCategoryNameIgnoreCase(@Param("categoryName") String categoryName);
}