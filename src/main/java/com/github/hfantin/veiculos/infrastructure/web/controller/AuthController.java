package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.config.AppConfig;
import com.github.hfantin.veiculos.config.AuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/public")
public class AuthController {

    private final RestTemplate restTemplate = new RestTemplate();


    private final AuthConfig authConfig;

    @Autowired
    public AuthController(AuthConfig authConfig) {
        this.authConfig = authConfig;
    }


    @PostMapping("/auth")
    public ResponseEntity<Map<String, Object>> getAccessToken() {
        String tokenUrl = "https://hfantin.us.auth0.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println("aaa " + authConfig);

        Map<String, String> body = Map.of(
                "client_id", authConfig.getClientId(),
                "client_secret", authConfig.getClientSecret(),
                "audience", "https://veiculos/api",
                "grant_type", "client_credentials"
        );

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to get token", "details", e.getMessage()));
        }
    }
}
