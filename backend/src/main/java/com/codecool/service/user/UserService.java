package com.codecool.service.user;

import com.codecool.dto.access.NewUserDTO;
import com.codecool.dto.user.UpdateProfileDTO;
import com.codecool.entity.Account;
import com.codecool.entity.Currency;
import com.codecool.entity.TransactionCategory;
import com.codecool.entity.User;
import com.codecool.exception.FormErrorException;
import com.codecool.repository.TransactionCategoryRepository;
import com.codecool.repository.UserRepository;
import com.codecool.service.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final TransactionCategoryRepository transactionCategoryRepository;
  private final CurrencyService currencyService;

  @Autowired
  public UserService(UserRepository userRepository, TransactionCategoryRepository transactionCategoryRepository, CurrencyService currencyService) {
    this.userRepository = userRepository;
    this.transactionCategoryRepository = transactionCategoryRepository;
    this.currencyService = currencyService;
  }

  @Transactional
  public void addUser(NewUserDTO user, String hashedPassword) {
    Currency currency = currencyService.findCurrencyByCode("HUF");
    Account account = new Account(currency);
    TransactionCategory defaultCategoryOne = transactionCategoryRepository.findById(1).get();
    TransactionCategory defaultCategoryTwo = transactionCategoryRepository.findById(2).get();
    TransactionCategory defaultCategoryThree = transactionCategoryRepository.findById(3).get();
    TransactionCategory defaultCategoryFour = transactionCategoryRepository.findById(4).get();

    List<TransactionCategory> defaultTransactionsCategories = new ArrayList<>(){{
      add(defaultCategoryOne);
      add(defaultCategoryTwo);
      add(defaultCategoryThree);
      add(defaultCategoryFour);
    }};
    User newUser = new User(user.registerEmail(), hashedPassword, account);
    newUser.setCategories(defaultTransactionsCategories);

    userRepository.save(newUser);
  }

  public User findUserByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);

    if (user.isEmpty()) {
      throw new FormErrorException("This email address is not registered in our database.");
    }

    return user.get();
  }

  public void checkEmailInDatabase(String email) {
    Optional<User> user = userRepository.findByEmail(email);

    if (user.isPresent()) {
      throw new FormErrorException("This email is already registered in our system. Choose another one.");
    }
  }

  @Transactional
  public void updateUserProfile(UpdateProfileDTO profileData, User currentUser) {
    currentUser.setUserName(profileData.username());
    currentUser.setEmail(profileData.email());
    currentUser.setHashedPassword(profileData.password());

    userRepository.save(currentUser);
  }
}
