package com.example.datastreambackend.api.services;

import com.example.datastreambackend.api.requests.AirtimeRequest;
import com.example.datastreambackend.api.responses.AirtimeProviderApiResponse;

import java.util.Map;

public interface AirtimeAggregationApiService {
    AirtimeProviderApiResponse fetchAirtimeProviders();

    Map<String, Object> purchaseAirtime(AirtimeRequest airtimeRequest);
}
