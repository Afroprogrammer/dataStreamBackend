package com.example.datastreambackend.mappers;

import com.example.datastreambackend.api.responses.ElectricityProviderItem;
import com.example.datastreambackend.dtos.ElectricityProviderDto;
import com.example.datastreambackend.models.ElectricityProvider;

public class ElectricityProviderMapper {

    public static ElectricityProviderDto mapToElectricityProviderDto(ElectricityProvider electricityProvider) {
        return ElectricityProviderDto.builder()
                .name(electricityProvider.getName())
                .shortName(electricityProvider.getShortName())
                .id(electricityProvider.getId())
                .build();
    }

    public static ElectricityProvider mapToElectricityProvider(ElectricityProviderItem electricityProviderItem) {
        return ElectricityProvider.builder()
                .billerId(electricityProviderItem.getBillerId())
                .name(electricityProviderItem.getName())
                .shortName(electricityProviderItem.getShortName())
                .productId(electricityProviderItem.getProductId())
                .serviceType(electricityProviderItem.getServiceType())
                .build();
    }
}
