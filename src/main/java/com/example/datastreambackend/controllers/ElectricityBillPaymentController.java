package com.example.datastreambackend.controllers;

import com.example.datastreambackend.reponses.CheckoutUrlResponse;
import com.example.datastreambackend.requets.ElectricityBillPaymentRequest;
import com.example.datastreambackend.services.ElectricityBillPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/electricity_bill_payments")
@RequiredArgsConstructor
@CrossOrigin
public class ElectricityBillPaymentController {

    private final ElectricityBillPaymentService electricityBillPaymentService;

    @PostMapping("/checkout")
    public CheckoutUrlResponse checkout(@Valid @RequestBody ElectricityBillPaymentRequest request) {
        return electricityBillPaymentService.initiateCheckout(request);
    }
}
