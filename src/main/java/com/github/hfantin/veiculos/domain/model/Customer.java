package com.github.hfantin.veiculos.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Customer {
    public enum Type {
        BUYER, SELLER, BOTH
    }

    private Long id;
    private String authId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Type type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Customer(String authId, String firstName, String lastName, String email) {
        this.authId = authId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = Type.BUYER;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
