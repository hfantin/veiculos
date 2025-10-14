package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.model.*;
import com.github.hfantin.veiculos.domain.model.enums.SaleStatus;
import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;
import com.github.hfantin.veiculos.domain.service.*;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VehicleSaleServiceImpl implements VehicleSaleService {

    private final SaleService saleService;
    private final SaleVehicleService saleVehicleService;
    private final VehicleService vehicleService;
    private final CustomerService customerService;
    private final EmailService emailService;
    private final MercadoPagoService mercadoPagoService;

    @Override
    @Transactional
    public Sale initiateVehicleSale(Integer vehicleId, Integer customerId) {
        log.info("Initiating vehicle sale - Vehicle ID: {}, Customer ID: {}", vehicleId, customerId);

        // Verificar se o veículo existe e está disponível
        Vehicle vehicle = vehicleService.getVehicleByIdWithDetails(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado com ID: " + vehicleId));

        if (vehicle.getStatus() != VehicleStatus.AVAILABLE) {
            throw new IllegalArgumentException("Veículo não está disponível para venda. Status atual: " + vehicle.getStatus());
        }

        // Verificar se o cliente existe
        Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + customerId));

        // Verificar se o veículo já está em alguma venda
        if (saleVehicleService.existsByVehicleId(vehicleId)) {
            throw new IllegalArgumentException("Veículo já está em processo de venda");
        }

        // Criar a venda
        Sale sale = Sale.builder()
                .customerId(customerId)
                .totalAmount(vehicle.getPrice()) // Usar o preço do veículo como valor total
                .status(SaleStatus.PENDING)
                .build();

        Sale savedSale = saleService.save(sale);

        // Criar o registro de veículo vendido
        SaleVehicle saleVehicle = SaleVehicle.builder()
                .saleId(savedSale.getId())
                .vehicleId(vehicleId)
                .salePrice(vehicle.getPrice()) // Usar o preço do veículo
                .build();

        saleVehicleService.save(saleVehicle);

        // Atualizar status do veículo para RESERVED
        vehicle.setStatus(VehicleStatus.RESERVED);
        vehicleService.update(vehicle);

        log.info("Vehicle sale initiated successfully - Sale ID: {}, Vehicle ID: {}", savedSale.getId(), vehicleId);

        try {
            String descricaoVeiculo = String.format("%s %s %s %s", vehicle.getBrandName(), vehicle.getModelName(), vehicle.getColor(), vehicle.getYear());
            PagamentoResponse pagamentoResponse = mercadoPagoService.criarPagamento(new PagamentoRequest(descricaoVeiculo, vehicle.getPrice(), 1, customer.getEmail(), String.valueOf(savedSale.getId())));
            String link = pagamentoResponse.linkPagamento();
            savedSale.setLink(link);
            String dadosVeiculo = String.format("%s - R$ %.2f", descricaoVeiculo, vehicle.getPrice());
            emailService.sendReserved(customer.getEmail(), customer.getFirstName(), link, dadosVeiculo);
        } catch (MessagingException e) {
            log.error("não foi possível enviar o email {}", e.getNextException(), e);
        } catch (Exception e) {
            log.error("não foi possível obter o link ou enviar email: {}", e.getMessage(), e);
        }
        return savedSale;
    }

    @Override
    @Transactional
    public Sale completeSale(Integer saleId, String transactionId, String paymentMethod) {
        log.info("Completing sale - Sale ID: {}, Transaction ID: {}", saleId, transactionId);

        Sale sale = saleService.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada com ID: " + saleId));

        if (sale.getStatus() != SaleStatus.PENDING) {
            throw new IllegalArgumentException("Venda não está pendente. Status atual: " + sale.getStatus());
        }

        // Buscar o veículo da venda
        SaleVehicle saleVehicle = saleVehicleService.findBySaleId(saleId).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nenhum veículo encontrado para a venda ID: " + saleId));

        // Atualizar status da venda para COMPLETED
        sale.setStatus(SaleStatus.COMPLETED);
        sale.setTransactionId(transactionId);
        sale.setPaymentMethod(paymentMethod);
        Sale updatedSale = saleService.update(sale);

        // Atualizar status do veículo para SOLD
        Vehicle vehicle = vehicleService.getVehicleByIdWithDetails(saleVehicle.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado com ID: " + saleVehicle.getVehicleId()));

        vehicle.setStatus(VehicleStatus.SOLD);
        vehicle.setSoldAt(LocalDateTime.now());
        vehicleService.update(vehicle);

        log.info("Sale completed successfully - Sale ID: {}, Vehicle ID: {}", saleId, saleVehicle.getVehicleId());
        try {
            Customer customer = customerService.findById(sale.getCustomerId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + sale.getCustomerId()));
            String dadosVeiculo = String.format("%s %s %s %s R$ %.2f", vehicle.getBrandName(), vehicle.getModelName(), vehicle.getColor(), vehicle.getYear(), vehicle.getPrice());
            emailService.sendConfirmed(customer.getEmail(), customer.getFirstName(), dadosVeiculo);
        } catch (MessagingException e) {
            log.error("não foi possível enviar o email", e.getNextException());
        }
        return updatedSale;
    }

    @Override
    @Transactional
    public Sale cancelSale(Integer saleId) {
        log.info("Cancelling sale - Sale ID: {}", saleId);

        Sale sale = saleService.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada com ID: " + saleId));

        if (sale.getStatus() != SaleStatus.PENDING) {
            throw new IllegalArgumentException("Venda não está pendente. Status atual: " + sale.getStatus());
        }

        // Buscar o veículo da venda
        SaleVehicle saleVehicle = saleVehicleService.findBySaleId(saleId).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nenhum veículo encontrado para a venda ID: " + saleId));

        // Atualizar status da venda para CANCELLED
        sale.setStatus(SaleStatus.CANCELLED);
        Sale updatedSale = saleService.update(sale);

        // Atualizar status do veículo de volta para AVAILABLE
        Vehicle vehicle = vehicleService.getVehicleByIdWithDetails(saleVehicle.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado com ID: " + saleVehicle.getVehicleId()));

        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicleService.update(vehicle);

        log.info("Sale cancelled successfully - Sale ID: {}, Vehicle ID: {}", saleId, saleVehicle.getVehicleId());

        try {
            Customer customer = customerService.findById(sale.getCustomerId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + sale.getCustomerId()));
            String dadosVeiculo = String.format("%s %s %s %s R$ %.2f", vehicle.getBrandName(), vehicle.getModelName(), vehicle.getColor(), vehicle.getYear(), vehicle.getPrice());
            emailService.sendCanceled(customer.getEmail(), customer.getFirstName(), dadosVeiculo);
        } catch (MessagingException e) {
            log.error("não foi possível enviar o email", e.getNextException());
        }

        return updatedSale;
    }


    @Override
    public List<Sale> listSales(Integer customerId) {
        log.info("lista compras do usuario {}",  customerId);
        return saleService.findByCustomerId(customerId);
    }
}
