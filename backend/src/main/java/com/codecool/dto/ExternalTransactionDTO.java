package com.codecool.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record ExternalTransactionDTO(
        String description,
        LocalDate dateOfTransaction,
        int amount,
        boolean isPlanned,
        boolean isRecurring,
        String categoryName
) {
}
