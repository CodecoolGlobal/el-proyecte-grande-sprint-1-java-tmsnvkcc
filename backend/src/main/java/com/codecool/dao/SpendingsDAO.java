package com.codecool.dao;

import com.codecool.model.transaction.ExternalTransaction;

import java.util.List;

public interface SpendingsDAO {
    public List<ExternalTransaction> getAllTransactionForMonth(int year, int month);
}
