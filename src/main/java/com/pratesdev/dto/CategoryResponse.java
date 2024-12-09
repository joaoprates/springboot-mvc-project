package com.pratesdev.dto;

public class CategoryResponse {
    private Long id;
    private String name;

    public CategoryResponse() {
        // Default constructor
    }

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
