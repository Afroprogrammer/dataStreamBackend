package com.example.datastreambackend.api.services.impl;

import com.example.datastreambackend.api.requests.AirtimeRequest;
import com.example.datastreambackend.api.requests.ElectricityRequest;
import com.example.datastreambackend.api.responses.AirtimeProviderApiResponse;
import com.example.datastreambackend.api.responses.ElectricityProviderApiResponse;
import com.example.datastreambackend.api.responses.VerifyElectricityProviderApiResponse;
import com.example.datastreambackend.api.services.BillerAggregationApiService;
import com.example.datastreambackend.exceptions.AirtimePurchaseException;
import com.example.datastreambackend.exceptions.ElectricityPurchaseException;
import com.example.datastreambackend.exceptions.VerifyElectricityProviderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
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
    public AirtimeProviderApiResponse fetchAirtimeProviders() {
        return webClient.get()
                .uri("/services/airtime/providers")
                .retrieve()
                .bodyToMono(AirtimeProviderApiResponse.class)
                .doOnError(throwable -> log.info("Error fetching airtime providers: {}", throwable.getMessage()))
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

        } catch (VerifyElectricityProviderException e) {
            throw new VerifyElectricityProviderException(e.getMessage(), e.getCode());
        }


    }

    @Override
    public Map<String, Object> purchaseElectricity(ElectricityRequest electricityRequest) {
        log.info("sending electricity purchase request: {}", electricityRequest);

        try {

            return webClient.post()
                    .uri("/services/electricity/request")
                    .body(Mono.just(electricityRequest), ElectricityRequest.class)
                    .retrieve()
                    .onStatus(
                            status -> status.value() == HttpStatus.BAD_REQUEST.value(),
                            error -> {
                                var errorResponse = error.bodyToMono(String.class);
                                return Mono.error(new ElectricityPurchaseException(String.format("electricity purchase bad request %s", errorResponse)));
                            }
                    )
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                    })
                    .block();


        } catch (ElectricityPurchaseException e) {
            throw new ElectricityPurchaseException(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> purchaseAirtime(AirtimeRequest airtimeRequest) {
        log.info("sending airtime purchase request; {}", airtimeRequest);
        try{
            return webClient.post()
                    .uri("services/airtime/request")
                    .body(Mono.just(airtimeRequest), AirtimeRequest.class)
                    .retrieve()
                    .onStatus(
                            status -> status.value() == HttpStatus.BAD_REQUEST.value(),
                            error -> {
                                var errorResponse = error.bodyToMono(String.class);
                                return Mono.error(new AirtimePurchaseException(String.format("airtime purchase bad request %s", errorResponse)));
                            }
                    )
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                    }).block();
        } catch (AirtimePurchaseException e){
            throw new AirtimePurchaseException(e.getMessage());
        }

    }
}
