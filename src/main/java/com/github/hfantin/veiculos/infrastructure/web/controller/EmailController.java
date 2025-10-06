package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/public")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/email/reservado")
    public ResponseEntity<String> enviarEmailReservado() throws MessagingException {
        emailService.sendReserved("hfantin@gmail.com", "Hamilton", "https://teste.com.br", "Toyota Corola prata 2020 - R$ 100.000,00");
        return ResponseEntity.ok("reservado com sucesso");
    }

    @GetMapping("/email/confirmado")
    public ResponseEntity<String> enviarEmailConfirmado() throws MessagingException {
        emailService.sendConfirmed("hfantin@gmail.com", "Hamilton", "Toyota Corola prata 2020 - R$ 100.000,00");
        return ResponseEntity.ok("confirmado com sucesso");
    }

    @GetMapping("/email/cancelado")
    public ResponseEntity<String> enviarEmailCancelado() throws MessagingException {
        emailService.sendCanceled("hfantin@gmail.com", "Hamilton",  "Toyota Corola prata 2020 - R$ 100.000,00");
        return ResponseEntity.ok("cancelado com sucesso");
    }

}
