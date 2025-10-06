package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Sale;
import jakarta.mail.MessagingException;

public interface VehicleSaleService {
    Sale initiateVehicleSale(Integer vehicleId, Integer customerId);
    Sale completeSale(Integer saleId, String transactionId);
    Sale cancelSale(Integer saleId);
}
