package com.github.hfantin.veiculos.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO de resposta para operações com modelos")
public class ModelResponse {

    @Schema(description = "ID único do modelo", example = "1")
    private Integer id;

    @Schema(description = "ID da marca do modelo", example = "1")
    private Integer brandId;

    @Schema(description = "Nome da marca", example = "Toyota") // Novo campo
    private String brandName;

    @Schema(description = "Nome do modelo", example = "Corolla")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("created_at")
    @Schema(description = "Data de criação do modelo", example = "2024-01-15T10:30:00")
    private LocalDateTime createdAt;
}
