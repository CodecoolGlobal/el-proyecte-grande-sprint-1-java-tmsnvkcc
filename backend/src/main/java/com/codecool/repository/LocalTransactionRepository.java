package com.codecool.repository;

import com.codecool.model.transaction.LocalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalTransactionRepository extends JpaRepository<LocalTransaction, Integer> {
}
