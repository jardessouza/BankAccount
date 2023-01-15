package com.gm2.dev.controller;

import com.gm2.dev.dto.MessageDTO;
import com.gm2.dev.dto.TransferResponseDTO;
import com.gm2.dev.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<MessageDTO> post(@RequestBody TransferResponseDTO transferResponseDTO){
        return this.transferService.transfers(transferResponseDTO);
    }

    @GetMapping(path = "/{accountNumber}")
    public ResponseEntity<List<TransferResponseDTO>> getAll(@PathVariable String accountNumber){
        return ResponseEntity.ok(this.transferService.getAll(accountNumber));
    }

}
