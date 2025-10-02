package com.github.hfantin.veiculos.infrastructure.persistence.repository;

import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleJpaRepository extends JpaRepository<SaleEntity, Integer> {
    List<SaleEntity> findByCustomerId(Integer customerId);
    boolean existsById(Integer id);
}
