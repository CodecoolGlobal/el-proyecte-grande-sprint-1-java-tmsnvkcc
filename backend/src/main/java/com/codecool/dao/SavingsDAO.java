package com.codecool.dao;

import com.codecool.model.transaction.LocalTransaction;

import java.util.List;

public interface SavingsDAO {
    List<LocalTransaction> getAllTransactionForMonth(int year, int month);
}
