package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.model.enums.SaleStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SaleService {
    Sale createSale(Sale sale);
    Optional<Sale> getSaleById(Integer id);
    List<Sale> getSalesByCustomerId(Integer customerId);
    List<Sale> getSalesByStatus(SaleStatus status);
    List<Sale> getSalesByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    Sale cancelSale(Integer id);
    Sale completeSale(Integer id);
    Sale pendSale(Integer id);
    void deleteSale(Integer id);
    long countSales();
}
