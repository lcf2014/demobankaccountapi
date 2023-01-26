package com.luanafaria.demoaccount.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    @NonNull
    private Long accountId;

    @NonNull
    private Integer operationTypeId;

    @NonNull
    private BigDecimal amount;
}
