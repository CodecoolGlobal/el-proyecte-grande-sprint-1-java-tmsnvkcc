package com.codecool.dto.user;

public record UpdateProfileDTO(
  String username,
  String email,
  String password
) {}
