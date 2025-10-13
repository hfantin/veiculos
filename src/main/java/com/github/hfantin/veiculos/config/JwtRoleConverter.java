package com.github.hfantin.veiculos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtRoleConverter extends JwtAuthenticationConverter {

    public JwtRoleConverter() {
        log.info("iniciando roleConverter");
        setJwtGrantedAuthoritiesConverter(new Converter<Jwt, Collection<GrantedAuthority>>() {
            @Override
            public Collection<GrantedAuthority> convert(Jwt jwt) {
                Object rolesClaim = jwt.getClaim("https://veiculos/roles");
                log.info("Obtendo perfis do token: {}", rolesClaim);

                if (rolesClaim instanceof List<?> roles) {
                    List<GrantedAuthority> authorities = roles.stream()
                            .map(Object::toString)
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                            .collect(Collectors.toList());
                    log.info("Autorizações extraídas: {}", authorities);
                    return authorities;
                }

                log.info("Nenhum papel encontrado no token");
                return List.of();
            }
        });
    }
}

