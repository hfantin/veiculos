package com.github.hfantin.veiculos.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Model {
    private Integer id;
    private Integer brandId;
    private String name;
    private LocalDateTime createdAt;

    public Model(Integer brandId, String name) {
        this.brandId = brandId;
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public void validate() {
        if (brandId == null) {
            throw new IllegalArgumentException("Brand ID cannot be null");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Model name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Model name cannot exceed 100 characters");
        }
    }
}
