package com.example.datastreambackend.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "electricity_bill_payments")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ElectricityBillPayment {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    @Column(name = "meter_number", nullable = false)
    private String meterNumber;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String token;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
