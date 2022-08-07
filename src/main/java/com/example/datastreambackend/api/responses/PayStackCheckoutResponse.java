package com.example.datastreambackend.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayStackCheckoutResponse {

    private boolean status;
    private String message;
    private Data data;

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Data {

        @JsonProperty("authorization_url")
        private String authorizationUrl;

        @JsonProperty("access_code")
        private String accessCode;

        private String reference;


    }
}
