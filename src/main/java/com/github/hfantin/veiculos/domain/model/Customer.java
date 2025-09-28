package com.github.hfantin.veiculos.domain.model;

import com.github.hfantin.veiculos.domain.model.enums.CustomerType;
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
    private CustomerType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Customer(String authId, String firstName, String lastName, String email) {
        this.authId = authId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = CustomerType.BUYER;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void validate() {
        if (authId == null || authId.trim().isEmpty()) {
            throw new IllegalArgumentException("Auth ID cannot be null or empty");
        }
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (type == null) {
            throw new IllegalArgumentException("Customer type cannot be null");
        }
    }

    public void validateForUpdate() {
        //TODO validar telefone quando existir
        //TODO validar endereço quando existir

        //TODO VALIDAR SE tipo é valido
        if (type == null) {
            throw new IllegalArgumentException("Customer type cannot be null");
        }
    }

    public void updateProfile(String firstName, String lastName, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateType(CustomerType type) {
        this.type = type;
        this.updatedAt = LocalDateTime.now();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
