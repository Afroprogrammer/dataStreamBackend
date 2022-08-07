package com.example.datastreambackend.services.impl;

import com.example.datastreambackend.api.services.BillerAggregationApiService;
import com.example.datastreambackend.api.services.PaymentApiService;
import com.example.datastreambackend.constants.TransactionType;
import com.example.datastreambackend.exceptions.InvalidMeterAndServiceTypeException;
import com.example.datastreambackend.exceptions.ResourceNotFoundException;
import com.example.datastreambackend.models.Transaction;
import com.example.datastreambackend.reponses.CheckoutUrlResponse;
import com.example.datastreambackend.repositories.ElectricityProviderRepository;
import com.example.datastreambackend.repositories.TransactionRepository;
import com.example.datastreambackend.requets.ElectricityBillPaymentRequest;
import com.example.datastreambackend.services.ElectricityBillPaymentService;
import com.example.datastreambackend.utils.AppUtils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ElectricityBillPaymentServiceImpl implements ElectricityBillPaymentService {

    private final PaymentApiService paymentApiService;

    private final ElectricityProviderRepository electricityProviderRepository;

    private final BillerAggregationApiService billerAggregationApiService;

    private final TransactionRepository transactionRepository;

    private final Gson gson;

    @Override
    public CheckoutUrlResponse initiateCheckout(ElectricityBillPaymentRequest request) {

        var electricityProvider = electricityProviderRepository.findById(request.getOperatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Electricity operator does not exists"));

        var isValid = billerAggregationApiService.isMeterNumberAndServiceTypeValid(request.getMeterNumber(), electricityProvider.getServiceType());

        if (!isValid) {
            throw new InvalidMeterAndServiceTypeException("Sorry could not verify account number");
        }

        var transaction = transactionRepository.save(
                Transaction.builder()
                        .amount(request.getAmount())
                        .transactionId(AppUtils.generateTransactionId(TransactionType.ELECTRICITY_BILL_PAYMENT))
                        .transactionType(TransactionType.ELECTRICITY_BILL_PAYMENT)
                        .build()
        );

        var response = paymentApiService.checkout(
                transaction.getTransactionId(),
                request.getAmount(),
                gson.toJson(request),
                request.getEmail()
        );

        return CheckoutUrlResponse.builder().checkoutUrl(response.getData().getAuthorizationUrl()).build();
    }
}
