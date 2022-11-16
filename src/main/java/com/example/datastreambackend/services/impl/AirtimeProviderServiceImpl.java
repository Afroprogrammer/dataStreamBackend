package com.example.datastreambackend.services.impl;

import com.example.datastreambackend.dtos.AirtimeOperatorDto;
import com.example.datastreambackend.mappers.AirtimeProviderMapper;
import com.example.datastreambackend.repositories.AirtimeOperatorRepository;
import com.example.datastreambackend.services.AirtimeProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AirtimeProviderServiceImpl implements AirtimeProviderService {

  private final AirtimeOperatorRepository airtimeOperatorRepository;
    @Override
    public List<AirtimeOperatorDto> fetchAirtimeProviders() {
        return airtimeOperatorRepository.findAll()
                .stream()
                .map(AirtimeProviderMapper::mapToAirtimeProviderDto)
                .collect(Collectors.toList());
    }
}
