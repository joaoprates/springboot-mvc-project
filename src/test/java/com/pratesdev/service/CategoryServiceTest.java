package com.pratesdev.service;

import com.pratesdev.model.Category;
import com.pratesdev.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories_ShouldReturnEmptyList() {
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());

        List<Category> categories = categoryService.getAllCategories();

        assertNotNull(categories);
        assertTrue(categories.isEmpty());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getAllCategories_ShouldReturnListOfCategories() {
        List<Category> mockCategories = new ArrayList<>();
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Category1");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Category2");

        mockCategories.add(category1);
        mockCategories.add(category2);

        when(categoryRepository.findAll()).thenReturn(mockCategories);

        List<Category> categories = categoryService.getAllCategories();

        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertEquals("Category1", categories.get(0).getNameCategory());
        assertEquals("Category2", categories.get(1).getNameCategory());
        verify(categoryRepository, times(1)).findAll();
    }
}
