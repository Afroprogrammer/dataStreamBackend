package com.example.datastreambackend.services.impl;

import com.example.datastreambackend.dtos.ElectricityProviderDto;
import com.example.datastreambackend.mappers.ElectricityProviderMapper;
import com.example.datastreambackend.repositories.ElectricityProviderRepository;
import com.example.datastreambackend.services.ElectricityProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ElectricityProviderServiceImpl implements ElectricityProviderService {

    private final ElectricityProviderRepository electricityProviderRepository;

    @Override
    public List<ElectricityProviderDto> fetchElectricityProviders() {
        return electricityProviderRepository.findAll()
                .stream()
                .map(ElectricityProviderMapper::mapToElectricityProviderDto)
                .collect(Collectors.toList());
    }
}
