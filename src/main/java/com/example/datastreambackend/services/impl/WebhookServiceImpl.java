package com.example.datastreambackend.services.impl;

import com.example.datastreambackend.api.requests.AirtimeRequest;
import com.example.datastreambackend.api.requests.ElectricityRequest;
import com.example.datastreambackend.api.services.BillerAggregationApiService;
import com.example.datastreambackend.constants.TransactionStatus;
import com.example.datastreambackend.constants.TransactionType;
import com.example.datastreambackend.exceptions.AirtimePurchaseException;
import com.example.datastreambackend.exceptions.ElectricityPurchaseException;
import com.example.datastreambackend.models.Transaction;
import com.example.datastreambackend.repositories.TransactionRepository;
import com.example.datastreambackend.requets.AirtimeBillPaymentRequest;
import com.example.datastreambackend.requets.ElectricityBillPaymentRequest;
import com.example.datastreambackend.requets.WebhookRequest;
import com.example.datastreambackend.services.AirtimeBillPaymentService;
import com.example.datastreambackend.services.ElectricityBillPaymentService;
import com.example.datastreambackend.services.WebhookService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class WebhookServiceImpl implements WebhookService {

    private final TransactionRepository transactionRepository;

    private final BillerAggregationApiService billerAggregationApiService;

    private final ElectricityBillPaymentService electricityBillPaymentService;

    private final AirtimeBillPaymentService airtimeBillPaymentService;

    private final Gson gson;


    @Override
    public void processWebhook(WebhookRequest webhookRequest) {

        var event = webhookRequest.getEvent();

        if (event != null && event.equals("charge.success")) {

            var data = webhookRequest.getData();

            var transactionReference = data.getOrDefault("reference", "").toString();

            if (transactionReference.isEmpty()) {
                return;
            }

            var transaction = transactionRepository
                    .findByTransactionIdAndStatus(UUID.fromString(transactionReference), TransactionStatus.PENDING);

            if (transaction.isEmpty()) {
                return;
            }

            var transactionEntity = transaction.get();

            var metaData = (HashMap<String, Object>) data.get("metadata");

            if (transactionEntity.getTransactionType() == TransactionType.ELECTRICITY_BILL_PAYMENT) {

                sendElectricityPurchaseRequest(transactionEntity, gson.fromJson(metaData.get("info").toString(), ElectricityBillPaymentRequest.class));

            }
            if (transactionEntity.getTransactionType() == TransactionType.RECHARGE_AIRTIME) {

                sendAirtimePurchaseRequest(transactionEntity, gson.fromJson(metaData.get("info").toString(), AirtimeBillPaymentRequest.class));

            }

        }

    }

    private void sendElectricityPurchaseRequest(Transaction transaction, ElectricityBillPaymentRequest request) {

        var electricityRequest = ElectricityRequest.builder()
                .accountNumber(request.getMeterNumber())
                .agentReference(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .serviceType("")
                .phone(request.getPhone())
                .metadata("")
                .agentId("") //@TODO: fix agent id
                .build();

        try {
            var response = billerAggregationApiService.purchaseElectricity(electricityRequest);

            //@TODO: record bill payment based on response

            electricityBillPaymentService.storeElectricityBillPayment(request, "");

        } catch (ElectricityPurchaseException e) {
            log.info("Error while purchasing electricity: {}", e.getMessage());
        }
    }

    private void sendAirtimePurchaseRequest(Transaction transaction, AirtimeBillPaymentRequest airtimeBillPaymentRequest) {
        var airtimeRequest = AirtimeRequest.builder()
                .phone(airtimeBillPaymentRequest.getPhone())
                .amount(transaction.getAmount())
                .agentId("")   //@TODO FIX THE AGENT ID
                .agentReference(transaction.getTransactionId())  //@TODO FIX THE REFRENCE ID
                .plan(airtimeBillPaymentRequest.getPlan())
                .serviceType("")  //@TODO FIX THE SERVICE TYPE
                .build();

        try {
            var response = billerAggregationApiService.purchaseAirtime(airtimeRequest);
            airtimeBillPaymentService.storeAirtimeBillPayment(airtimeBillPaymentRequest, "");
        } catch (AirtimePurchaseException e) {
            log.info(" Error whiles purchasing Airtime: {}", e.getMessage());
        }

    }

}

