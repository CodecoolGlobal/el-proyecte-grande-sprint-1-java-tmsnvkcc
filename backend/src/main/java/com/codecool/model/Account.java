package com.codecool.model;

import java.util.UUID;

public record Account(
        int id,
        int userId,
        UUID uuid,
        String name,
        String description,
        String currency,
        double actualBalance,
        double savingsBalance) {
}
