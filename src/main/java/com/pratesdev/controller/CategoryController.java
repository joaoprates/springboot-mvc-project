package com.pratesdev.controller;

import com.pratesdev.dto.CategoryResponse;
import com.pratesdev.model.Category;
import com.pratesdev.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();

        // Mapear as categorias para o formato esperado pelo front-end
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(category -> new CategoryResponse(category.getId(), category.getNameCategory()))
                .toList();

        return ResponseEntity.ok(categoryResponses);
    }
}
