package com.codecool.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record ExternalTransactionDTO(
        int id,
        String description,
        LocalDate dateOfTransaction,
        double amount,
        boolean isPlanned,
        boolean isRecurring,
        String categoryName
) {
}
