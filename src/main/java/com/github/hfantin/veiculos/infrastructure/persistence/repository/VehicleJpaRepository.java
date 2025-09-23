package com.github.hfantin.veiculos.infrastructure.persistence.repository;

import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleJpaRepository extends JpaRepository<VehicleEntity, Integer> {

    List<VehicleEntity> findByStatus(VehicleStatus status);

    @Query("SELECT v FROM VehicleEntity v JOIN FETCH v.model m JOIN FETCH m.brand WHERE v.status = :status")
    List<VehicleEntity> findByStatusWithDetails(@Param("status") VehicleStatus status);

    List<VehicleEntity> findByModelId(Integer modelId);

    @Query("SELECT v FROM VehicleEntity v JOIN FETCH v.model m JOIN FETCH m.brand WHERE v.model.id = :modelId")
    List<VehicleEntity> findByModelIdWithDetails(@Param("modelId") Integer modelId);

    @Query("SELECT v FROM VehicleEntity v WHERE v.price BETWEEN :minPrice AND :maxPrice")
    List<VehicleEntity> findByPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    @Query("SELECT v FROM VehicleEntity v JOIN FETCH v.model m JOIN FETCH m.brand WHERE v.price BETWEEN :minPrice AND :maxPrice")
    List<VehicleEntity> findByPriceRangeWithDetails(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    @Query("SELECT v FROM VehicleEntity v WHERE v.year BETWEEN :startYear AND :endYear")
    List<VehicleEntity> findByYearRange(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);

    @Query("SELECT v FROM VehicleEntity v JOIN FETCH v.model m JOIN FETCH m.brand WHERE v.year BETWEEN :startYear AND :endYear")
    List<VehicleEntity> findByYearRangeWithDetails(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);

    @Query("SELECT v FROM VehicleEntity v WHERE v.model.brand.id = :brandId")
    List<VehicleEntity> findByBrandId(@Param("brandId") Integer brandId);

    @Query("SELECT v FROM VehicleEntity v JOIN FETCH v.model m JOIN FETCH m.brand WHERE v.model.brand.id = :brandId")
    List<VehicleEntity> findByBrandIdWithDetails(@Param("brandId") Integer brandId);

    @Query("SELECT v FROM VehicleEntity v JOIN FETCH v.model m JOIN FETCH m.brand WHERE v.id = :id")
    Optional<VehicleEntity> findByIdWithDetails(@Param("id") Integer id);

    @Query("SELECT v FROM VehicleEntity v JOIN FETCH v.model m JOIN FETCH m.brand")
    List<VehicleEntity> findAllWithDetails();

    @Query("SELECT v FROM VehicleEntity v JOIN FETCH v.model m JOIN FETCH m.brand ORDER BY v.price ASC")
    List<VehicleEntity> findAllWithDetailsOrderedByPrice();

    @Query("SELECT v FROM VehicleEntity v JOIN FETCH v.model m JOIN FETCH m.brand WHERE v.status = 'AVAILABLE' ORDER BY v.price ASC")
    List<VehicleEntity> findAvailableWithDetailsOrderedByPrice();

    @Query("SELECT v FROM VehicleEntity v JOIN FETCH v.model m JOIN FETCH m.brand WHERE v.status = 'SOLD' ORDER BY v.price ASC")
    List<VehicleEntity> findSoldWithDetailsOrderedByPrice();
}
