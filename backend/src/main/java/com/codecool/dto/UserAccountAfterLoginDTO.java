package com.codecool.dto;

import com.codecool.entity.ExternalTransaction;
import com.codecool.entity.LocalTransaction;

import java.util.List;

public record UserAccountAfterLoginDTO(
  String name,
  String description,
  double actualBalance,
  double savingsBalance,
  List<ExternalTransaction> externalTransactions,
  List<LocalTransaction> localTransactions
) {}
