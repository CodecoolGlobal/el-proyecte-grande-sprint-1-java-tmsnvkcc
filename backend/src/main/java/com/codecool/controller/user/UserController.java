package com.codecool.controller.user;

import com.codecool.config.postgreSQL.PostgreSQLImpl;
import com.codecool.dto.access.ForgottenPasswordDTO;
import com.codecool.dto.access.LoginUserDTO;
import com.codecool.dto.access.NewUserDTO;
import com.codecool.dto.access.ResetPasswordDTO;
import com.codecool.dto.user.AboutMeDTO;
import com.codecool.dto.user.UpdateProfileDTO;
import com.codecool.dto.user.UserAccountAfterLoginDTO;
import com.codecool.dto.user.UserDataAfterLoginDTO;
import com.codecool.entity.Account;
import com.codecool.entity.ExternalTransaction;
import com.codecool.entity.LocalTransaction;
import com.codecool.entity.Role;
import com.codecool.entity.TrackeroUser;
import com.codecool.config.webSecurity.JwtResponse;
import com.codecool.exception.FormErrorException;
import com.codecool.security.JwtUtils;
import com.codecool.service.account.AccountService;
import com.codecool.service.email.EmailService;
import com.codecool.dto.access.EmailDetailsDTO;
import com.codecool.service.role.RoleService;
import com.codecool.service.transaction.ExternalTransactionService;
import com.codecool.service.transaction.LocalTransactionsService;
import com.codecool.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.Set;

@RestController
@RequestMapping(path = "/api/users", consumes = "application/json", produces = "application/json")
public class UserController {
  private final UserService userService;
  private final AccountService accountService;
  private final EmailService emailService;
  private final ExternalTransactionService externalTransactionService;
  private final LocalTransactionsService localTransactionsService;
  private final RoleService roleService;
  private final UserMessages userMessages;
  private final PasswordEncoder encoder;
  private final JwtUtils jwtUtils;
  private final AuthenticationManager authenticationManager;
  private static final Logger logger = LoggerFactory.getLogger(PostgreSQLImpl.class);

  @Autowired
  public UserController(
    UserService userService,
    AccountService accountService,
    EmailService emailService,
    ExternalTransactionService externalTransactionService,
    LocalTransactionsService localTransactionsService,
    RoleService roleService,
    UserMessages userMessages,
    PasswordEncoder encoder,
    JwtUtils jwtUtils,
    AuthenticationManager authenticationManager) {
    this.userService = userService;
    this.accountService = accountService;
    this.emailService = emailService;
    this.externalTransactionService = externalTransactionService;
    this.localTransactionsService = localTransactionsService;
    this.roleService = roleService;
    this.userMessages = userMessages;
    this.encoder = encoder;
    this.jwtUtils = jwtUtils;
    this.authenticationManager = authenticationManager;
  }

  @GetMapping("/me")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public ResponseEntity<AboutMeDTO> findUser() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    TrackeroUser trackeroUser = userService.findUserByEmail(user.getUsername());
    AboutMeDTO aboutMeDTO = new AboutMeDTO(trackeroUser.getId(), trackeroUser.getEmail(), trackeroUser.getUserName());

    return new ResponseEntity<>(aboutMeDTO, HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<UserDataAfterLoginDTO> loginUser(@RequestBody LoginUserDTO userLoginData) {
    if (userLoginData == null || userLoginData.loginEmail().isEmpty() || userLoginData.loginPassword().isEmpty()) {
      throw new FormErrorException(userMessages.LOGIN_ERROR_MESSAGE);
    }

    TrackeroUser foundTrackeroUser = userService.findUserByEmail(userLoginData.loginEmail());

    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginData.loginEmail(), userLoginData.loginPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    User userDetails = (User) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
      .map(user -> user.getAuthority())
      .toList();

    JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), roles);

//    int currentYear = getCurrentYear();
//    int currentMonth = getCurrentMonthValue();

    // TODO the login endpoint shouldn't return anything else other than the user information.
    // datafetching should be driven by the frontend.
    // TODO an extra level of "domain service" layer could be implemented here that collects these various transaction service calls, so the upper-level methods don't look that cluttered.
//    List<ExternalTransaction> externalTransactions = externalTransactionService.findTransactionsByYearAndMonth(foundTrackeroUser.getId(), currentYear, currentMonth);
//    List<LocalTransaction> localTransactions = localTransactionsService.findTransactionsByYearAndMonth(foundTrackeroUser.getId(), currentYear, currentMonth);
//    UserAccountAfterLoginDTO userAccountAfterLoginDTO = new UserAccountAfterLoginDTO(
//      foundTrackeroUser.getAccount().getId(),
//      foundTrackeroUser.getAccount().getName(),
//      foundTrackeroUser.getAccount().getDescription(),
//      foundTrackeroUser.getAccount().getActualBalance(),
//      foundTrackeroUser.getAccount().getSavingsBalance(),
//      externalTransactions,
//      localTransactions
//    );
    UserDataAfterLoginDTO userData = new UserDataAfterLoginDTO(
      foundTrackeroUser.getId(),
      foundTrackeroUser.getDateOfRegistration(),
      foundTrackeroUser.getUserName(),
      foundTrackeroUser.getEmail(),
      foundTrackeroUser.getCategories(),
      foundTrackeroUser.getAccount().getActualBalance(),
      foundTrackeroUser.getAccount().getSavingsBalance(),
      foundTrackeroUser.getAccount().getId(),
//      userAccountAfterLoginDTO,
      jwtResponse
    );

