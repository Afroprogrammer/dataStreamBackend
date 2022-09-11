package com.example.datastreambackend.api.services.impl;

import com.example.datastreambackend.api.requests.AirtimeRequest;
import com.example.datastreambackend.api.responses.AirtimeProviderApiResponse;
import com.example.datastreambackend.api.responses.ElectricityProviderApiResponse;
import com.example.datastreambackend.api.services.AirtimeAggregationApiService;
import com.example.datastreambackend.exceptions.AirtimePurchaseException;
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
public class AirtimeAggregationApiServiceImpl implements AirtimeAggregationApiService {
      private final WebClient webClient;
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
