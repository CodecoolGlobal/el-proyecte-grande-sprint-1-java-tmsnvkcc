package com.codecool.service.transaction;

import com.codecool.entity.LocalTransaction;
import com.codecool.repository.LocalTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalTransactionsService {
  private final LocalTransactionRepository localTransactionRepository;

  public LocalTransactionsService(LocalTransactionRepository localTransactionRepository) {
    this.localTransactionRepository = localTransactionRepository;
  }

  public List<LocalTransaction> findTransactionsByYearAndMonth(int userId, int currentYear, int currentMonth) {
    return localTransactionRepository.findAllByYearAndMonth(userId, currentYear, currentMonth);
  }
}
