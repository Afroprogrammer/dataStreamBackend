package com.example.datastreambackend.repositories;

import com.example.datastreambackend.constants.TransactionStatus;
import com.example.datastreambackend.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Optional<Transaction> findByTransactionIdAndStatus(UUID transactionId, TransactionStatus status);

}
