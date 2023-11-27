package com.codecool.controller.user;

import com.codecool.config.postgreSQL.PostgreSQLImpl;
import com.codecool.dto.ForgottenPasswordDTO;
import com.codecool.dto.LoginUserDTO;
import com.codecool.dto.NewUserDTO;
import com.codecool.dto.UserDataAfterLoginDTO;
import com.codecool.entity.User;
import com.codecool.exception.FormErrorException;
import com.codecool.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(
  path = "/api/users",
  consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  // TODO: delete userId from all endpoint because it will be in token
  private final UserService userService;
  private static final Logger logger = LoggerFactory.getLogger(PostgreSQLImpl.class);

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<UserDataAfterLoginDTO> loginUser(@RequestBody LoginUserDTO user) throws FormErrorException {
    if (user == null || user.loginEmail().isEmpty() || user.loginPassword().isEmpty()) {
      throw new FormErrorException("The login was unsuccessful, please try again.");
    }

    Optional<User> foundUser = userService.findUserByEmail(user.loginEmail());

    if (foundUser.isEmpty()) {
      throw new FormErrorException("The login was unsuccessful, please try again.");
    }

    User userDetails = foundUser.get();
    UserDataAfterLoginDTO userData = new UserDataAfterLoginDTO(
      userDetails.getId(),
      userDetails.getDateOfRegistration(),
      userDetails.getUserName(),
      userDetails.getEmail(),
      userDetails.getAccount()
    );

    return new ResponseEntity<>(userData, HttpStatus.OK);
  }

  @PostMapping(path = "/register")
  public ResponseEntity<Object> registerUser(@RequestBody NewUserDTO user) throws FormErrorException {
    if (user == null || user.registerEmail().isEmpty() || user.registerPassword().isEmpty()) {
      throw new FormErrorException("The registration was unsuccessful, please try again.");
    }

    boolean isEmailPresent = userService.isEmailAlreadyInDatabase(user.registerEmail());

    if (isEmailPresent) {
      throw new FormErrorException("This email is already registered in our system. Choose another one.");
    }

    userService.addUser(user, "fakehashedpassword");
    Map<String, String> message = new HashMap<>() {{ put("message", "success"); }};

    return new ResponseEntity<>(message, HttpStatus.CREATED);
  }

  @PutMapping("/password-reset")
  public ResponseEntity<Object> resetPassword(@RequestBody ForgottenPasswordDTO user) throws FormErrorException {
    if (user == null || user.email().isEmpty()) {
      throw new FormErrorException("The form submission was unsuccessful, please try again.");
    }

    // TODO - finish method
    // TODO - implement email reset stuff
    Map<String, String> message = new HashMap<>() {{ put("message", "Reset email sent."); }};
    return new ResponseEntity<>(message, HttpStatus.CREATED);
  }

  @PutMapping("/changeUsername/{userId}")
  public ResponseEntity<String> changeUsername(@PathVariable UUID userId) {
    try {
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      logger.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
  }

  @PutMapping("/changePassword/{userId}")
  public ResponseEntity<String> changePassword(@PathVariable UUID userId) {
    try {
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      logger.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
  }

  @PutMapping("/changeEmail/{userId}")
  public ResponseEntity<String> changeEmail(@PathVariable UUID userId) {
    try {
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      logger.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
  }
}
