package com.gm2.dev.dto;

import com.gm2.dev.util.Operation;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDTO {

    private String accountNumber;

    private Operation operation;

    private BigDecimal value;

}
