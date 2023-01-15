package com.gm2.dev.controller;

import com.gm2.dev.dto.AccountStatementDTO;
import com.gm2.dev.dto.TransactionResponseDTO;
import com.gm2.dev.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody TransactionResponseDTO transactionResponseDTO){
        return new ResponseEntity<>(this.transactionService.save(transactionResponseDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/{accountNumber}")
    public ResponseEntity<AccountStatementDTO> statement(@PathVariable String accountNumber){
        return ResponseEntity.ok(this.transactionService.getAccountStatement(accountNumber));
    }
}
