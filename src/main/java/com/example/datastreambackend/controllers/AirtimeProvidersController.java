package com.example.datastreambackend.controllers;


import com.example.datastreambackend.dtos.AirtimeOperatorDto;
import com.example.datastreambackend.services.AirtimeBillPaymentService;
import com.example.datastreambackend.services.AirtimeProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airtime_operators")
public class AirtimeProvidersController {
    private final AirtimeProviderService airtimeProviderService;


    @GetMapping
    public List<AirtimeOperatorDto> getAirtimeOperators(){
        return airtimeProviderService.fetchAirtimeProviders();
    }

}
