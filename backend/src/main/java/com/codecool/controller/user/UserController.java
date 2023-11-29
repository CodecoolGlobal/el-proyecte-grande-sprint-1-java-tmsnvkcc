package com.codecool.controller.user;

import com.codecool.config.postgreSQL.PostgreSQLImpl;
import com.codecool.dto.*;
import com.codecool.entity.Account;
import com.codecool.entity.ExternalTransaction;
import com.codecool.entity.LocalTransaction;
import com.codecool.entity.TransactionCategory;
import com.codecool.entity.User;
import com.codecool.exception.FormErrorException;
import com.codecool.service.account.AccountService;
import com.codecool.service.transaction.ExternalTransactionService;
import com.codecool.service.transaction.LocalTransactionsService;
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

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(
  path = "/api/users",
  consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  private final UserService userService;
  private final AccountService accountService;
  private final ExternalTransactionService externalTransactionService;
  private final LocalTransactionsService localTransactionsService;
  private static final Logger logger = LoggerFactory.getLogger(PostgreSQLImpl.class);

  @Autowired
  public UserController(UserService userService, AccountService accountService, ExternalTransactionService externalTransactionService, LocalTransactionsService localTransactionsService) {
    this.userService = userService;
    this.accountService = accountService;
    this.externalTransactionService = externalTransactionService;
    this.localTransactionsService = localTransactionsService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody LoginUserDTO user) throws FormErrorException {
    if (user == null || user.loginEmail().isEmpty() || user.loginPassword().isEmpty()) {
      throw new FormErrorException("The login was unsuccessful, please try again.");
    }

    Optional<User> foundUser = userService.findUserByEmail(user.loginEmail());

    if (foundUser.isEmpty()) {
      throw new FormErrorException("The login was unsuccessful, please try again.");
    }

    int currentYear = LocalDate.now().getYear();
    int currentMonth = LocalDate.now().getMonthValue();

    User userDetails = foundUser.get();
    Optional<List<Account>> userAccount = accountService.getAccountsByUserId(userDetails.getId(), currentYear, currentMonth);
    List<ExternalTransaction> externalTransactions = externalTransactionService.findTransactionsByYearAndMonth(userDetails.getId(), currentYear, currentMonth);
    List<LocalTransaction> localTransactions = localTransactionsService.findTransactionsByYearAndMonth(userDetails.getId(), currentYear, currentMonth);
    UserAccountAfterLoginDTO userAccountAfterLoginDTO = new UserAccountAfterLoginDTO(
      userAccount.get().get(0).getId(),
      userAccount.get().get(0).getName(),
      userAccount.get().get(0).getDescription(),
      userAccount.get().get(0).getActualBalance(),
      userAccount.get().get(0).getSavingsBalance(),
      externalTransactions,
      localTransactions
    );
    UserDataAfterLoginDTO userData = new UserDataAfterLoginDTO(
      userDetails.getId(),
      userDetails.getDateOfRegistration(),
      userDetails.getUserName(),
      userDetails.getEmail(),
      userDetails.getCategories(),
      userAccountAfterLoginDTO
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
      // do this check in the service.
      // do not throw it, just return a ResponseEntity with the correct http status and the message.
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

  @PutMapping("/update-profile")
  public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileDTO profileData) throws FormErrorException{
    if (profileData == null || profileData.email().isEmpty() || profileData.password().isEmpty() || profileData.username().isEmpty()) {
      throw new FormErrorException("The update was unsuccessful, please try again.");
    }


    Optional<User> foundUser = userService.findUserByEmail(profileData.email()); // TODO fix this line because it wants to find by new email

    if (foundUser.isEmpty()) {
      throw new FormErrorException("The update was unsuccessful, please try again.");
    }

    User userDetails = foundUser.get();

    userService.updateUserProfile(profileData, userDetails);

    int currentYear = LocalDate.now().getYear();
    int currentMonth = LocalDate.now().getMonthValue();

    Optional<List<Account>> userAccount = accountService.getAccountsByUserId(userDetails.getId(), currentYear, currentMonth);
    List<ExternalTransaction> externalTransactions = externalTransactionService.findTransactionsByYearAndMonth(userDetails.getId(), currentYear, currentMonth);
    List<LocalTransaction> localTransactions = localTransactionsService.findTransactionsByYearAndMonth(userDetails.getId(), currentYear, currentMonth);
    UserAccountAfterLoginDTO userAccountAfterLoginDTO = new UserAccountAfterLoginDTO(
            userAccount.get().get(0).getId(),
            userAccount.get().get(0).getName(),
            userAccount.get().get(0).getDescription(),
            userAccount.get().get(0).getActualBalance(),
            userAccount.get().get(0).getSavingsBalance(),
            externalTransactions,
            localTransactions
    );
    UserDataAfterLoginDTO userData = new UserDataAfterLoginDTO(
            userDetails.getId(),
            userDetails.getDateOfRegistration(),
            userDetails.getUserName(),
            userDetails.getEmail(),
            userDetails.getCategories(),
            userAccountAfterLoginDTO
    );

    return new ResponseEntity<>(userData, HttpStatus.OK);
  }
}
