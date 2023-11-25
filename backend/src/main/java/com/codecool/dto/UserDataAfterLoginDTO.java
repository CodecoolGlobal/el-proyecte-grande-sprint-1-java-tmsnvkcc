package com.codecool.dto;

import com.codecool.entity.Account;

import java.sql.Timestamp;
import java.util.UUID;

public record UserDataAfterLoginDTO(
  UUID uuid,
  Timestamp dateOfRegistration,
  String user_name,
  String email,
  Account account
) {}
