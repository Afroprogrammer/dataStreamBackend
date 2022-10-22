package com.example.datastreambackend.services.impl;

import com.example.datastreambackend.api.services.PaymentApiService;
import com.example.datastreambackend.constants.TransactionStatus;
import com.example.datastreambackend.constants.TransactionType;
import com.example.datastreambackend.models.AirtimeBillPayment;
import com.example.datastreambackend.models.Transaction;
import com.example.datastreambackend.reponses.CheckoutUrlResponse;
import com.example.datastreambackend.repositories.AirtimeBillPaymentRepository;
import com.example.datastreambackend.repositories.AirtimeOperatorRepository;
import com.example.datastreambackend.repositories.TransactionRepository;
import com.example.datastreambackend.requets.AirtimeBillPaymentRequest;
import com.example.datastreambackend.services.AirtimeBillPaymentService;
import com.example.datastreambackend.utils.AppUtils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor


public class AirtimeBillPaymentServiceImpl implements AirtimeBillPaymentService {
    private final PaymentApiService paymentApiService;

    private final AirtimeBillPaymentRepository airtimeBillPaymentRepository;

//    private final AirtimeOperatorRepository airtimeOperatorRepository;

    private final TransactionRepository transactionRepository;


    private final Gson gson;

    @Override
    public void storeAirtimeBillPayment(AirtimeBillPaymentRequest request, String token) {
        var bill = AirtimeBillPayment.builder()
                .amount(request.getAmount())
                .token(token)
                .build();

        airtimeBillPaymentRepository.save(bill);
    }

    @Override
    public CheckoutUrlResponse initiateCheckout(AirtimeBillPaymentRequest request) {
//        var airtimeProvider = airtimeOperatorRepository.findById(request.getOperatorId())
//                .orElseThrow(() -> new ResourceNotFoundException("Airtime Operator does not exist")); //Might be needed later

        var transactionReference = AppUtils.generateTransactionId(TransactionType.RECHARGE_AIRTIME);

        var response = paymentApiService.checkout(
                transactionReference,
                request.getAmount(),
                gson.toJson(request)

        );

        transactionRepository.save(
                Transaction.builder()
                        .amount(request.getAmount())
                        .transactionId(transactionReference)
                        .transactionType(TransactionType.ELECTRICITY_BILL_PAYMENT)
                        .status(TransactionStatus.PENDING)
                        .build()
        );

        return CheckoutUrlResponse.builder().checkoutUrl(response.getData().getAuthorizationUrl()).build();
    }
}
