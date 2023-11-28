package com.codecool.controller.transaction;

import com.codecool.dto.GetMonthlyTransactionsDTO;
import com.codecool.service.transaction.ExternalTransactionService;
import com.codecool.service.transaction.LocalTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final ExternalTransactionService externalTransactionService;
    private final LocalTransactionsService localTransactionsService;

    @Autowired
    public TransactionController(ExternalTransactionService externalTransactionService, LocalTransactionsService localTransactionsService) {
        this.externalTransactionService = externalTransactionService;
        this.localTransactionsService = localTransactionsService;
    }

    @GetMapping("/{year}/{month}")
    public GetMonthlyTransactionsDTO getTransactionsForMonth(@PathVariable int year, @PathVariable int month){
//        return trackPageService.getTransactionForMonth(year,month);
        return null;
    }
}
