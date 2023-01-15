package com.gm2.dev.dto;

import com.gm2.dev.util.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {

    private String accountNumber;


    private LocalDateTime date;


    private Operation operation;


    private BigDecimal value;

}
