package com.gm2.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    @NotBlank
    private String cpf;

    @NotBlank
    private String fullName;

    @NotBlank
    private String accountNumber;

    @NotNull
    private boolean enable;

}
