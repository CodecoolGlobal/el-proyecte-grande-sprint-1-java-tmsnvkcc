package com.codecool.dao;

import com.codecool.model.transaction.ExternalTransaction;

import java.util.List;

public interface IncomeDAO {
    public List<ExternalTransaction> getAllTransactionForMonth(int year, int month);
}
