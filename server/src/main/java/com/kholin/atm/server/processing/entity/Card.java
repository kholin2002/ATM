package com.kholin.atm.server.processing.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Table("CARDS")
@Data
public class Card {

    @Id
    private long id;
    private String num;
    private Date expire;
    private String cvc2;

//    @MappedCollection(idColumn = "ID")
//    private Account account;

}

