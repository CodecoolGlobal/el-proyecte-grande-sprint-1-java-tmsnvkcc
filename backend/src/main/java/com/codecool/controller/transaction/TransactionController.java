package com.codecool.controller.transaction;

import com.codecool.dto.LocalTransactionDTO;
import com.codecool.dto.MonthlyTransactionsDTO;
import com.codecool.dto.transactions.NewExternalTransactionDTO;
import com.codecool.entity.ExternalTransaction;
import com.codecool.entity.LocalTransaction;
import com.codecool.service.transaction.ExternalTransactionService;
import com.codecool.service.transaction.LocalTransactionsService;
import com.codecool.service.transaction.MainTransactionService;
import com.codecool.service.transactionCategory.TransactionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final MainTransactionService mainTransactionService;
    private final ExternalTransactionService externalTransactionService;
    private final TransactionCategoryService transactionCategoryService;
    private final LocalTransactionsService localTransactionsService;

    @Autowired
    public TransactionController(MainTransactionService mainTransactionService, ExternalTransactionService externalTransactionService, TransactionCategoryService transactionCategoryService, LocalTransactionsService localTransactionsService) {
        this.mainTransactionService = mainTransactionService;
        this.externalTransactionService = externalTransactionService;
        this.transactionCategoryService = transactionCategoryService;
        this.localTransactionsService = localTransactionsService;
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<MonthlyTransactionsDTO> getTransactionsForMonth(@PathVariable int year, @PathVariable int month){
        MonthlyTransactionsDTO result = mainTransactionService.getMonthlyTransactions(1,year,month);
        return new ResponseEntity<>(result, HttpStatus.OK);
        //TODO Change hard coded user id
    }

    @PostMapping("/add/external-transaction")
    public ResponseEntity<ExternalTransaction> addTransaction(@RequestBody NewExternalTransactionDTO newExternalTransaction) {
        ExternalTransaction externalTransaction = externalTransactionService.addTransaction(newExternalTransaction);

        return new ResponseEntity<>(externalTransaction, HttpStatus.CREATED);
    }

    @PostMapping("/add/local-transaction")
    public ResponseEntity<LocalTransaction> addLocalTransaction(@RequestBody LocalTransactionDTO localTransactionDTO) {
        LocalTransaction localTransaction = localTransactionsService.addTransaction( localTransactionDTO );

        return new ResponseEntity<>(localTransaction,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/local-transaction")
    public ResponseEntity<LocalTransaction> deleteLocalTransaction(@RequestBody int transactionId){
        LocalTransaction localTransaction = localTransactionsService.deleteTransaction( transactionId );

        return new ResponseEntity<>(localTransaction,HttpStatus.ACCEPTED);
    }
}
