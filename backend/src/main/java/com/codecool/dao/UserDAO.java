package com.codecool.dao;

import com.codecool.entity.User;

import java.util.UUID;

public interface UserDAO {
    void deleteUser(UUID uuid);
    void updatePassword(UUID uuid, String newPassword);
    void updateEmail(UUID uuid, String newEmail);
    void updateUsername(UUID uuid, String newUsername);
    User getUser(String email, String password);
    void addUser(String email, String username, boolean isAdmin);
}
