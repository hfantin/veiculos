package com.github.hfantin.veiculos.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "auth")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthConfig {
    private String clientId;
    private String clientSecret;
}
