package com.codecool.controller.transaction;

import com.codecool.dto.MonthlyTransactionsDTO;
import com.codecool.service.transaction.ExternalTransactionService;
import com.codecool.service.transaction.LocalTransactionsService;
import com.codecool.service.transaction.MainTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final MainTransactionService mainTransactionService;

    @Autowired
    public TransactionController(MainTransactionService mainTransactionService) {
        this.mainTransactionService = mainTransactionService;
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<MonthlyTransactionsDTO> getTransactionsForMonth(@PathVariable int year, @PathVariable int month){
        MonthlyTransactionsDTO result = mainTransactionService.getMonthlyTransactions(1,year,month);
        return new ResponseEntity<>(result, HttpStatus.OK);
        //TODO Change hard coded user id
    }
}
