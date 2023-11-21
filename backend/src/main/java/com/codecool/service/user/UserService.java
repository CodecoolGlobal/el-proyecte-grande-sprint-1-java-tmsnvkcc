package com.codecool.service.user;

import com.codecool.dto.NewUserDTO;
import com.codecool.entity.Account;
import com.codecool.entity.User;
import com.codecool.exception.FormErrorException;
import com.codecool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public void addUser(NewUserDTO user, String hashedPassword) {
    Account account = new Account();
    User newUser = new User(user.registerEmail(), hashedPassword, account, false);
    account.setUser(newUser);

    userRepository.save(newUser);
  }

  public void findUserByEmail(String email) throws FormErrorException {
    Optional<User> foundUser = userRepository.findByEmail(email);

    if (foundUser.isPresent()) {
      throw new FormErrorException("Email is already registered.");
    }
  }
}
