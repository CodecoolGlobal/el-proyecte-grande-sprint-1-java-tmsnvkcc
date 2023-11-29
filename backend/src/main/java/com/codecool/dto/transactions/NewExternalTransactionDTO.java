package com.codecool.dto.transactions;

import java.time.LocalDate;

public record NewExternalTransactionDTO(
  int accountId,
  int userId,
  String category,
  String description,
  double amount,
  LocalDate dateOfTransaction,
  boolean isRecurring,
  boolean isPlanned
) {}
