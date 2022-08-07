package com.example.datastreambackend.services;

import com.example.datastreambackend.reponses.CheckoutUrlResponse;
import com.example.datastreambackend.requets.ElectricityBillPaymentRequest;

public interface  ElectricityBillPaymentService {

    CheckoutUrlResponse initiateCheckout(ElectricityBillPaymentRequest request);
}
