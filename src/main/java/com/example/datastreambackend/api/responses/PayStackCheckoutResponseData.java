package com.example.datastreambackend.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayStackCheckoutResponseData {

    @JsonProperty("authorization_url")
    private String authorizationUrl;

    @JsonProperty("access_code")
    private String accessCode;

    private String reference;
}
