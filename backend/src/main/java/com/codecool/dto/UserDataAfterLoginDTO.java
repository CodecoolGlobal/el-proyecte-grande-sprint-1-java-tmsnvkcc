package com.codecool.dto;

import java.sql.Timestamp;

public record UserDataAfterLoginDTO(
  int id,
  Timestamp dateOfRegistration,
  String userName,
  String email,
  UserAccountAfterLoginDTO accountData
) {}
