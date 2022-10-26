package com.example.datastreambackend.services;

import com.example.datastreambackend.dtos.AirtimeOperatorDto;

import java.util.List;

public interface AirtimeProviderService {

    List<AirtimeOperatorDto> fetchAirtimeProviders();
}
