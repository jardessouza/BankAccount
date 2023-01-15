package com.gm2.dev.mapper;

import com.gm2.dev.dto.CustomerDTO;
import com.gm2.dev.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    public static final CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toModel(CustomerDTO customerDTO);

    CustomerDTO toDTO(Customer customer);


}
