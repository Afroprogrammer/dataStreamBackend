package com.example.datastreambackend.api.services;

import com.example.datastreambackend.api.responses.ElectricityProviderApiResponse;

public interface BillerAggregationApiService {

    ElectricityProviderApiResponse fetchElectricityProviders();
}