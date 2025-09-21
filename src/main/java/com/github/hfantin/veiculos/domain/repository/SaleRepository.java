package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {

    Sale save(Sale sale);
    Optional<Sale> findById(Long id);
    List<Sale> findByCustomerId(Long customerId);
    List<Sale> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
