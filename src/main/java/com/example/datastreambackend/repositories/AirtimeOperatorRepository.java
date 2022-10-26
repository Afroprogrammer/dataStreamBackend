package com.example.datastreambackend.repositories;

import com.example.datastreambackend.models.AirtimeProvider;
import com.example.datastreambackend.models.ElectricityProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AirtimeOperatorRepository  extends JpaRepository<AirtimeProvider,UUID > {
    Optional<AirtimeProvider> findByProductIdAndBillerId(int productId, int billerId);

}
