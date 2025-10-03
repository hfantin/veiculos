package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.model.Brand;
import com.github.hfantin.veiculos.domain.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    @DisplayName("Deve salvar uma marca com sucesso")
    void shouldSaveBrandSuccessfully() {
        // Given
        Brand brand = new Brand("Toyota");
        Brand savedBrand = Brand.builder()
                .id(1)
                .name("Toyota")
                .createdAt(LocalDateTime.now())
                .build();

        when(brandRepository.save(any(Brand.class))).thenReturn(savedBrand);

        // When
        Brand result = brandService.createBrand(brand);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Toyota", result.getName());
        verify(brandRepository).save(brand);
    }

    @Test
    @DisplayName("Deve encontrar marca por ID")
    void shouldFindBrandById() {
        // Given
        Integer brandId = 1;
        Brand brand = Brand.builder().id(brandId).name("Toyota").build();
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));

        // When
        Optional<Brand> result = brandService.getBrandById(brandId);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Toyota", result.get().getName());
        verify(brandRepository).findById(brandId);
    }

    @Test
    @DisplayName("Deve retornar todas as marcas")
    void shouldFindAllBrands() {
        // Given
        List<Brand> brands = Arrays.asList(
                Brand.builder().id(1).name("Toyota").build(),
                Brand.builder().id(2).name("Honda").build()
        );
        when(brandRepository.findAll()).thenReturn(brands);

        // When
        List<Brand> result = brandService.getAllBrands();

        // Then
        assertEquals(2, result.size());
        verify(brandRepository).findAll();
    }

    @Test
    @DisplayName("Deve atualizar uma marca existente")
    void shouldUpdateBrand() {
        // Given
        Integer brandId = 1;
        Brand existingBrand = Brand.builder()
                .id(brandId)
                .name("Toyota")
                .createdAt(LocalDateTime.now().minusDays(1))
                .build();

        Brand updatedBrand = Brand.builder()
                .id(brandId)
                .name("Toyota Motors")
                .build();

        when(brandRepository.findById(brandId)).thenReturn(Optional.of(existingBrand));
        when(brandRepository.save(any(Brand.class))).thenReturn(updatedBrand);

        // When
        Brand result = brandService.updateBrand(brandId, updatedBrand);

        // Then
        assertNotNull(result);
        assertEquals("Toyota Motors", result.getName());
        verify(brandRepository).findById(brandId);
        verify(brandRepository).save(any(Brand.class));
    }

    @Test
    @DisplayName("Deve deletar uma marca existente")
    void shouldDeleteBrand() {
        // Given
        Integer brandId = 1;
        when(brandRepository.existsById(brandId)).thenReturn(true);

        // When
        brandService.deleteBrand(brandId);

        // Then
        verify(brandRepository).deleteById(brandId);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar marca inexistente")
    void shouldThrowExceptionWhenDeletingNonExistentBrand() {
        // Given
        Integer brandId = 999;
        when(brandRepository.existsById(brandId)).thenReturn(false);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> brandService.deleteBrand(brandId));
        verify(brandRepository, never()).deleteById(brandId);
    }
}
