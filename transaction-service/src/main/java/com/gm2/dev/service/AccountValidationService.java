package com.gm2.dev.service;

import com.gm2.dev.exception.AccountNotFound;
import com.gm2.dev.request.AccountRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Scope("singleton")
public class AccountValidationService {
    private final AccountRequest accountRequest;

    public AccountValidationService(AccountRequest accountRequest) {
        this.accountRequest = accountRequest;
    }

    void verifyAccount(String accountNumber) {
        Object accRequest = Optional.of(this.accountRequest.getUserAccount(accountNumber))
                .orElseThrow(() -> new AccountNotFound(accountNumber));
    }
}