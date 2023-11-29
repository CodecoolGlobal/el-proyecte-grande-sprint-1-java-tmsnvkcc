package com.codecool.service.account;

import com.codecool.entity.Account;
import com.codecool.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
  private final AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Optional<List<Account>> getAccountsByUserId(int userId) {
    return accountRepository.findByUserId(userId);
  }
}
