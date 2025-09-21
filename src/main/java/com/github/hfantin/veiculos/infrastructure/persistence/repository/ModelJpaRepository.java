package com.github.hfantin.veiculos.infrastructure.persistence.repository;

import com.github.hfantin.veiculos.infrastructure.persistence.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelJpaRepository extends JpaRepository<ModelEntity, Integer> {

    Optional<ModelEntity> findByBrandIdAndName(Integer brandId, String name);

    List<ModelEntity> findByBrandId(Integer brandId);

    boolean existsByBrandIdAndName(Integer brandId, String name);

    @Query("SELECT m FROM ModelEntity m ORDER BY m.name ASC")
    List<ModelEntity> findAllOrderedByName();

    @Query("SELECT m FROM ModelEntity m ORDER BY m.brandId, m.name ASC")
    List<ModelEntity> findAllOrderedByBrandAndName();
}
