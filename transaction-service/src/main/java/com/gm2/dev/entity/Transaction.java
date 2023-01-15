package com.gm2.dev.entity;

import com.gm2.dev.util.Operation;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transaction")
public class Transaction {
    @MongoId
    private String id;

    @Field("account_number")
    private String accountNumber;

    @CreatedDate
    private LocalDateTime date;

    @NonNull
    private Operation operation;

    @NonNull
    private BigDecimal value;
}
