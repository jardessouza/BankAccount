package com.gm2.dev.exception;


public class AccountCustomerIsNotValid extends RuntimeException {
    public AccountCustomerIsNotValid(String message) {
        super(String.format("Account %s is not valid", message));
    }
}
