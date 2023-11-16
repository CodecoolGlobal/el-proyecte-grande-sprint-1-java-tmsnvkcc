package com.codecool.dao;

import com.codecool.model.transaction.ExternalTransaction;
import com.codecool.postgresDb.PsqlConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class IncomeDAOJdbc implements IncomeDAO{
    private final PsqlConnector psqlConnector;

    @Autowired
    public IncomeDAOJdbc(PsqlConnector psqlConnector) {
        this.psqlConnector = psqlConnector;
    }

    @Override
    public List<ExternalTransaction> getAllTransactionForMonth(int year, int month) {
        //TODO Dummy data for testing purposes only
        return List.of(new ExternalTransaction(UUID.randomUUID(),1,1,"Test Income", LocalDate.now(),15000,false,false,"Test category"));
    }
}
