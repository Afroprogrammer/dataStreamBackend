package com.example.datastreambackend.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirtimeDataResponse {
    private String statusCode;
    private String transactionStatus;
    private String transactionReference;
    private String transactionMessage;
    private String baxiReference;
    private String provider_message;
}
