package com.example.datastreambackend.constants;

public enum TransactionType {

    ELECTRICITY_BILL_PAYMENT("EBP"), CABLE_TV_BILL_PAYMENT("CTB"),
    RECHARGE_AIRTIME("RA");

    private final String code;

    TransactionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
