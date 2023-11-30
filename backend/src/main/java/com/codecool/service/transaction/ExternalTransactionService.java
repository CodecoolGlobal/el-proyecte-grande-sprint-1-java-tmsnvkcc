package com.codecool.service.transaction;

import com.codecool.dto.transactions.NewExternalTransactionDTO;
import com.codecool.entity.ExternalTransaction;
import com.codecool.entity.TransactionCategory;
import com.codecool.entity.User;
import com.codecool.repository.ExternalTransactionRepository;
import com.codecool.repository.TransactionCategoryRepository;
import com.codecool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalTransactionService {
  private final ExternalTransactionRepository externalTransactionRepository;
  private final UserRepository userRepository;
  private final TransactionCategoryRepository transactionCategoryRepository;

  @Autowired
  public ExternalTransactionService(ExternalTransactionRepository externalTransactionRepository, UserRepository userRepository, TransactionCategoryRepository transactionCategoryRepository) {
    this.externalTransactionRepository = externalTransactionRepository;
    this.userRepository = userRepository;
    this.transactionCategoryRepository = transactionCategoryRepository;
  }

  public List<ExternalTransaction> findTransactionsByYearAndMonth(int userId, int currentYear, int currentMonth) {
    return externalTransactionRepository.findAllByYearAndMonth(userId, currentYear, currentMonth);
  }

  public ExternalTransaction addTransaction(NewExternalTransactionDTO newExternalTransactionDTO) {
    Optional<User> user = userRepository.findById(newExternalTransactionDTO.userId());
    Optional<TransactionCategory> transactionCategory = transactionCategoryRepository.findById(newExternalTransactionDTO.categoryId());

    ExternalTransaction externalTransaction = new ExternalTransaction(
      user.get(),
      newExternalTransactionDTO.description(),
      newExternalTransactionDTO.dateOfTransaction(),
      newExternalTransactionDTO.amount(),
      newExternalTransactionDTO.isPlanned(),
      newExternalTransactionDTO.isRecurring(),
      user.get().getAccount(),
      transactionCategory.get()
    );

    externalTransactionRepository.save(externalTransaction);

    return externalTransaction;
  }
}
