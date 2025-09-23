package com.github.hfantin.veiculos.infrastructure.persistence.repository;

import com.github.hfantin.veiculos.infrastructure.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandJpaRepository extends JpaRepository<BrandEntity, Integer> {

    Optional<BrandEntity> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT b FROM BrandEntity b ORDER BY b.name ASC")
    List<BrandEntity> findAllOrderedByName();
}