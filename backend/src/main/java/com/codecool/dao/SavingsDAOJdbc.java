package com.codecool.dao;

import com.codecool.model.transaction.LocalTransaction;
import com.codecool.postgresDb.PsqlConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class SavingsDAOJdbc implements SavingsDAO {
    private final PsqlConnector psqlConnector;

    @Autowired
    public SavingsDAOJdbc(PsqlConnector psqlConnector) {
        this.psqlConnector = psqlConnector;
    }

    @Override
    public List<LocalTransaction> getAllTransactionForMonth(int year, int month) {
        // TODO: Warning, this is dummy data, replace it.
        return List.of(
            new LocalTransaction(UUID.randomUUID(), 1, 1, "Saving for a new bike", LocalDate.now(), 2000, true, false),
            new LocalTransaction(UUID.randomUUID(), 1, 1, "For birthday presents :)", LocalDate.now(), 5000, false, false)
        );
    }
}
