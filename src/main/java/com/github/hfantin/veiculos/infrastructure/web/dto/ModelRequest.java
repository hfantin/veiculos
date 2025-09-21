package com.github.hfantin.veiculos.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Schema(description = "DTO para criação ou atualização de um modelo")
public class ModelRequest {

    @NotNull(message = "Brand ID is required")
    @Schema(description = "ID da marca do modelo", example = "1", required = true)
    private Integer brandId;

    @NotBlank(message = "Model name is required")
    @Size(max = 100, message = "Model name cannot exceed 100 characters")
    @Schema(description = "Nome do modelo", example = "Corolla", required = true, maxLength = 100)
    private String name;
}
