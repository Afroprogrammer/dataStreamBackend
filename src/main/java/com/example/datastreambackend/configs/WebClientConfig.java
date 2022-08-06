package com.example.datastreambackend.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${services.paystack.baseUrl}")
    private String payStackBaseUrl;

    @Value("${services.paystack.apiKey}")
    private String payStackApiKey;

    @Value("${services.datastream.baseUrl}")
    private String dataStreamBaseUrl;

    @Value("${services.datastream.apiKey}")
    private String dataStreamApiKey;


    @Bean(name = "payStack")
    public WebClient payStackClient() {
        return WebClient.builder()
                .baseUrl(payStackBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", payStackApiKey))
                .build();
    }


    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(dataStreamBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("x-api-key", dataStreamApiKey)
                .build();
    }
}