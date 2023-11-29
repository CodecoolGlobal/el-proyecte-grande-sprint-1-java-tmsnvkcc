package com.codecool.service.transaction;

import com.codecool.dto.transactions.NewExternalTransactionDTO;
import com.codecool.entity.Account;
import com.codecool.entity.ExternalTransaction;
import com.codecool.entity.User;
import com.codecool.repository.ExternalTransactionRepository;
import com.codecool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalTransactionService {
  private final ExternalTransactionRepository externalTransactionRepository;
  private final UserRepository userRepository;

  @Autowired
  public ExternalTransactionService(ExternalTransactionRepository externalTransactionRepository, UserRepository userRepository) {
    this.externalTransactionRepository = externalTransactionRepository;
    this.userRepository = userRepository;
  }

  public List<ExternalTransaction> findTransactionsByYearAndMonth(int userId, int currentYear, int currentMonth) {
    return externalTransactionRepository.findAllByYearAndMonth(userId, currentYear, currentMonth);
  }

  public void addTransaction(NewExternalTransactionDTO transaction) {
    Optional<User> user = userRepository.findById(transaction.userId());

    ExternalTransaction externalTransaction = new ExternalTransaction(
      user.get(),
      transaction.description(),
      transaction.dateOfTransaction(),
      transaction.amount(),
      transaction.isPlanned(),
      transaction.isRecurring(),
      user.get().getAccount()
    );

    externalTransactionRepository.save(externalTransaction);
  }
}
