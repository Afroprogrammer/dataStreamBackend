package com.example.datastreambackend.api.services.impl;

import com.example.datastreambackend.api.responses.ElectricityProviderApiResponse;
import com.example.datastreambackend.api.services.BillerAggregationApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillerAggregationApiServiceImpl implements BillerAggregationApiService {

    private final WebClient webClient;

    @Override
    public ElectricityProviderApiResponse fetchElectricityProviders() {
        return webClient.get()
                .uri("/services/electricity/billers")
                .retrieve()
                .bodyToMono(ElectricityProviderApiResponse.class)
                .doOnError(throwable -> log.info("Error fetching electricity providers: {}", throwable.getMessage()))
                .block();
    }
}
