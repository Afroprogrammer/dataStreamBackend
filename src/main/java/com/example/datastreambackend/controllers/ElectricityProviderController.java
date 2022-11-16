package com.example.datastreambackend.controllers;

import com.example.datastreambackend.api.services.BillerAggregationApiService;
import com.example.datastreambackend.dtos.ElectricityProviderDto;
import com.example.datastreambackend.services.ElectricityProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ElectricityProviderController {

    private final ElectricityProviderService electricityProviderService;
    private final BillerAggregationApiService billerAggregationApiService;

    @GetMapping("/electricity_providers")
    public List<ElectricityProviderDto> getElectricityProviders() {
        return electricityProviderService.fetchElectricityProviders();
    }
    @GetMapping("/meter_number_validity")
    public boolean getMeterNumberValidation(@RequestParam String meterNumber, @RequestParam String serviceType){
      return   billerAggregationApiService.isMeterNumberAndServiceTypeValid(meterNumber, serviceType);
    }
}
