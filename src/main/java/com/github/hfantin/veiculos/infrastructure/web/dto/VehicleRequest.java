package com.github.hfantin.veiculos.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Schema(description = "DTO para criação ou atualização de um veículo")
public class VehicleRequest {

    @NotNull(message = "Model ID is required")
    @Schema(description = "ID do modelo do veículo", example = "1", required = true)
    private Integer modelId;

    @NotNull(message = "Year is required")
    @Min(value = 1886, message = "Year must be at least 1886")
    @Max(value = 2100, message = "Year cannot exceed 2100")
    @Schema(description = "Ano do veículo", example = "2023", required = true)
    private Integer year;

    @NotBlank(message = "Color is required")
    @Size(max = 50, message = "Color cannot exceed 50 characters")
    @Schema(description = "Cor do veículo", example = "Preto", required = true, maxLength = 50)
    private String color;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have up to 10 integer digits and 2 fraction digits")
    @Schema(description = "Preço do veículo", example = "45000.00", required = true)
    private BigDecimal price;
}