package com.codecool.dao;

import java.util.UUID;

public interface UserDAO {
    void deleteUser(UUID uuid);
    void updatePassword(UUID uuid);
    void updateEmail(UUID uuid);
    void updateUsername(UUID uuid);
    void addUser(String email, String username, boolean isAdmin);
}
