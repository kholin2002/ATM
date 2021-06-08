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
public class ClientDTO {
    @EqualsAndHashCode.Include
    private long id;

    private String name;

    private List<AccountDTO> accounts;
}
