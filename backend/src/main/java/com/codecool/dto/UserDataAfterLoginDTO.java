package com.codecool.dto;

import com.codecool.entity.TransactionCategory;

import java.sql.Timestamp;
import java.util.List;

public record UserDataAfterLoginDTO(
  int id,
  Timestamp dateOfRegistration,
  String userName,
  String email,
  List<TransactionCategory> categories,
  UserAccountAfterLoginDTO accountData
) {}
