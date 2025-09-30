package com.github.hfantin.veiculos.infrastructure.web.controller;

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
public class AuthController {

    private final RestTemplate restTemplate = new RestTemplate();



    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/token")
    public Map<String, Object> me(
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

        return response;
    }

}
