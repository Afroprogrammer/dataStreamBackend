package com.example.datastreambackend.repositories;

import com.example.datastreambackend.models.ElectricityBillPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ElectricityBillPaymentRepository extends JpaRepository<ElectricityBillPayment, UUID> {

}
