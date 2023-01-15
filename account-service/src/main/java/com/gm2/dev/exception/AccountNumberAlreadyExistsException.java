package com.gm2.dev.exception;

import javax.persistence.EntityExistsException;

public class AccountNumberAlreadyExistsException extends EntityExistsException {
    public AccountNumberAlreadyExistsException(String number){
        super(String.format("Account with number %s already exists! ", number));
    }
}
