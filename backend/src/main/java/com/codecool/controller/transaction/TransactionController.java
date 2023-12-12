package com.codecool.controller.transaction;
import com.codecool.dto.transactions.LocalTransactionDTO;
import com.codecool.dto.transactions.MonthlyTransactionsDTO;
import com.codecool.dto.transactions.NewExternalTransactionDTO;
import com.codecool.entity.ExternalTransaction;
import com.codecool.entity.LocalTransaction;
import com.codecool.entity.TrackeroUser;
import com.codecool.service.account.AccountService;
import com.codecool.service.transaction.ExternalTransactionService;
import com.codecool.service.transaction.LocalTransactionsService;
import com.codecool.service.transaction.MainTransactionService;
import com.codecool.service.transactionCategory.TransactionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final MainTransactionService mainTransactionService;
    private final ExternalTransactionService externalTransactionService;
    private final TransactionCategoryService transactionCategoryService;
    private final LocalTransactionsService localTransactionsService;
    private final AccountService accountService;

    @Autowired
    public TransactionController(MainTransactionService mainTransactionService,LocalTransactionsService localTransactionsService, ExternalTransactionService externalTransactionService, TransactionCategoryService transactionCategoryService, AccountService accountService) {
        this.mainTransactionService = mainTransactionService;
        this.externalTransactionService = externalTransactionService;
        this.transactionCategoryService = transactionCategoryService;
        this.accountService = accountService;
        this.localTransactionsService = localTransactionsService;
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<MonthlyTransactionsDTO> getTransactionsForMonth(@PathVariable int year, @PathVariable int month) {
        TrackeroUser user = (TrackeroUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        MonthlyTransactionsDTO result = mainTransactionService.getMonthlyTransactions(user.getId(), year, month);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/add/external-transaction")
    public ResponseEntity<ExternalTransaction> addTransaction(@RequestBody NewExternalTransactionDTO newExternalTransaction) {
        ExternalTransaction externalTransaction = externalTransactionService.addTransaction(newExternalTransaction);
        accountService.updateBalance(newExternalTransaction.accountId(), newExternalTransaction.amount());

        return new ResponseEntity<>(externalTransaction, HttpStatus.CREATED);
    }

    @PostMapping("/add/local-transaction")
    public ResponseEntity<LocalTransaction> addLocalTransaction(@RequestBody LocalTransactionDTO localTransactionDTO) {
        LocalTransaction localTransaction = localTransactionsService.addTransaction( localTransactionDTO );

        return new ResponseEntity<>(localTransaction,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/local-transaction")
    public ResponseEntity<LocalTransaction> deleteLocalTransaction(@RequestBody int transactionId){
        LocalTransaction localTransaction = localTransactionsService.deleteTransaction(transactionId);

        return new ResponseEntity<>(localTransaction,HttpStatus.ACCEPTED);
    }
}
