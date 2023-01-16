package com.gm2.dev.controller;

import com.gm2.dev.dto.TransactionResponseDTO;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("resilience")
public class ResilienceTestController {
    private Logger logger = LoggerFactory.getLogger(ResilienceTestController.class);

    @GetMapping
    @Retry(name = "default", fallbackMethod = "fallbackMethod")
    public ResponseEntity get(){
        logger.info("Resquest recebido!");
        ResponseEntity<TransactionResponseDTO> forEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/v1/customer-accounttttt",
                TransactionResponseDTO.class);

        return ResponseEntity.ok(forEntity).getBody();
    }

    private ResponseEntity fallbackMethod(Exception error){
        return ResponseEntity.ok("Servico Indisponivel");
    }
}
