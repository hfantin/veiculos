package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.repository.SaleRepository;
import com.github.hfantin.veiculos.domain.service.SaleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    @Override
    public Sale save(Sale sale) {
        log.info("Saving sale for customer ID: {}", sale.getCustomerId());
        sale.setCreatedAt(LocalDateTime.now());
        if (sale.getSaleDate() == null) {
            sale.setSaleDate(LocalDateTime.now());
        }
        return saleRepository.save(sale);
    }

    @Override
    public Optional<Sale> findById(Integer id) {
        return saleRepository.findById(id);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> findByCustomerId(Integer customerId) {
        return saleRepository.findByCustomerId(customerId);
    }

    @Override
    public Sale update(Sale sale) {
        log.info("Updating sale with ID: {}", sale.getId());
        Sale existingSale = saleRepository.findById(sale.getId())
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with ID: " + sale.getId()));

        existingSale.setCustomerId(sale.getCustomerId());
        existingSale.setSaleDate(sale.getSaleDate());
        existingSale.setTotalAmount(sale.getTotalAmount());
        existingSale.setPaymentMethod(sale.getPaymentMethod());
        existingSale.setTransactionId(sale.getTransactionId());
        existingSale.setStatus(sale.getStatus());

        return saleRepository.save(existingSale);
    }

    @Override
    public void deleteById(Integer id) {
        if (!saleRepository.existsById(id)) {
            throw new IllegalArgumentException("Sale not found with ID: " + id);
        }
        saleRepository.deleteById(id);
        log.info("Sale deleted with ID: {}", id);
    }
}