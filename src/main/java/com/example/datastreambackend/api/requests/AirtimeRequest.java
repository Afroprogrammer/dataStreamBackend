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
public class AirtimeRequest {
    @JsonProperty("service_type")
    private String serviceType;
    private String phone;
    private String agentId;
    private Double amount;
    private String agentReference;
    private String prepaid;
    
}
