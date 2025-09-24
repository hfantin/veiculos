package com.github.hfantin.veiculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/info",
                                "/auth/token",
                                "/swagger-ui/**",
                                "/api-docs/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/public/**").permitAll()
                        .requestMatchers("/api/**").authenticated()
//                        .requestMatchers("/api/private-scoped").hasAuthority("SCOPE_read:messages")
                        .anyRequest().authenticated())
                .cors(cors -> {})
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));
                return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/public/**", "/login", "/css/**", "/js/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt()); // Or your preferred authentication
//        return http.build();
//    }

}