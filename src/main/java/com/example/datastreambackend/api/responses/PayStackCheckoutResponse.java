package com.example.datastreambackend.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayStackCheckoutResponse {

    private boolean status;
    private String message;
    private PayStackCheckoutResponseData data;

}
