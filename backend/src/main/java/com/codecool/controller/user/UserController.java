package com.codecool.controller.user;

import com.codecool.postgresDb.PsqlConnectorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(PsqlConnectorImpl.class);
    @GetMapping("/")
    public ResponseEntity<String> getUser(){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
