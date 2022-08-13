package com.example.datastreambackend.schedulers;

import com.example.datastreambackend.api.services.BillerAggregationApiService;
import com.example.datastreambackend.mappers.ElectricityProviderMapper;
import com.example.datastreambackend.repositories.ElectricityProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProvidersScheduler {

    private final BillerAggregationApiService electricityProviderService;
    private final ElectricityProviderRepository electricityProviderRepository;

    @Scheduled(fixedDelayString = "${schedulers.electricity.fixedDelay}", initialDelayString = "${schedulers.electricity.initialDelay}")
    public void fetchElectricityProviders() {

        log.info("fetching electricity providers");

        var response = electricityProviderService.fetchElectricityProviders();

        if (response == null) {
            log.error("electricity provider response is null");
            return;
        }

        if (response.getCode() == HttpStatus.OK.value() && response.getStatus().equalsIgnoreCase("success")) {

            log.info("providers: {}", response.getData());

            var providers = response.getData()
                    .stream()
                    .map(ElectricityProviderMapper::mapToElectricityProvider)
                    .collect(Collectors.toList());

            for (var provider : providers) {

                electricityProviderRepository
                        .findByProductIdAndBillerId(provider.getProductId(), provider.getBillerId())
                        .ifPresent(existingProvider -> provider.setId(existingProvider.getId()));

                electricityProviderRepository.save(provider);
            }

        }
        log.info("fetched electricity providers");

    }
}
