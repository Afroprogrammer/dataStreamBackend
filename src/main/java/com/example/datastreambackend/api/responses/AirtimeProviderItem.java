package com.example.datastreambackend.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AirtimeProviderItem {

    @JsonProperty("service_type")
    private String serviceType;
    @JsonProperty("shortname")
    private String shortName;
    @JsonProperty("biller_id")
    private Integer billerId;
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("name")
    private String name;
}
