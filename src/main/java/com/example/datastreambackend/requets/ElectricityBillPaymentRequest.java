package com.example.datastreambackend.requets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectricityBillPaymentRequest {

    @NotBlank(message = "meter number is required")
    private String meterNumber;

    @NotNull(message = "operator is required")
    private UUID operatorId;

    @Min(value = 500, message = "amount must be at least 500")
    @NotNull(message = "amount is required")
    private Double amount;

    @NotBlank(message = "phone is required")
    private String phone;

    @NotBlank(message = "email is required")
    @Email(message = "email is invalid")
    private String email;

    private Boolean useWalletBalance;
}
