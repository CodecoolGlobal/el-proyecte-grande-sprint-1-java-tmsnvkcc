package com.codecool.service.user;

import com.codecool.dto.NewUserDTO;
import com.codecool.dto.UpdateProfileDTO;
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
    User newUser = new User(user.registerEmail(), hashedPassword, account);
    account.setUser(newUser);

    userRepository.save(newUser);
  }

  public User findUserByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);

    if (user.isEmpty()) {
      throw new FormErrorException("This email address is not registered in our database.");
    }

    return user.get();
  }

  public boolean isEmailAlreadyInDatabase(String email) {
    Optional<User> foundUser = userRepository.findByEmail(email);

    return foundUser.isPresent();
  }

  public void updateUserProfile(UpdateProfileDTO profileData, User currentUser){
    currentUser.setUserName(profileData.username());
    currentUser.setEmail(profileData.email());
    currentUser.setHashedPassword(profileData.password());

    userRepository.save(currentUser);
  }
}
