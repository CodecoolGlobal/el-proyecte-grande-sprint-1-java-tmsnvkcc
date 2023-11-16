package com.codecool.dto;

import java.util.ArrayList;
import java.util.List;

public record GetMonthlyTransactionsDTO(List<ExternalTransactionDTO> income, List<ExternalTransactionDTO> spendings, List<LocalTransactionDTO> savings) {
}
