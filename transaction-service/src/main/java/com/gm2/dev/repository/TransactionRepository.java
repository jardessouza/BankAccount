package com.gm2.dev.repository;

import com.gm2.dev.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> getTransactionByAccountNumber(String accountFrom);
}
