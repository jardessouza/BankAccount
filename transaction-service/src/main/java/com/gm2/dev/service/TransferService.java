package com.gm2.dev.service;

import com.gm2.dev.dto.AccountStatementDTO;
import com.gm2.dev.dto.MessageDTO;
import com.gm2.dev.dto.TransferResponseDTO;
import com.gm2.dev.mapper.TransferMapper;
import com.gm2.dev.repository.TransferRepository;
import com.gm2.dev.util.MessageDTOUtils;
import com.gm2.dev.util.Operation;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransactionService transactionService;
    private final AccountValidationService accountValidationService;

    public TransferService(TransferRepository transferRepository, @Lazy TransactionService transactionService,
                           AccountValidationService accountValidationService) {
        this.transferRepository = transferRepository;
        this.transactionService = transactionService;
        this.accountValidationService = accountValidationService;
    }

    public ResponseEntity<MessageDTO> transfers(TransferResponseDTO transferResponseDTO){
        accountsAreValid(transferResponseDTO);

        return getMessageDTOResponseEntity(transferResponseDTO);
    }
    public List<TransferResponseDTO> getAll(String accountNumber){

        List<TransferResponseDTO> transfersResponse = this.transferRepository.getTransferByAccountFrom(accountNumber).stream()
                .map(TransferMapper.INSTANCE::toDTOResponse)
                .collect(Collectors.toList());

        return getTransferResponseDTOS(accountNumber, transfersResponse);
    }
    public void accountsAreValid(TransferResponseDTO transferResponseDTO){
        this.accountValidationService.verifyAccount(transferResponseDTO.getAccountFrom());
        this.accountValidationService.verifyAccount(transferResponseDTO.getAccountTo());
    }

    private ResponseEntity<MessageDTO> getMessageDTOResponseEntity(TransferResponseDTO transferResponseDTO) {
        AccountStatementDTO accountStatement =
                this.transactionService.getAccountStatement(transferResponseDTO.getAccountFrom());

        int currentBalance = accountStatement.getBalance().compareTo(transferResponseDTO.getValue());

        if (currentBalance == 1 || currentBalance == 0){
            var response = this.transferRepository.save(TransferMapper.INSTANCE.toModel(transferResponseDTO));
            return ResponseEntity.ok(MessageDTOUtils.transferSucessfull(transferResponseDTO));
        } else {
            return new ResponseEntity<>(MessageDTOUtils.InsufficientFunds(transferResponseDTO), HttpStatus.NOT_FOUND);
        }
    }

    private static List<TransferResponseDTO> getTransferResponseDTOS(String accountNumber, List<TransferResponseDTO> transfersResponse) {
        transfersResponse.forEach(t ->  {
            if (t.getAccountFrom().equals(accountNumber)){
                t.setOperation(Operation.TransferenciaEnviada);
                t.setValue(t.getValue().negate());
                t.setDate(LocalDateTime.now());

            } else {
                t.setOperation(Operation.TransferenciaRecebida);
            }
        });

        return transfersResponse;
    }
}
