package com.github.hfantin.veiculos.infrastructure.web.dto;

import com.github.hfantin.veiculos.domain.model.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequest {
    private String phone;
    private String address;
    private CustomerType type;
}