    return new ResponseEntity<>(userData, HttpStatus.OK);
  }

  @PostMapping(path = "/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<HttpStatus> registerUser(@RequestBody NewUserDTO userData) {
    if (userData == null || userData.registerEmail().isEmpty() || userData.registerPassword().isEmpty()) {
      throw new FormErrorException(userMessages.FORM_ERROR_MESSAGE);
    }

    userService.checkEmailInDatabase(userData.registerEmail());

    Role userRole = roleService.findRoleByName("ROLE_USER");
    userService.addUser(userData, encoder.encode(userData.registerPassword()), Set.of(userRole));

    String subject = userMessages.WELCOME_EMAIL_SUBJECT;
    String body = userMessages.WELCOME_EMAIL_BODY;
    EmailDetailsDTO emailDetailsDTO = new EmailDetailsDTO(userData.registerEmail(), subject, body);
//    emailService.sendEmail(emailDetailsDTO);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/password-reset")
  public ResponseEntity<HttpStatus> sendResetPasswordEmail(@RequestBody ForgottenPasswordDTO userData) {
    if (userData == null || userData.resetEmail().isEmpty()) {
      throw new FormErrorException(userMessages.FORM_ERROR_MESSAGE);
    }

    TrackeroUser foundTrackeroUser = userService.findUserByEmail(userData.resetEmail());
    String subject = userMessages.PASSWORD_RESET_SUBJECT;
    // TODO send the email in hashed form.
    String body = MessageFormat.format(userMessages.PASSWORD_RESET_BODY, foundTrackeroUser.getEmail());
    EmailDetailsDTO emailDetailsDTO = new EmailDetailsDTO(foundTrackeroUser.getEmail(), subject, body);
//    emailService.sendEmail(emailDetailsDTO);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/password-reset/{hashedUserEmail}")
  public ResponseEntity<HttpStatus> resetPassword(@PathVariable("hashedUserEmail") String email, @RequestBody ResetPasswordDTO userData) {
    if (email == null || userData == null || userData.resetPassword().isEmpty()) {
      throw new FormErrorException(userMessages.FORM_ERROR_MESSAGE);
    }

    TrackeroUser foundTrackeroUser = userService.findUserByEmail(email);

    // TODO - check the hashed string once it is set like that
    UpdateProfileDTO updatedData = new UpdateProfileDTO(foundTrackeroUser.getUserName(), email, userData.resetPassword());
    userService.updateUserProfile(updatedData, foundTrackeroUser);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/get-accounts")
  public ResponseEntity<Account> getProfileAccounts() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    TrackeroUser foundTrackeroUser = userService.findUserByEmail(user.getUsername());

    Account userAccount = accountService.findAccountById(foundTrackeroUser.getId());
    return new ResponseEntity<>(userAccount, HttpStatus.OK);
  }

  @GetMapping("/get-categories")
  public ResponseEntity<?> getCategories() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    TrackeroUser foundUser = userService.findUserByEmail(user.getUsername());

    return new ResponseEntity<>(foundUser.getCategories(), HttpStatus.OK);
  }

  @PutMapping("/update-profile")
  public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileDTO profileData) throws FormErrorException{
    if (profileData == null || profileData.email().isEmpty() || profileData.password().isEmpty() || profileData.username().isEmpty()) {
      throw new FormErrorException("The update was unsuccessful, please try again.");
    }

    TrackeroUser foundTrackeroUser = userService.findUserByEmail(profileData.email()); // TODO fix this line because it wants to find by new email

    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(profileData.email(), profileData.password()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    User userDetails = (User) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
      .map(user -> user.getAuthority())
      .toList();

    JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), roles);

    userService.updateUserProfile(profileData, foundTrackeroUser);

//    int currentYear = getCurrentYear();
//    int currentMonth = getCurrentMonthValue();

//    List<ExternalTransaction> externalTransactions = externalTransactionService.findTransactionsByYearAndMonth(foundTrackeroUser.getId(), currentYear, currentMonth);
//    List<LocalTransaction> localTransactions = localTransactionsService.findTransactionsByYearAndMonth(foundTrackeroUser.getId(), currentYear, currentMonth);
//    UserAccountAfterLoginDTO userAccountAfterLoginDTO = new UserAccountAfterLoginDTO(
//      foundTrackeroUser.getAccount().getId(),
//      foundTrackeroUser.getAccount().getName(),
//      foundTrackeroUser.getAccount().getDescription(),
//      foundTrackeroUser.getAccount().getActualBalance(),
//      foundTrackeroUser.getAccount().getSavingsBalance(),
//      externalTransactions,
//      localTransactions
//    );
    UserDataAfterLoginDTO userData = new UserDataAfterLoginDTO(
      foundTrackeroUser.getId(),
      foundTrackeroUser.getDateOfRegistration(),
      foundTrackeroUser.getUserName(),
      foundTrackeroUser.getEmail(),
      foundTrackeroUser.getCategories(),
      foundTrackeroUser.getAccount().getActualBalance(),
      foundTrackeroUser.getAccount().getSavingsBalance(),
      foundTrackeroUser.getAccount().getId(),
//      userAccountAfterLoginDTO,
      jwtResponse
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
