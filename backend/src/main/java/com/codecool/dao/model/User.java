package com.codecool.dao.model;

import java.util.UUID;

public class User {
    private final int id;
    private final UUID uuid;
    private String username;
    private String email;
    private final int accountId;
    private boolean isAdmin;

    public User(int id, UUID uuid, String username, String email, int accountId, boolean isAdmin) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.accountId = accountId;
        this.isAdmin = isAdmin;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAccountId() {
        return accountId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
