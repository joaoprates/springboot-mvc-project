package com.pratesdev.repository;

import com.pratesdev.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // 1. Find products by name
    List<Product> findByNameContainingIgnoreCase(String name);

    // 2. Find products by category
    List<Product> findByCategoryIgnoreCase(String category);

    // 3. Find products by availability
    List<Product> findByAvailable(Boolean available);
}
