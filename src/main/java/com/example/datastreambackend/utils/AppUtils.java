package com.example.datastreambackend.utils;

import com.example.datastreambackend.constants.TransactionType;

import java.util.UUID;

public class AppUtils {

    private AppUtils() {}

    public static String generateTransactionId(TransactionType transactionType) {
        return String.format("%s-%s", transactionType.getCode(), UUID.randomUUID());
    }
}
