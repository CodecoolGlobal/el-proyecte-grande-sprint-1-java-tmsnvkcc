package com.codecool.controller.user;

import com.codecool.config.postgreSQL.PostgreSQLImpl;
import com.codecool.dto.access.ForgottenPasswordDTO;
import com.codecool.dto.access.LoginUserDTO;
import com.codecool.dto.access.NewUserDTO;
import com.codecool.dto.access.ResetPasswordDTO;
import com.codecool.dto.user.UpdateProfileDTO;
import com.codecool.dto.user.UserAccountAfterLoginDTO;
import com.codecool.dto.user.UserDataAfterLoginDTO;
import com.codecool.entity.Account;
import com.codecool.entity.ExternalTransaction;
import com.codecool.entity.LocalTransaction;
import com.codecool.entity.User;
import com.codecool.exception.FormErrorException;
import com.codecool.service.account.AccountService;
import com.codecool.service.email.EmailService;
import com.codecool.dto.access.EmailDetailsDTO;
import com.codecool.service.transaction.ExternalTransactionService;
import com.codecool.service.transaction.LocalTransactionsService;
import com.codecool.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
  path = "/api/users",
  consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  private final UserService userService;
  private final AccountService accountService;
  private final EmailService emailService;
  private final ExternalTransactionService externalTransactionService;
  private final LocalTransactionsService localTransactionsService;
  private final UserMessages userMessages;
  private static final Logger logger = LoggerFactory.getLogger(PostgreSQLImpl.class);

  @Autowired
  public UserController(UserService userService, AccountService accountService, EmailService emailService, ExternalTransactionService externalTransactionService, LocalTransactionsService localTransactionsService, UserMessages userMessages) {
    this.userService = userService;
    this.accountService = accountService;
    this.emailService = emailService;
    this.externalTransactionService = externalTransactionService;
    this.localTransactionsService = localTransactionsService;
    this.userMessages = userMessages;
  }

  @PostMapping("/login")
  public ResponseEntity<UserDataAfterLoginDTO> loginUser(@RequestBody LoginUserDTO user) {
    if (user == null || user.loginEmail().isEmpty() || user.loginPassword().isEmpty()) {
      throw new FormErrorException(userMessages.LOGIN_ERROR_MESSAGE);
    }

    User foundUser = userService.findUserByEmail(user.loginEmail());

    int currentYear = getCurrentYear();
    int currentMonth = getCurrentMonthValue();

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
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<HttpStatus> registerUser(@RequestBody NewUserDTO user) {
    if (user == null || user.registerEmail().isEmpty() || user.registerPassword().isEmpty()) {
      throw new FormErrorException(userMessages.FORM_ERROR_MESSAGE);
    }

    userService.checkEmailInDatabase(user.registerEmail());

    userService.addUser(user, "fakehashedpassword");

    String subject = userMessages.WELCOME_EMAIL_SUBJECT;
    String body = userMessages.WELCOME_EMAIL_BODY;
    EmailDetailsDTO emailDetailsDTO = new EmailDetailsDTO(user.registerEmail(), subject, body);
    emailService.sendEmail(emailDetailsDTO);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/password-reset")
  public ResponseEntity<HttpStatus> sendResetPasswordEmail(@RequestBody ForgottenPasswordDTO userData) {
    if (userData == null || userData.resetEmail().isEmpty()) {
      throw new FormErrorException(userMessages.FORM_ERROR_MESSAGE);
    }

    User foundUser = userService.findUserByEmail(userData.resetEmail());
    String subject = userMessages.PASSWORD_RESET_SUBJECT;
    // TODO send the email in hashed form.
    String body = MessageFormat.format(userMessages.PASSWORD_RESET_BODY, foundUser.getEmail());
    EmailDetailsDTO emailDetailsDTO = new EmailDetailsDTO(foundUser.getEmail(), subject, body);
    emailService.sendEmail(emailDetailsDTO);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/password-reset/{hashedUserEmail}")
  public ResponseEntity<HttpStatus> resetPassword(@PathVariable("hashedUserEmail") String email, @RequestBody ResetPasswordDTO userData) {
    if (email == null || userData == null || userData.resetPassword().isEmpty()) {
      throw new FormErrorException(userMessages.FORM_ERROR_MESSAGE);
    }

    User foundUser = userService.findUserByEmail(email);

    // TODO - hash the password;
    UpdateProfileDTO updatedData = new UpdateProfileDTO(foundUser.getUserName(), email, userData.resetPassword());
    userService.updateUserProfile(updatedData, foundUser);

    return new ResponseEntity<>(HttpStatus.OK);
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

    int currentYear = getCurrentYear();
    int currentMonth = getCurrentMonthValue();

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

  private int getCurrentYear() {
    return LocalDate.now().getYear();
  }

  private int getCurrentMonthValue() {
    return LocalDate.now().getMonthValue();
  }
}
