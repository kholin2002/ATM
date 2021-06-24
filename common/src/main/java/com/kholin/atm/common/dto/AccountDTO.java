package com.kholin.atm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AccountDTO {
    @EqualsAndHashCode.Include
    private String number;

    private BalanceDTO balance;

    private List<CardDTO> cards;
}
