package com.codecool.dao;

import com.codecool.model.Account;
import com.codecool.postgresDb.PsqlConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AccountDAOJdbc implements AccountDAO {
    private final PsqlConnector psqlConnector;

    @Autowired
    public AccountDAOJdbc(PsqlConnector psqlConnector) {
        this.psqlConnector = psqlConnector;
    }


    @Override
    public Account getAccount() {
        //TODO Replace dummy account with real data
        return new Account(
                1,
                1,
                UUID.randomUUID(),
                "Account 1",
                "Main account",
                "HUF",
                100000,
                50000
        );
    }
}
