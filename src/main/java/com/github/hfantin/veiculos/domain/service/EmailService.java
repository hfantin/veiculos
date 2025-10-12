package com.github.hfantin.veiculos.domain.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendReserved(String to, String name, String link, String dadosVeiculo) throws MessagingException;
    void sendConfirmed(String to, String name, String dadosVeiculo) throws MessagingException;
    void sendCanceled(String to, String name, String dadosVeiculo) throws MessagingException;
}
