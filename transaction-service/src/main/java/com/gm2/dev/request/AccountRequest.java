package com.gm2.dev.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", url = "localhost:8000")
public interface AccountRequest {
    @GetMapping("/v1/customer-account/{account}")
    Object getUserAccount(@PathVariable String account);
}
