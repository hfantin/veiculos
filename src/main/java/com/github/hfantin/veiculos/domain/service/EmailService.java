package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Brand;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Optional;

public interface EmailService {
    void sendReserved(String to, String name, String link, String dadosVeiculo) throws MessagingException;
    void sendConfirmed(String to, String name, String dadosVeiculo) throws MessagingException;
    void sendCanceled(String to, String name, String dadosVeiculo) throws MessagingException;
}
