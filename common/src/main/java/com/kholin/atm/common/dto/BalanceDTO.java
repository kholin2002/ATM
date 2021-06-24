package com.kholin.atm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDTO {
    private BigDecimal value;
    private Currency currency;
}
