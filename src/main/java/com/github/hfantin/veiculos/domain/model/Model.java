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
    private Long id;
    private Long brandId;
    private String name;
    private LocalDateTime createdAt;

    public Model(Long brandId, String name) {
        this.brandId = brandId;
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }
}
