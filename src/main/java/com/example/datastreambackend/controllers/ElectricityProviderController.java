package com.example.datastreambackend.controllers;

import com.example.datastreambackend.dtos.ElectricityProviderDto;
import com.example.datastreambackend.services.ElectricityProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/electricity_providers")
@RequiredArgsConstructor
public class ElectricityProviderController {

    private final ElectricityProviderService electricityProviderService;

    @GetMapping
    public List<ElectricityProviderDto> getElectricityProviders() {
        return electricityProviderService.fetchElectricityProviders();
    }
}
