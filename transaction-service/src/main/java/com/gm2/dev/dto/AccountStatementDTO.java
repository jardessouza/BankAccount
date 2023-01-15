package com.gm2.dev.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountStatementDTO {
    private String accountNumber;
    private List<TransactionResponseDTO> transactions;
    private BigDecimal balance;
    public void calculateBalance(){
        this.balance = BigDecimal.ZERO;
        for(TransactionResponseDTO transaction: transactions){
            this.balance = balance.add(transaction.getValue());
        }
    }


}
