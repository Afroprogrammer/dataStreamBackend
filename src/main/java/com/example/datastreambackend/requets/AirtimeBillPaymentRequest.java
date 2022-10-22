package com.example.datastreambackend.requets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirtimeBillPaymentRequest {

    @NotBlank(message = "phone is required")
    private String phone;

    @NotNull(message = "operator is required")
    private UUID operatorId;

    @NotNull(message = "amount is required")
    private Double amount;

    @NotNull(message = "plan is required")
    private String plan;

    private Boolean useWalletBalance;
}
