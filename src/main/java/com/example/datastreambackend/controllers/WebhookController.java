package com.example.datastreambackend.controllers;

import com.example.datastreambackend.requets.WebhookRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WebhookController {

    public void webhook(@RequestBody WebhookRequest body) {

    }
}
