package com.gm2.dev.exception;

import javax.persistence.EntityNotFoundException;

public class AccountCustomerNotFoundException extends EntityNotFoundException {
    public AccountCustomerNotFoundException(Long id){
        super(String.format("Account with id %s not exists", id));
    }

}
