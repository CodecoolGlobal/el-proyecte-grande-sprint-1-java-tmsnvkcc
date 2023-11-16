package com.codecool.controller.service;

import com.codecool.dao.IncomeDAO;
import com.codecool.dao.SavingsDAO;
import com.codecool.dao.SpendingsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackPageService {
    private final IncomeDAO incomeDAO;
    private final SpendingsDAO spendingsDAO;
    private final SavingsDAO savingsDAO;

    @Autowired
    public TrackPageService(IncomeDAO incomeDAO, SpendingsDAO spendingsDAO, SavingsDAO savingsDAO) {
        this.incomeDAO = incomeDAO;
        this.spendingsDAO = spendingsDAO;
        this.savingsDAO = savingsDAO;
    }

    public void getTransactionForMonth(int year, int month) {

    }
}
