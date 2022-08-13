package com.example.datastreambackend.services;

import com.example.datastreambackend.models.ElectricityProvider;
import com.example.datastreambackend.repositories.ElectricityProviderRepository;
import com.example.datastreambackend.services.impl.ElectricityProviderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ElectricityProviderServiceImplTest {

    @InjectMocks
    private ElectricityProviderServiceImpl electricityProviderService;

    @Mock
    private ElectricityProviderRepository electricityProviderRepository;

    @Test
    public void testFetchElectricityProviders() {
        var provider = ElectricityProvider.builder()
                .billerId(1)
                .name("test")
                .shortName("test")
                .name("test")
                .serviceType("test_service_type")
                .productId(1)
                .build();

        when(electricityProviderRepository.findAll()).thenReturn(List.of(provider));

        electricityProviderService.fetchElectricityProviders();

        verify(electricityProviderRepository, times(1)).findAll();
    }
}
