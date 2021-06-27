package com.kholin.atm.rest.processing.repository;

import com.kholin.atm.rest.processing.entity.Client;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    //QueryCreationException Reason: Cannot query by nested property:
    //Optional<Client> findByAccounts_Cards_NumAndAccounts_Cards_ExpireAndAccounts_Cards_Cvc2(String num, Date expire, String cvc2);
    @Query("SELECT c.* FROM clients c, accounts a, cards k " +
           "WHERE a.client_id = c.id AND k.account_id = a.id AND " +
           "      k.num = :num AND k.expire = :expire AND k.cvc2 = :cvc2")
    Optional<Client> findByCard(String num, Date expire, String cvc2);

}
