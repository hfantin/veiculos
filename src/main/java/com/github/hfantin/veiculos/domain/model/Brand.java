package com.github.hfantin.veiculos.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Brand {

    private Integer id;
    private String name;
    private LocalDateTime createdAt;

    public Brand(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    // Business Methods
    public void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Brand name cannot exceed 100 characters");
        }
    }

}
