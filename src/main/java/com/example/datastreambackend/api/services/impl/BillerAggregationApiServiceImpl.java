package com.example.datastreambackend.api.services.impl;

import com.example.datastreambackend.api.responses.ElectricityProviderApiResponse;
import com.example.datastreambackend.api.responses.VerifyElectricityProviderApiResponse;
import com.example.datastreambackend.api.services.BillerAggregationApiService;
import com.example.datastreambackend.exceptions.VerifyElectricityProviderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

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

    @Override
    public Boolean isMeterNumberAndServiceTypeValid(String meterNumber, String serviceType) {
        log.info("verifying meter number: {} and service type: {}", meterNumber, serviceType);

        try {

            var response = webClient.post()
                    .uri("/services/electricity/verify")
                    .body(Mono.just(Map.of("account_number", meterNumber, "service_type", serviceType)), Map.class)
                    .retrieve()
                    .onStatus(
                            status -> status.value() == HttpStatus.BAD_REQUEST.value(),
                            error -> {
                                var errorResponse = error.bodyToMono(Map.class);
                                log.error("error {}", errorResponse);
                                return Mono.error(new VerifyElectricityProviderException("something expected happened", error.rawStatusCode()));
                            }
                    )
                    .bodyToMono(VerifyElectricityProviderApiResponse.class)
                    .block();

            return response != null && response.getStatus().equals("success");

        }catch (VerifyElectricityProviderException e) {
            throw new VerifyElectricityProviderException(e.getMessage(), e.getCode());
        }


    }
}
