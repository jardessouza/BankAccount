package com.gm2.dev.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Document(collection = "transfer")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Transfer {
    @MongoId
    private String id;
    @NonNull
    private String accountFrom;
    @NonNull
    private String accountTo;
    @NonNull
    private LocalDateTime dateTime;
    @NonNull
    private BigDecimal value;
}
