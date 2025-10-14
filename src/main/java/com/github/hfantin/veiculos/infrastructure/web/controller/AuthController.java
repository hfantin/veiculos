package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.service.CustomerService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@Hidden
public class AuthController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/token")
    public Map<String, Object> obterToken(
            @AuthenticationPrincipal OidcUser oidcUser,
            Authentication authentication) {

        Map<String, Object> response = new HashMap<>();

        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
            OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                    oauthToken.getAuthorizedClientRegistrationId(),
                    oauthToken.getName());
            if (client != null && client.getAccessToken() != null) {
                response.put("accessToken", client.getAccessToken().getTokenValue());
            }
        }

        Map<String, Object> claims = oidcUser.getClaims();
        String email = (String) claims.get("email");
        String firstName = (String) claims.get("given_name");

        response.put("mensagem",
                customerService.findByEmail(email)
                        .filter(Customer::getValidated)
                        .map(c -> "Cadastro completo") // ajuste a mensagem conforme necessário
                        .orElse("Seja bem vindo " + firstName + ", seu cadastro foi efetuado com sucesso, por favor, complete o cadastro para utilizar nossa plataforma.")
        );

        return response;
    }


    @GetMapping("/auth/token")
    public Map<String, Object> token(@AuthenticationPrincipal OidcUser principal) {
        return principal.getClaims();
    }
}
