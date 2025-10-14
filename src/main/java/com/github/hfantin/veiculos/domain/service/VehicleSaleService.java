package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Sale;
import jakarta.mail.MessagingException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VehicleSaleService {
    Sale initiateVehicleSale(Integer vehicleId, Integer customerId);
    Sale completeSale(Integer saleId, String transactionId, String paymentMethod);
    Sale cancelSale(Integer saleId);

    @Transactional
    List<Sale> listSales(Integer customerId);
}
