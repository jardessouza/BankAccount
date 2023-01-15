package com.gm2.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponseDTO extends TransactionResponseDTO {
    private String accountFrom;
    private String accountTo;
    private BigDecimal value;

}
