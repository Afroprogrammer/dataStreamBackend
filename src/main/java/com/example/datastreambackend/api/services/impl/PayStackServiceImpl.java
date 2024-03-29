package com.example.datastreambackend.api.services.impl;

import com.example.datastreambackend.api.responses.PayStackCheckoutResponse;
import com.example.datastreambackend.api.services.PaymentApiService;
import com.example.datastreambackend.exceptions.PayStackCheckoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PayStackServiceImpl implements PaymentApiService {

    private final WebClient webClient;

    @Value("${services.paystack.callbackUrl}")
    private String callbackUrl;

    public PayStackServiceImpl(@Qualifier("payStack") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public PayStackCheckoutResponse checkout(String reference, Double amount, String metaData, String email) {
        var body = Map.of(
                "reference", reference,
                "amount", amount * 100,
                "email", email,
                "callback_url", callbackUrl,
                "currency", "GHS",
                "metadata", Map.of("info", metaData),
                "channels", List.of("bank", "card", "ussd", "mobile_money")
        );

        return sendRequest(body);
    }

    @Override
    public PayStackCheckoutResponse checkout(String reference, Double amount, String metaData) {
        var body = Map.of(
                "reference", reference,
                "amount", amount * 100,
                "callback_url", callbackUrl,
                "currency", "GHS",
                "email", "neemacomnetwork@gmail.com",
                "metadata", Map.of("info", metaData),
                "channels", List.of("bank", "card", "ussd", "mobile_money")
        );

        return sendRequest(body);
    }

    private PayStackCheckoutResponse sendRequest(Map<String, Object> body) {
        try {
            var response =  webClient.post()
                    .uri("/transaction/initialize")
                    .body(Mono.just(body), Map.class)
                    .retrieve()
                    .onStatus(status -> status.value() == HttpStatus.UNAUTHORIZED.value(),
                            error -> Mono.error(new PayStackCheckoutException("Unauthorized, invalid key"))
                    )
                    .onStatus(status -> status.value() == HttpStatus.BAD_REQUEST.value(),
                            error -> Mono.error(new PayStackCheckoutException("Bad request, invalid parameters"))
                    )
                    .bodyToMono(PayStackCheckoutResponse.class)
                    .block();

            log.info("paystack response: {}", response);

            if (response == null) {
                throw new PayStackCheckoutException("Paystack response is null");
            }

            if (!response.isStatus()) {
                throw new PayStackCheckoutException(response.getMessage());
            }

            return response;

        }catch (PayStackCheckoutException e) {
            log.error("Error sending request to paystack: {}", e.getMessage());
            throw e;
        }


    }
}
