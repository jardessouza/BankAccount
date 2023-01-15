package com.gm2.dev.util;

import com.gm2.dev.dto.MessageDTO;
import com.gm2.dev.dto.TransferResponseDTO;

import java.math.BigDecimal;

public class MessageDTOUtils {
    public static MessageDTO InsufficientFunds(TransferResponseDTO transferResponseDTO){
        return returnMessageInsufficientFunds(transferResponseDTO);
    }

    public static MessageDTO transferSucessfull(TransferResponseDTO transferResponseDTO){
        return returnMessageTransferSucessfull(transferResponseDTO);
    }
    private static MessageDTO returnMessageInsufficientFunds(TransferResponseDTO transferResponseDTO){
        String accountFrom = transferResponseDTO.getAccountFrom();
        BigDecimal value = transferResponseDTO.getValue();
        String createdMessage =
                String.format("Account balance %s is insufficient, total balance is %.2f", accountFrom, value);

        return MessageDTO.builder()
                .message(createdMessage)
                .build();
    }

    private static MessageDTO returnMessageTransferSucessfull(TransferResponseDTO transferResponseDTO){
        String accountFrom = transferResponseDTO.getAccountFrom();
        String accountTo = transferResponseDTO.getAccountTo();
        BigDecimal value = transferResponseDTO.getValue();

        String createdMessage =
                String.format("Transfer of account %s from %.2f to %s successful", accountFrom,value, accountTo );

        return MessageDTO.builder()
                .message(createdMessage)
                .build();
    }
}
