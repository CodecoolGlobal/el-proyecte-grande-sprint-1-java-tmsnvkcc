package com.codecool.dto;

import java.time.LocalDate;

public record LocalTransactionDTO(
        String description,
        LocalDate dateOfTransaction,
        int amount,
        boolean isPlanned,
        boolean isRecurring
) {
}
