package com.github.hfantin.veiculos.config;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.service.CustomerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserValidationFilter extends OncePerRequestFilter {

    private final CustomerService customerService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Se não há autenticação ou é anônimo, continua o fluxo (será tratado pelo Spring Security)
        if (authentication == null || !authentication.isAuthenticated() ||
                "anonymousUser".equals(authentication.getPrincipal())) {
            filterChain.doFilter(request, response);
            return;
        }

        String authId = authentication.getName();
        log.debug("Validando usuário com authId: {} para requisição: {}", authId, request.getRequestURI());

        try {
            // Verifica se o usuário está validado
            boolean isValidated = customerService.findByAuthId(authId)
                    .map(Customer::getValidated)
                    .orElse(false);

            if (!isValidated) {
                log.warn("Acesso negado para usuário não validado: {}", authId);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{\"error\": \"Usuário com cadastro incompleto.\"}");
                response.getWriter().flush();
                return;
            }

            // Usuário validado, continua o fluxo
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("Erro ao validar usuário: {}", e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Erro interno ao validar usuário\"}");
            response.getWriter().flush();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Não aplicar o filtro para endpoints públicos
        String path = request.getRequestURI();
        return
                path.equals("/") ||
                path.startsWith("/api/customers") ||
                path.startsWith("/info") ||
                path.startsWith("/swagger-ui") ||
                path.startsWith("/api-docs") ||
                path.startsWith("/v3/api-docs") ||
                path.startsWith("/swagger-resources") ||
                path.startsWith("/webjars") ||
                path.startsWith("/public") ||
                path.equals("/favicon.ico");
    }
}
