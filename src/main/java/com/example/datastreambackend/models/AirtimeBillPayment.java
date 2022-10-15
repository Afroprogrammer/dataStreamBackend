package com.example.datastreambackend.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AirtimeBillPayment {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    @Column(name = "phone_number", nullable = false)
    private String phone;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String plan;

    @Column(nullable = false)
    private String token;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
