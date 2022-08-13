package com.example.datastreambackend.services;

import com.example.datastreambackend.dtos.ElectricityProviderDto;

import java.util.List;

public interface ElectricityProviderService {

    List<ElectricityProviderDto> fetchElectricityProviders();
}
