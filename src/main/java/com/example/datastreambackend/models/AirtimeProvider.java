package com.example.datastreambackend.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "airtime_operator")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AirtimeProvider {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    @Column(name = "service_type", nullable = false)
    private String serviceType;

    @Column(name = "shortname", nullable = false)
    private String shortName;

    @Column(nullable = false)
    private String name;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "biller_id", nullable = false)
    private Integer billerId;

}
