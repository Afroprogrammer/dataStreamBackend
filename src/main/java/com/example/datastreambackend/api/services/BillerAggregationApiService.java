package com.example.datastreambackend.api.services;

import com.example.datastreambackend.api.requests.ElectricityRequest;
import com.example.datastreambackend.api.responses.ElectricityProviderApiResponse;

import java.util.Map;

public interface BillerAggregationApiService {

    ElectricityProviderApiResponse fetchElectricityProviders();

    Boolean isMeterNumberAndServiceTypeValid(String meterNumber, String serviceType);

    Map<String, Object> purchaseElectricity(ElectricityRequest electricityRequest);
}
