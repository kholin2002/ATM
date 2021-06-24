package com.kholin.atm.rest.processing.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("CLIENTS")
@Data
public class Client {

    @Id
    private long id;

    private String name;

    @MappedCollection(idColumn = "CLIENT_ID")
    private Set<Account> accounts;

}
