package com.codecool.dao;

import com.codecool.model.transaction.ExternalTransaction;
import com.codecool.postgresDb.PsqlConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class SpendingsDAOJdbc implements SpendingsDAO{
    private final PsqlConnector psqlConnector;

    @Autowired
    public SpendingsDAOJdbc(PsqlConnector psqlConnector) {
        this.psqlConnector = psqlConnector;
    }

    @Override
    public List<ExternalTransaction> getAllTransactionForMonth(int year, int month) {
        //TODO Dummy data for testing purposes only!
        return List.of(new ExternalTransaction(UUID.randomUUID(),1,1,"Test Spending", LocalDate.now(),-5000,false,false,"Test category"));
    }
}
