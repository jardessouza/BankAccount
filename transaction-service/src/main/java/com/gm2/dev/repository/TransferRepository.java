package com.gm2.dev.repository;

import com.gm2.dev.entity.Transfer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TransferRepository extends MongoRepository<Transfer, String> {
    @Query("{$or: [{'accountFrom': ?0},{'accountTo':?0}]}")
    List<Transfer> getTransferByAccountFrom(String accountNumber);
}
