package com.github.hfantin.veiculos.domain.exception;

public class Auth0AuthenticationException extends RuntimeException{

    public Auth0AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

}
