package com.example.datastreambackend.api.services;

import com.example.datastreambackend.api.responses.PayStackCheckoutResponse;

public interface AirtimePaymentApiService {

    PayStackCheckoutResponse checkout(String reference, Double amount, String metaData);

}
