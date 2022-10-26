package com.example.datastreambackend.mappers;

import com.example.datastreambackend.api.responses.AirtimeProviderItem;
import com.example.datastreambackend.dtos.AirtimeOperatorDto;
import com.example.datastreambackend.models.AirtimeProvider;

public class AirtimeProviderMapper {

    public static AirtimeOperatorDto mapToAirtimeProviderDto(AirtimeProvider airtimeProvider) {
        return AirtimeOperatorDto.builder()
                .name(airtimeProvider.getName())
                .shortName(airtimeProvider.getShortName())
                .id(airtimeProvider.getId())
                .build();
    }

    public static AirtimeProvider mapToAirtimeProvider(AirtimeProviderItem airtimeProviderItem) {

        return AirtimeProvider.builder()
                .billerId(airtimeProviderItem.getBillerId())
                .name(airtimeProviderItem.getName())
                .shortName(airtimeProviderItem.getShortName())
                .productId(airtimeProviderItem.getProductId())
                .serviceType(airtimeProviderItem.getServiceType())
                .build();
    }
}
