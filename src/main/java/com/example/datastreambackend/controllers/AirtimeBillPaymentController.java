package com.example.datastreambackend.controllers;

import com.example.datastreambackend.api.services.PaymentApiService;
import com.example.datastreambackend.reponses.CheckoutUrlResponse;
import com.example.datastreambackend.requets.AirtimeBillPaymentRequest;
import com.example.datastreambackend.services.AirtimeBillPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/airtime_bill_payment")
@RequiredArgsConstructor
public class AirtimeBillPaymentController {
    private final AirtimeBillPaymentService airtimeBillPaymentService;

    @PostMapping("/checkout")
    public CheckoutUrlResponse checkoutUrlResponse(@Valid @RequestBody AirtimeBillPaymentRequest request){
        return  airtimeBillPaymentService.initiateCheckout(request);
    }



}
