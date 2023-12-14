package com.codecool.dto.user;

import com.codecool.entity.TransactionCategory;
import com.codecool.entity.security.JwtResponse;

import java.sql.Timestamp;
import java.util.List;

public record UserDataAfterLoginDTO(
  int id,
  Timestamp dateOfRegistration,
  String userName,
  String email,
  List<TransactionCategory> categories,
//  UserAccountAfterLoginDTO accountData,
  JwtResponse jwtResponse
) {}
