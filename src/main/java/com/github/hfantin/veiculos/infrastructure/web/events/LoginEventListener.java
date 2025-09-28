package com.github.hfantin.veiculos.infrastructure.web.events;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginEventListener {

    @EventListener
    public void onLogin(InteractiveAuthenticationSuccessEvent event) {
        var auth = event.getAuthentication();

        System.out.println("User logged in: " + auth.getName());

        //TODO gravar dados do usuario logado na base de dados
        var principal = event.getAuthentication().getPrincipal();
        if (principal instanceof OidcUser oidcUser) {
            Map<String, Object> claims = oidcUser.getClaims();
            System.out.println("User email: " + claims.get("email"));
            System.out.println("claims " + claims);
        }

        // Call a service, e.g., update last login timestamp
        // userService.updateLastLogin(auth.getName());

        // Optionally log access token if using OAuth2
//        if (auth.getDetails() instanceof OAuth2AuthenticationDetails details) {
//            System.out.println("Access token: " + details.getTokenValue());
//        }
    }
}

