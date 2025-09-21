package com.github.hfantin.veiculos.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Brand {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;

    public Brand() {}

    public Brand(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public Brand(Integer id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Business Methods
    public void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Brand name cannot exceed 100 characters");
        }
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id) &&
                Objects.equals(name, brand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
