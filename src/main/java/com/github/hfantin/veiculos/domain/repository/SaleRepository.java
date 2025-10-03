package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    Sale save(Sale sale);
    Optional<Sale> findById(Integer id);
    List<Sale> findAll();
    List<Sale> findByCustomerId(Integer customerId);
    void deleteById(Integer id);
    boolean existsById(Integer id);
}
