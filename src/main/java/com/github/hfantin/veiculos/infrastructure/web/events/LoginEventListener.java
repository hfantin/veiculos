package com.github.hfantin.veiculos.infrastructure.web.events;

import com.github.hfantin.veiculos.domain.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class LoginEventListener {

    private final CustomerService customerService;

    @EventListener
    public void onLogin(InteractiveAuthenticationSuccessEvent event) {
        var auth = event.getAuthentication();
        String authId = auth.getName();

        log.info("identificador do usuário logado: {}", authId);

        var principal = event.getAuthentication().getPrincipal();
        if (principal instanceof OidcUser oidcUser) {
            log.info("dados do usuário {}", oidcUser);
            Map<String, Object> claims = oidcUser.getClaims();
            String email = (String) claims.get("email");
            String givenName = (String) claims.get("given_name");
            String familyName = (String) claims.get("family_name");
            String fullName = (String) claims.get("name");

            String firstName = givenName != null ? givenName : extractFirstName(fullName);
            String lastName = familyName != null ? familyName : extractLastName(fullName);

            try {
                var customer = customerService.createOrUpdateFromOAuth(authId, email, firstName, lastName);
                log.info("cliente processado com sucesso - id: {}, email: {}", customer.getId(), customer.getEmail());
            } catch (Exception e) {
                log.error("erro processando cliente após login: {}", e.getMessage(), e);
            }
        }
    }

    private String extractFirstName(String fullName) {
        if (fullName == null) return "Desconhecido";
        String[] names = fullName.split(" ");
        return names.length > 0 ? names[0] : "Desconhecido";
    }

    private String extractLastName(String fullName) {
        if (fullName == null) return "Desconhecido";
        String[] names = fullName.split(" ");
        return names.length > 1 ? names[names.length - 1] : "Desconhecido";
    }
}

