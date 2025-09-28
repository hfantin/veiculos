package com.github.hfantin.veiculos.infrastructure.persistence.repository;

import com.github.hfantin.veiculos.domain.model.enums.CustomerType;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {

    Optional<CustomerEntity> findByAuthId(String authId);
    Optional<CustomerEntity> findByEmail(String email);
    boolean existsByAuthId(String authId);
    boolean existsByEmail(String email);
    List<CustomerEntity> findByType(CustomerType type);
    boolean existsById(Long id);
}
