package com.github.hfantin.veiculos.infrastructure.persistence.repository;

import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleVehicleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SaleVehicleJpaRepositoryTest {

    @Autowired
    private SaleVehicleJpaRepository saleVehicleJpaRepository;

    @Test
    public void findAllByCustomeId() {
        List<SaleVehicleEntity> all = saleVehicleJpaRepository.findAllByCustomeId("auth0|123456789");
        assertFalse(all.isEmpty());
    }

    @Test
    public void existsNotCancelledByVehicleId() {
        Optional<SaleVehicleEntity> saleVehicleEntity = saleVehicleJpaRepository.existsNotCancelledByVehicleId(1);
        assertEquals(1, saleVehicleEntity.get().getId());
    }
}