package com.codecool.dto;

public record UpdateProfileDTO(
    String username,
    String email,
    String password
) { }
