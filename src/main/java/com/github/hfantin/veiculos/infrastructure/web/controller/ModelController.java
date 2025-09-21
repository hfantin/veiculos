package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.service.ModelService;
import com.github.hfantin.veiculos.infrastructure.web.dto.ModelRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.ModelResponse;
import com.github.hfantin.veiculos.infrastructure.web.mapper.ModelWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/models")
@Tag(name = "Models", description = "API para gerenciamento de modelos de veículos")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;
    private final ModelWebMapper modelWebMapper;

    @PostMapping
    @Operation(summary = "Criar um novo modelo", description = "Cria um novo modelo de veículo no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Modelo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou modelo já existe"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    public ResponseEntity<ModelResponse> createModel(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do modelo a ser criado",
                    required = true,
                    content = @Content(examples = @ExampleObject(value = "{\"brandId\": 1, \"name\": \"Corolla\"}"))
            )
            @Valid @RequestBody ModelRequest request) {

        var model = modelWebMapper.toDomain(request);
        var createdModel = modelService.createModel(model);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelWebMapper.toResponse(createdModel));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar modelo por ID", description = "Retorna os detalhes de um modelo específico pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Modelo encontrado"),
            @ApiResponse(responseCode = "404", description = "Modelo não encontrado")
    })
    public ResponseEntity<ModelResponse> getModelById(
            @Parameter(description = "ID do modelo", example = "1", required = true)
            @PathVariable Integer id) {

        return modelService.getModelById(id)
                .map(model -> ResponseEntity.ok(modelWebMapper.toResponse(model)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/brand/{brandId}")
    @Operation(summary = "Buscar modelos por marca", description = "Retorna todos os modelos de uma marca específica")
    @ApiResponse(responseCode = "200", description = "Modelos encontrados")
    public ResponseEntity<List<ModelResponse>> getModelsByBrandId(
            @Parameter(description = "ID da marca", example = "1", required = true)
            @PathVariable Integer brandId) {

        var models = modelService.getModelsByBrandId(brandId).stream()
                .map(modelWebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @GetMapping
    @Operation(summary = "Listar todos os modelos", description = "Retorna uma lista de todos os modelos cadastrados")
    public ResponseEntity<List<ModelResponse>> getAllModels(
            @Parameter(description = "Ordenar resultados por nome", example = "false")
            @RequestParam(defaultValue = "false") boolean ordered) {

        List<ModelResponse> models;
        if (ordered) {
            models = modelService.getAllModelsOrderedByName().stream()
                    .map(modelWebMapper::toResponse)
                    .collect(Collectors.toList());
        } else {
            models = modelService.getAllModels().stream()
                    .map(modelWebMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(models);
    }

    @GetMapping("/exists")
    @Operation(summary = "Verificar existência de modelo", description = "Verifica se um modelo com o nome especificado já existe para uma marca")
    public ResponseEntity<Boolean> modelExists(
            @Parameter(description = "ID da marca", example = "1", required = true)
            @RequestParam Integer brandId,

            @Parameter(description = "Nome do modelo", example = "Corolla", required = true)
            @RequestParam String name) {

        return ResponseEntity.ok(modelService.modelExists(brandId, name));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um modelo", description = "Atualiza os dados de um modelo existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Modelo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Modelo não encontrado")
    })
    public ResponseEntity<ModelResponse> updateModel(
            @Parameter(description = "ID do modelo a ser atualizado", example = "1", required = true)
            @PathVariable Integer id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados do modelo",
                    required = true,
                    content = @Content(examples = @ExampleObject(value = "{\"brandId\": 1, \"name\": \"Novo Corolla\"}"))
            )
            @Valid @RequestBody ModelRequest request) {

        var model = modelWebMapper.toDomain(request);
        var updatedModel = modelService.updateModel(id, model);
        return ResponseEntity.ok(modelWebMapper.toResponse(updatedModel));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um modelo", description = "Remove um modelo do sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Modelo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Modelo não encontrado")
    })
    public ResponseEntity<Void> deleteModel(
            @Parameter(description = "ID do modelo a ser deletado", example = "1", required = true)
            @PathVariable Integer id) {

        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    @Operation(summary = "Contar total de modelos", description = "Retorna o número total de modelos cadastrados")
    public ResponseEntity<Long> countModels() {
        return ResponseEntity.ok(modelService.countModels());
    }
}
