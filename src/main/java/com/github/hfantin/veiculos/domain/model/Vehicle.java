package com.github.hfantin.veiculos.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Vehicle {
    public enum Status {
        AVAILABLE, SOLD, RESERVED
    }

    private Long id;
    private Long modelId;
    private Integer year;
    private String color;
    private BigDecimal price;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime soldAt;

    public Vehicle(Long modelId, Integer year, String color, BigDecimal price) {
        this.modelId = modelId;
        this.year = year;
        this.color = color;
        this.price = price;
        this.status = Status.AVAILABLE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}