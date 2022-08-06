package com.example.datastreambackend.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectricityProviderApiResponse {

    private String status;
    private String message;
    private Integer code;
    private List<ElectricityProviderItem> data;

}
