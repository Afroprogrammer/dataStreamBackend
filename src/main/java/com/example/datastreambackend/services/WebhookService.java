package com.example.datastreambackend.services;

import com.example.datastreambackend.requets.WebhookRequest;

public interface WebhookService {

    void processWebhook(WebhookRequest webhookRequest);
}
