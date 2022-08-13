package com.example.datastreambackend.exceptions;

public class VerifyElectricityProviderException extends RuntimeException {
    private final Integer code;

    public VerifyElectricityProviderException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

