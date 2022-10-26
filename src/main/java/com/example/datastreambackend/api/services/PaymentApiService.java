package com.example.datastreambackend.api.services;

import com.example.datastreambackend.api.responses.PayStackCheckoutResponse;

public interface PaymentApiService {

    PayStackCheckoutResponse checkout(String reference, Double amount, String metaData, String email);
    PayStackCheckoutResponse checkout(String reference, Double amount, String metaData);


}
