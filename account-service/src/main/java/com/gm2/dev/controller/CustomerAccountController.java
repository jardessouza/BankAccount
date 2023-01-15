package com.gm2.dev.controller;

import com.gm2.dev.dto.CustomerDTO;
import com.gm2.dev.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/customer-account")
public class CustomerAccountController {

    private final CustomerService customerService;

    public CustomerAccountController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> post(@RequestBody @Valid CustomerDTO customerDTO ){
        return new ResponseEntity<>(this.customerService.save(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        return ResponseEntity.ok(this.customerService.getAll());
    }

    @GetMapping(path = "/{account}")
    public ResponseEntity<Object> isValid(@PathVariable String account){
        return new ResponseEntity<>(this.customerService.verifyIfExistsAccount(account),HttpStatus.OK);
    }

}
