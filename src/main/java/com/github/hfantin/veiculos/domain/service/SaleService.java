package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {
    Sale save(Sale sale);
    Optional<Sale> findById(Integer id);
    List<Sale> findAll();
    List<Sale> findByCustomerId(Integer customerId);
    Sale update(Sale sale);
    void deleteById(Integer id);
}
