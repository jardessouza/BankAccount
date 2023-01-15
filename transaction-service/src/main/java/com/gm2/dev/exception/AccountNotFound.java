package com.gm2.dev.exception;

public class AccountNotFound extends RuntimeException{
    public AccountNotFound(String account) {
        super(String.format("Account with id %s not exists", account));
    }
}
