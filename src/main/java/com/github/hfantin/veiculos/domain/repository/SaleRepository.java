package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.model.enums.SaleStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    Sale save(Sale sale);
    Optional<Sale> findById(Integer id);
    List<Sale> findByCustomerId(Integer customerId);
    List<Sale> findByStatus(SaleStatus status);
    List<Sale> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    boolean existsById(Integer id);
    void deleteById(Integer id);
    long count();
}
