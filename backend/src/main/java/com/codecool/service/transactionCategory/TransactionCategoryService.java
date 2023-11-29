package com.codecool.service.transactionCategory;

import com.codecool.entity.TransactionCategory;
import com.codecool.repository.TransactionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionCategoryService {
  private final TransactionCategoryRepository transactionCategoryRepository;

  @Autowired
  public TransactionCategoryService(TransactionCategoryRepository transactionCategoryRepository) {
    this.transactionCategoryRepository = transactionCategoryRepository;
  }

  public List<TransactionCategory> getAll() {
    return transactionCategoryRepository.findAll();
  }
}
