package com.codecool.dto.user;

import java.util.List;

public record AboutMeDTO(
  int id,
  String email,
  String username,
  List<String> userRoles,
  Double actualBalance,
  Double savingsBalance
) {}
