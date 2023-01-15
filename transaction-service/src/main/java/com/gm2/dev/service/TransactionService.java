package com.gm2.dev.service;

import com.gm2.dev.dto.AccountStatementDTO;
import com.gm2.dev.dto.TransactionResponseDTO;
import com.gm2.dev.entity.Transaction;
import com.gm2.dev.mapper.TransactionMapper;
import com.gm2.dev.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class  TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountValidationService accountValidationService;

    private final TransferService transferService;



    public TransactionService(TransactionRepository transactionRepository,
                              AccountValidationService accountValidationService,
                              TransferService transferService) {
        this.transactionRepository = transactionRepository;
        this.accountValidationService = accountValidationService;
        this.transferService = transferService;
    }

    public TransactionResponseDTO save(TransactionResponseDTO transactionResponseDTO) {
        accountValidationService.verifyAccount(transactionResponseDTO.getAccountNumber());
        Transaction transactionCreated = getAndSaveTransaction(transactionResponseDTO);

        return TransactionMapper.INSTANCE.toDTOResponse(transactionCreated);
    }



    public AccountStatementDTO getAccountStatement(String accountNumber) {
        AccountStatementDTO transactionsResponse = new AccountStatementDTO();
        transactionsResponse.setAccountNumber(accountNumber);
        transactionsResponse.setTransactions(getAllTransactions(accountNumber));

        transactionsResponse.calculateBalance();

        return transactionsResponse;
    }

    private Transaction getAndSaveTransaction(TransactionResponseDTO transactionResponseDTO) {
        Transaction transactionToCreate = TransactionMapper.INSTANCE.toModel(transactionResponseDTO);
        transactionToCreate.setDate(LocalDateTime.now());
        return this.transactionRepository.save(transactionToCreate);
    }

    private List<TransactionResponseDTO> getAllTransactions(String accountNumber) {
        List<TransactionResponseDTO> transactions = new ArrayList<>();

        transactions.addAll(getTransactions(accountNumber));
        transactions.addAll(getTransfers(accountNumber));

        return transactions;
    }

    private List<TransactionResponseDTO> getTransfers(String accountNumber) {
        return this.transferService.getAll(accountNumber).stream()
                .map(TransactionMapper.INSTANCE::toDTOResponse)
                .collect(Collectors.toList());
    }

    private List<TransactionResponseDTO> getTransactions(String accountNumber) {
        List<Transaction> transactionByAccountNumber =
                this.transactionRepository.getTransactionByAccountNumber(accountNumber);

        return transactionByAccountNumber.stream().map(TransactionMapper.INSTANCE::toDTOResponse)
                .collect(Collectors.toList());
    }
}
