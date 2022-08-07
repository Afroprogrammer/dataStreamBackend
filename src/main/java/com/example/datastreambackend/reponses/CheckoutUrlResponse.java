package com.example.datastreambackend.reponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutUrlResponse {

    @JsonProperty("checkout_url")
    private String checkoutUrl;
}
