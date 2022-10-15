package com.example.datastreambackend.repositories;

import com.example.datastreambackend.models.AirtimeBillPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirtimeBillPaymentRepository extends JpaRepository<AirtimeBillPayment, UUID> {


}
