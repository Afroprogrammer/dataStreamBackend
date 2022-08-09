package com.example.datastreambackend.repositories;

import com.example.datastreambackend.models.ElectricityProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ElectricityProviderRepository extends JpaRepository<ElectricityProvider, UUID> {

    Optional<ElectricityProvider> findByProductIdAndBillerId(int productId, int billerId);
}
