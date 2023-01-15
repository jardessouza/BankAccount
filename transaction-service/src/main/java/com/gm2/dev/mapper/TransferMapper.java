package com.gm2.dev.mapper;

import com.gm2.dev.dto.TransactionResponseDTO;
import com.gm2.dev.dto.TransferRequestDTO;
import com.gm2.dev.dto.TransferResponseDTO;
import com.gm2.dev.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    public static final TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

    Transfer toModel(TransferResponseDTO TransferResponseDTO);

    TransferRequestDTO toDTO(Transfer transfer);
    TransferResponseDTO toDTOResponse(Transfer transfer);

    TransactionResponseDTO toDTOTransaction(TransferResponseDTO TransferResponseDTO);
}
