package com.github.hfantin.veiculos.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "uk_brands_name")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ModelEntity> models = List.of();

    public BrandEntity(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
