package com.github.hfantin.veiculos.infrastructure.persistence.repository;

import com.github.hfantin.veiculos.infrastructure.persistence.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelJpaRepository extends JpaRepository<ModelEntity, Integer> {

    Optional<ModelEntity> findByBrandIdAndName(Integer brandId, String name);

    List<ModelEntity> findByBrandId(Integer brandId);

    @Query("SELECT m FROM ModelEntity m JOIN FETCH m.brand WHERE m.brand.id = :brandId")
    List<ModelEntity> findByBrandIdWithBrand(@Param("brandId") Integer brandId);

    boolean existsByBrandIdAndName(Integer brandId, String name);

    @Query("SELECT m FROM ModelEntity m ORDER BY m.name ASC")
    List<ModelEntity> findAllOrderedByName();

    @Query("SELECT m FROM ModelEntity m JOIN FETCH m.brand ORDER BY m.name ASC")
    List<ModelEntity> findAllOrderedByNameWithBrand();

    @Query("SELECT m FROM ModelEntity m JOIN FETCH m.brand ORDER BY m.brand.name, m.name ASC")
    List<ModelEntity> findAllOrderedByBrandAndName();

    @Query("SELECT m FROM ModelEntity m JOIN FETCH m.brand WHERE m.id = :id")
    Optional<ModelEntity> findByIdWithBrand(@Param("id") Integer id);

    @Query("SELECT m FROM ModelEntity m JOIN FETCH m.brand")
    List<ModelEntity> findAllWithBrand();
}
