package com.github.hfantin.veiculos.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "DTO de resposta para operações com veículos")
public class VehicleResponse {

    @Schema(description = "ID único do veículo", example = "1")
    private Integer id;

    @Schema(description = "ID do modelo do veículo", example = "1")
    private Integer modelId;

    @Schema(description = "Nome do modelo", example = "Corolla")
    private String modelName;

    @Schema(description = "ID da marca", example = "1")
    private Integer brandId;

    @Schema(description = "Nome da marca", example = "Toyota")
    private String brandName;

    @Schema(description = "Ano do veículo", example = "2023")
    private Integer year;

    @Schema(description = "Cor do veículo", example = "Preto")
    private String color;

    @Schema(description = "Preço do veículo", example = "45000.00")
    private BigDecimal price;

    @Schema(description = "Status do veículo", example = "AVAILABLE")
    private VehicleStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("created_at")
    @Schema(description = "Data de criação do veículo", example = "2024-01-15T10:30:00")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("updated_at")
    @Schema(description = "Data da última atualização do veículo", example = "2024-01-15T10:30:00")
    private LocalDateTime updatedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("sold_at")
    @Schema(description = "Data da venda do veículo", example = "2024-01-16T14:25:00")
    private LocalDateTime soldAt;
}