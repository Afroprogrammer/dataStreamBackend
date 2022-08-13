package com.example.datastreambackend.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ElectricityRequest {

    @JsonProperty("service_type")
    private String serviceType;

    @JsonProperty("account_number")
    private String accountNumber;

    private Double amount;

    private String metadata;

    private String phone;

    private String agentId;

    private String agentReference;
}
