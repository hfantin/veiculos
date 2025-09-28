package com.github.hfantin.veiculos.infrastructure.web.dto;

import com.github.hfantin.veiculos.domain.model.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
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
}
