package com.github.hfantin.veiculos.domain.model;

import com.github.hfantin.veiculos.domain.model.enums.CustomerType;
import com.github.hfantin.veiculos.domain.utils.CPFValidator;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Customer {

    private Integer id;
    private String authId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String cpf;
    private Boolean validated;
    private CustomerType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Customer(String authId, String firstName, String lastName, String email) {
        this.authId = authId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = CustomerType.USER;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void validateForUpdate() {
        if (phone == null || !phone.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Telefone deve conter 10 ou 11 dígitos numéricos (DDD + número)");
        }
        if (address == null || address.trim().length() < 10) {
            throw new IllegalArgumentException("Endereço inválido");
        }
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos numéricos");
        }

        if (!CPFValidator.isValid(cpf)) {
            throw new IllegalArgumentException("CPF com dígitos verificadores inválidos");
        }
    }

}
