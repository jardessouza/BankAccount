package com.gm2.dev.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TB_CUSTOMERS")
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number" ,nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "full_name",nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false)
    private boolean enable;
}
