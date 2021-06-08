package com.kholin.atm.server.processing.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Set;

@Table("ACCOUNTS")
@Data
public class Account {

    @Id
    private long id;

    private String num;

    private BigDecimal balance;

    private String currency;

//    @MappedCollection(idColumn = "ID")
//    private Client client;

    @MappedCollection(idColumn = "ACCOUNT_ID")
    private Set<Card> cards;

}
