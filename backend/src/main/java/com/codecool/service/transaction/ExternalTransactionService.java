package com.codecool.service.transaction;

import com.codecool.entity.ExternalTransaction;
import com.codecool.repository.ExternalTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalTransactionService {
  private final ExternalTransactionRepository externalTransactionRepository;

  @Autowired
  public ExternalTransactionService(ExternalTransactionRepository externalTransactionRepository) {
    this.externalTransactionRepository = externalTransactionRepository;
  }

  public List<ExternalTransaction> findTransactionsByYearAndMonth(int userId, int currentYear, int currentMonth) {
    return externalTransactionRepository.findAllByYearAndMonth(userId, currentYear, currentMonth);
  }
}
