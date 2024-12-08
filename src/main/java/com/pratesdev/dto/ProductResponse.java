package com.pratesdev.dto;

import com.pratesdev.model.Product;

public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private Boolean available;

    // Construtor
    // Construtor que recebe um objeto Product
    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory() != null ? product.getCategory().getNameCategory() : null;
        this.available = product.getAvailable();
    }

    public ProductResponse(Long id, String name, String description, Double price, String category, Boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.available = available;
    }


    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getAvailable() {
        return available;
    }
}
