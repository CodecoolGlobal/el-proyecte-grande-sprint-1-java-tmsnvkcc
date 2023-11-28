package com.codecool.dto;

import java.util.List;

public record MonthlyTransactionsDTO(List<ExternalTransactionDTO> externalTransactionDTOS, List<LocalTransactionDTO> localTransactionDTOS) {
}
