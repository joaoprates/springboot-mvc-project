package com.pratesdev.config;

import com.pratesdev.model.Category;
import com.pratesdev.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initCategories(CategoryRepository categoryRepository) {
        return args -> {
            List<String> categories = List.of("Electronics", "Books", "Clothing", "Home Appliances");

            categories.forEach(name -> {
                if (!categoryRepository.existsByName(name)) {
                    categoryRepository.save(new Category(name));
                }
            });
        };
    }
}
