package com.codecool.repository;

import com.codecool.model.transaction.ExternalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalTransactionRepository extends JpaRepository<ExternalTransaction, Integer> {

}
