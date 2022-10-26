package com.example.datastreambackend.services;

import com.example.datastreambackend.dtos.AirtimeOperatorDto;
import com.example.datastreambackend.reponses.CheckoutUrlResponse;
import com.example.datastreambackend.requets.AirtimeBillPaymentRequest;
import com.example.datastreambackend.requets.ElectricityBillPaymentRequest;

import java.util.List;

public interface AirtimeBillPaymentService {

    void storeAirtimeBillPayment(AirtimeBillPaymentRequest request, String token);

    CheckoutUrlResponse initiateCheckout(AirtimeBillPaymentRequest request);


}
