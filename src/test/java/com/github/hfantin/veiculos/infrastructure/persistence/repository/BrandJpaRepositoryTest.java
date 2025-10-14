package com.github.hfantin.veiculos.infrastructure.persistence.repository;

import com.github.hfantin.veiculos.infrastructure.persistence.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BrandJpaRepositoryTest {

    @Autowired
    private BrandJpaRepository brandJpaRepository;

    @Test
    public void listAll() {
        List<BrandEntity> all = brandJpaRepository.findAll();
        assertFalse(all.isEmpty());
    }

    @Test
    public void getById() {
        BrandEntity brand = brandJpaRepository.findById(1).orElse(null);
        assertEquals("Toyota", brand.getName());
    }

}