package com.example.datastreambackend.schedulers;

import com.example.datastreambackend.api.responses.AirtimeProviderApiResponse;
import com.example.datastreambackend.api.services.AirtimeAggregationApiService;
import com.example.datastreambackend.mappers.AirtimeProviderMapper;
import com.example.datastreambackend.models.AirtimeProvider;
import com.example.datastreambackend.models.ElectricityProvider;
import com.example.datastreambackend.repositories.AirtimeOperatorRepository;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class AirtimeProviderScheduler {
  private final AirtimeAggregationApiService airtimeAggregationApiService;

  private final AirtimeOperatorRepository airtimeOperatorRepository;

  public void fetchAirtimeProviders(){

      AirtimeProviderApiResponse airtimeProviderApiResponse =  airtimeAggregationApiService.fetchAirtimeProviders();

      if (airtimeProviderApiResponse == null){
          log.error("airtime provider response is null");
          return;
      }
      if (airtimeProviderApiResponse.getCode() == HttpStatus.OK.value() && airtimeProviderApiResponse.getStatus().equalsIgnoreCase("success")){
          log.info("providers: {}", airtimeProviderApiResponse.getData());

          List<AirtimeProvider> providers = airtimeProviderApiResponse.getData()
                .stream()
                .map(AirtimeProviderMapper::mapToAirtimeProvider)
                .collect(Collectors.toList());

          for( AirtimeProvider provider : providers ){

              airtimeOperatorRepository
                      .findByProductIdAndBillerId(provider.getProductId(),  provider.getBillerId())
                      .ifPresent(existingProvider -> provider.setId(existingProvider.getId()));
              airtimeOperatorRepository.save(provider);

          }
      }
      log.info("fetched providers successfully ");
  }


}

