package com.codecool.service.user;

import com.codecool.dto.NewUserDTO;
import com.codecool.dto.UpdateProfileDTO;
import com.codecool.entity.Account;
import com.codecool.entity.TransactionCategory;
import com.codecool.entity.User;
import com.codecool.exception.FormErrorException;
import com.codecool.repository.TransactionCategoryRepository;
import com.codecool.repository.UserRepository;
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

  @Autowired
  public UserService(UserRepository userRepository, TransactionCategoryRepository transactionCategoryRepository) {
    this.userRepository = userRepository;
    this.transactionCategoryRepository = transactionCategoryRepository;
  }

  @Transactional
  public void addUser(NewUserDTO user, String hashedPassword) {
    Account account = new Account();
    TransactionCategory defaultCategoryOne = transactionCategoryRepository.findById(1).get();
    TransactionCategory defaultCategoryTwo = transactionCategoryRepository.findById(2).get();
    TransactionCategory defaultCategoryThree = transactionCategoryRepository.findById(3).get();
    TransactionCategory defaultCategoryFour = transactionCategoryRepository.findById(4).get();

    List<TransactionCategory> transactionCategories = new ArrayList<>(){{
      add(defaultCategoryOne);
      add(defaultCategoryTwo);
      add(defaultCategoryThree);
      add(defaultCategoryFour);
    }};
    User newUser = new User(user.registerEmail(), hashedPassword, account);
    newUser.setCategories(transactionCategories);

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
