package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.config.AuthConfig;
import com.github.hfantin.veiculos.domain.exception.Auth0AuthenticationException;
import com.github.hfantin.veiculos.infrastructure.web.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
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

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/token")
    public Map<String, Object> me(
            @AuthenticationPrincipal OidcUser oidcUser,
            Authentication authentication) {

        Map<String, Object> response = new HashMap<>();
//        response.put("userClaims", oidcUser.getClaims());

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


    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> getAccessToken() {
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
            var response = restTemplate.postForEntity(tokenUrl, request, AuthResponse.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            throw new Auth0AuthenticationException("Failed to get token", e);
        }
    }

    @GetMapping("/auth/callback")
    public ResponseEntity<AuthResponse> authCallback() {
        return ResponseEntity.ok(new AuthResponse("abc123", 1, "Bearer"));

    }

    @GetMapping("/auth/logout")
    public ResponseEntity<String> authLogout() {
        return ResponseEntity.ok("successfully logged out!");

    }


}
