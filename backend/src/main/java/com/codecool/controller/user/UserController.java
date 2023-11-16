package com.codecool.controller.user;

import com.codecool.dto.NewUserDTO;
import com.codecool.postgresDb.PsqlConnectorImpl;
import com.codecool.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    // TODO: delete userId from all endpoint because it will be in token
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private static final Logger logger = LoggerFactory.getLogger(PsqlConnectorImpl.class);

    @GetMapping("/")
    public ResponseEntity<String> getUserInformation(){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Object> registerUser(@RequestBody NewUserDTO user) {
      try {
        userService.createUserAccount(user);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "User was successfully created.");

        return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(map);
      } catch (Exception exception) {
        logger.error(exception.getMessage());

        Map<String, Object> map = new HashMap<>();
        map.put("message", exception.getMessage());

        return ResponseEntity
          .status(HttpStatus.NOT_ACCEPTABLE)
          .body(map);
      }
    }

    @PutMapping("/password-reset")
    public ResponseEntity<String> resetPassword(){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/changeUsername/{userId}")
    public ResponseEntity<String> changeUsername(@PathVariable UUID userId){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/changePassword/{userId}")
    public ResponseEntity<String> changePassword(@PathVariable UUID userId){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/changeEmail/{userId}")
    public ResponseEntity<String> changeEmail(@PathVariable UUID userId){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
