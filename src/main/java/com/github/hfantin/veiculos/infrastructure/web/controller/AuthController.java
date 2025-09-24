package com.github.hfantin.veiculos.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Operation(
            summary = "Get Auth0 Bearer Token (client credentials grant)",
            description = "This endpoint is a placeholder to show how to get a token from Auth0. Use a POST request to the Auth0 domain directly."
    )
    @ApiResponse(responseCode = "200", description = "Token response")
    @PostMapping("/token")
    public ResponseEntity<String> getTokenExample() {
        return ResponseEntity.ok("""
                curl --request POST \\
                  --url https://hfantin.us.auth0.com/oauth/token \\
                  --header 'content-type: application/json' \\
                  --data '{
                    "client_id": "?",
                    "client_secret": "?",
                    "audience": "https://veiculos/api",
                    "grant_type": "client_credentials"
                  }'
                """);
    }
}
