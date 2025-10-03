package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.model.Model;
import com.github.hfantin.veiculos.domain.repository.ModelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModelServiceImplTest {

    @Mock
    private ModelRepository modelRepository;

    @InjectMocks
    private ModelServiceImpl modelService;

    @Test
    @DisplayName("Deve salvar um modelo com sucesso")
    void shouldSaveModelSuccessfully() {
        // Given
        Model model = new Model(1, "Corolla");
        Model savedModel = Model.builder()
                .id(1)
                .brandId(1)
                .name("Corolla")
                .createdAt(LocalDateTime.now())
                .build();

        when(modelRepository.save(any(Model.class))).thenReturn(savedModel);

        // When
        Model result = modelService.createModel(model);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Corolla", result.getName());
        verify(modelRepository).save(model);
    }

    @Test
    @DisplayName("Deve encontrar modelos por brandId")
    void shouldFindModelsByBrandId() {
        // Given
        Integer brandId = 1;
        List<Model> models = Arrays.asList(
                Model.builder().id(1).brandId(brandId).name("Corolla").build(),
                Model.builder().id(2).brandId(brandId).name("Camry").build()
        );
        when(modelRepository.findByBrandId(brandId)).thenReturn(models);

        // When
        List<Model> result = modelService.getModelsByBrandId(brandId);

        // Then
        assertEquals(2, result.size());
        assertEquals("Corolla", result.get(0).getName());
        verify(modelRepository).findByBrandId(brandId);
    }

    @Test
    @DisplayName("Deve verificar se existe modelo por brandId e nome")
    void shouldCheckIfModelExistsByBrandIdAndName() {
        // Given
        Integer brandId = 1;
        String modelName = "Corolla";
        when(modelRepository.existsByBrandIdAndName(brandId, modelName)).thenReturn(true);

        // When
        boolean result = modelService.modelExists(brandId, modelName);

        // Then
        assertTrue(result);
        verify(modelRepository).existsByBrandIdAndName(brandId, modelName);
    }
}
