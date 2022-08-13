package com.example.datastreambackend.repositories;

import com.example.datastreambackend.models.ElectricityProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class ElectricityProviderRepositoryTest {

    @Autowired
    private ElectricityProviderRepository electricityProviderRepository;

    @BeforeEach
    void setUp() {
        electricityProviderRepository.deleteAll();
    }

    @Test
    @DisplayName("find all electricity providers")
    public void testFindAll() {

        assertEquals(0, electricityProviderRepository.findAll().size());

        var provider = ElectricityProvider.builder()
                .billerId(1)
                .name("test")
                .shortName("test")
                .name("test")
                .serviceType("test_service_type")
                .productId(1)
                .build();

        electricityProviderRepository.save(provider);

        assertEquals(1, electricityProviderRepository.findAll().size());

    }

    @Test
    @DisplayName("save electricity provider")
    public void testSave() {

        var provider = ElectricityProvider.builder()
                .billerId(1)
                .name("test")
                .shortName("test")
                .name("test")
                .serviceType("test_service_type")
                .productId(1)
                .build();

        var savedProvider = electricityProviderRepository.save(provider);

        assertEquals(provider.getName(), savedProvider.getName());

        assertNotNull(savedProvider.getId());

    }
}
