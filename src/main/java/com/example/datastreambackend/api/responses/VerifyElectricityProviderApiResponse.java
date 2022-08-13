package com.example.datastreambackend.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyElectricityProviderApiResponse {

    private String status;
    private String message;
    private Integer code;
    private Data data;

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Data {
        private User user;
    }

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        private String name;
        private String address;
        private String accountNumber;
        private Double minimumAmount;
    }

}
