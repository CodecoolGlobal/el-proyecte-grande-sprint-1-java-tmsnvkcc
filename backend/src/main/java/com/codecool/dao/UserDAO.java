package com.codecool.dao;

import com.codecool.dao.model.User;

import java.util.UUID;

public interface UserDAO {
    void deleteUser(UUID uuid);
    void updatePassword(UUID uuid);
    void updateEmail(UUID uuid);
    void updateUsername(UUID uuid);
    User getUser(String email, String password);
    void addUser(String email, String username, boolean isAdmin);
}
