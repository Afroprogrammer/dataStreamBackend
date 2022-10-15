package com.example.datastreambackend.dtos;

import com.example.datastreambackend.api.responses.AirtimeProviderItem;
import com.example.datastreambackend.models.AirtimeProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirtimeProviderProperties {
    private List<AirtimeProviderItem> providers ;
}
