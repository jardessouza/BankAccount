package com.gm2.dev.mapper;

import com.gm2.dev.dto.TransactionRequestDTO;
import com.gm2.dev.dto.TransactionResponseDTO;
import com.gm2.dev.dto.TransferResponseDTO;
import com.gm2.dev.entity.Transaction;
import com.gm2.dev.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    public static final TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toModel(TransactionRequestDTO transactionRequestDTO);

    TransactionRequestDTO toDTO(Transaction transaction);

    Transaction toModel(TransactionResponseDTO responseDTO);

    TransactionResponseDTO toDTOResponse(Transaction transaction);

    TransactionResponseDTO toDTOResponse(TransferResponseDTO transferResponseDTO);

    TransactionResponseDTO toDtoResponse(Transfer transfer);
}
