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

    // Buscar produtos pelo nome
    List<Product> findByNameContainingIgnoreCase(String name);

    // Buscar produtos pela categoria
    List<Product> findByCategory(Category category);

    // Buscar produtos pela disponibilidade
    List<Product> findByAvailable(Boolean available);

    // Buscar produtos com base no nome da categoria
    @Query("SELECT p FROM Product p WHERE LOWER(p.category.name) = LOWER(:categoryName)")
    List<Product> findByCategoryNameIgnoreCase(@Param("categoryName") String categoryName);
}

