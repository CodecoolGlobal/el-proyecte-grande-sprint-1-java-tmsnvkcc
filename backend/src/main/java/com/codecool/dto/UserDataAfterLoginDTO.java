package com.codecool.dto;

import com.codecool.entity.Account;

import java.sql.Timestamp;

public record UserDataAfterLoginDTO(
  int id,
  Timestamp dateOfRegistration,
  String user_name,
  String email,
  Account account
) {}
