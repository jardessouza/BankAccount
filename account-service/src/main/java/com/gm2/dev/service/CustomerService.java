package com.gm2.dev.service;

import com.gm2.dev.dto.CustomerDTO;
import com.gm2.dev.entity.Customer;
import com.gm2.dev.exception.AccountCustomerIsNotValid;
import com.gm2.dev.exception.AccountNumberAlreadyExistsException;
import com.gm2.dev.mapper.CustomerMapper;
import com.gm2.dev.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO save(CustomerDTO customerDTO){
        verifyIfExists(customerDTO.getAccountNumber());
        Customer customerCreated = getAndSaveCustomer(customerDTO);
        return CustomerMapper.INSTANCE.toDTO(customerCreated);
    }

    private Customer getAndSaveCustomer(CustomerDTO customerDTO) {
        Customer customerToCreated = CustomerMapper.INSTANCE.toModel(customerDTO);
        Customer customerCreated = this.customerRepository.save(customerToCreated);
        return customerCreated;
    }

    public List<CustomerDTO> getAll(){
        return this.customerRepository.findAll()
                .stream().map(CustomerMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Object verifyIfExistsAccount(String accountNumber) {
        return this.customerRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountCustomerIsNotValid(accountNumber));
    }

    private void verifyIfExists(String accountNumber) {
        this.customerRepository.findByAccountNumber(accountNumber)
                .ifPresent(author -> {throw new AccountNumberAlreadyExistsException(accountNumber); });
    }



}
