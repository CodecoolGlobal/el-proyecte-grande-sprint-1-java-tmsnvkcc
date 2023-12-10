package com.codecool.controller.user;

import com.codecool.config.postgreSQL.PostgreSQLImpl;
import com.codecool.dto.access.ForgottenPasswordDTO;
import com.codecool.dto.access.LoginUserDTO;
import com.codecool.dto.access.NewUserDTO;
import com.codecool.dto.user.UpdateProfileDTO;
import com.codecool.dto.user.UserAccountAfterLoginDTO;
import com.codecool.dto.user.UserDataAfterLoginDTO;
import com.codecool.entity.Account;
import com.codecool.entity.ExternalTransaction;
import com.codecool.entity.LocalTransaction;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
  public ResponseEntity<?> loginUser(@RequestBody LoginUserDTO user) {
    if (user == null || user.loginEmail().isEmpty() || user.loginPassword().isEmpty()) {
      throw new FormErrorException("The login was unsuccessful, please try again.");
    }

    User foundUser = userService.findUserByEmail(user.loginEmail());

    int currentYear = LocalDate.now().getYear();
    int currentMonth = LocalDate.now().getMonthValue();

    // TODO the login endpoint shouldn't return anything else other than the user information.
    // datafetching should be driven by the frontend.
    // TODO an extra level of "domain service" layer could be implemented here that collects these various transaction service calls, so the upper-level methods don't look that cluttered.
    List<ExternalTransaction> externalTransactions = externalTransactionService.findTransactionsByYearAndMonth(foundUser.getId(), currentYear, currentMonth);
    List<LocalTransaction> localTransactions = localTransactionsService.findTransactionsByYearAndMonth(foundUser.getId(), currentYear, currentMonth);
    UserAccountAfterLoginDTO userAccountAfterLoginDTO = new UserAccountAfterLoginDTO(
      foundUser.getAccount().getId(),
      foundUser.getAccount().getName(),
      foundUser.getAccount().getDescription(),
      foundUser.getAccount().getActualBalance(),
      foundUser.getAccount().getSavingsBalance(),
      externalTransactions,
      localTransactions
    );
    UserDataAfterLoginDTO userData = new UserDataAfterLoginDTO(
      foundUser.getId(),
      foundUser.getDateOfRegistration(),
      foundUser.getUserName(),
      foundUser.getEmail(),
      foundUser.getCategories(),
      userAccountAfterLoginDTO
    );

    return new ResponseEntity<>(userData, HttpStatus.OK);
  }

  @PostMapping(path = "/register")
  public ResponseEntity<Object> registerUser(@RequestBody NewUserDTO user) throws FormErrorException {
    if (user == null || user.registerEmail().isEmpty() || user.registerPassword().isEmpty()) {
      throw new FormErrorException("The registration was unsuccessful, please try again.");
    }

    userService.checkEmailInDatabase(user.registerEmail());

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
    // TODO - implement email reset stuff, e.g. nodemailer or something similar.
    Map<String, String> message = new HashMap<>() {{ put("message", "Reset email sent."); }};
    return new ResponseEntity<>(message, HttpStatus.CREATED);
  }

  @GetMapping("/get-accounts")
  public ResponseEntity<?> getProfileAccounts() {
    User foundUser = userService.findUserByEmail("1@1.1"); // TODO change hard coded email

    // userid is not stored in accounts anymore. a user's accounts can be retrieved by using user.getAccount(). This currently allows for having one account.
    Optional<List<Account>> userAccount = accountService.getAccountsByUserId(foundUser.getId());
    return new ResponseEntity<>(userAccount, HttpStatus.OK);
  }

  @PutMapping("/update-profile")
  public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileDTO profileData) throws FormErrorException{
    if (profileData == null || profileData.email().isEmpty() || profileData.password().isEmpty() || profileData.username().isEmpty()) {
      throw new FormErrorException("The update was unsuccessful, please try again.");
    }

    User foundUser = userService.findUserByEmail(profileData.email()); // TODO fix this line because it wants to find by new email

    userService.updateUserProfile(profileData, foundUser);

    int currentYear = LocalDate.now().getYear();
    int currentMonth = LocalDate.now().getMonthValue();

    List<ExternalTransaction> externalTransactions = externalTransactionService.findTransactionsByYearAndMonth(foundUser.getId(), currentYear, currentMonth);
    List<LocalTransaction> localTransactions = localTransactionsService.findTransactionsByYearAndMonth(foundUser.getId(), currentYear, currentMonth);
    UserAccountAfterLoginDTO userAccountAfterLoginDTO = new UserAccountAfterLoginDTO(
      foundUser.getAccount().getId(),
      foundUser.getAccount().getName(),
      foundUser.getAccount().getDescription(),
      foundUser.getAccount().getActualBalance(),
      foundUser.getAccount().getSavingsBalance(),
      externalTransactions,
      localTransactions
    );
    UserDataAfterLoginDTO userData = new UserDataAfterLoginDTO(
      foundUser.getId(),
      foundUser.getDateOfRegistration(),
      foundUser.getUserName(),
      foundUser.getEmail(),
      foundUser.getCategories(),
      userAccountAfterLoginDTO
    );

    return new ResponseEntity<>(userData, HttpStatus.OK);
  }
}
