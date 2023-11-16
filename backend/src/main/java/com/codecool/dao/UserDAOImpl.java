package com.codecool.dao;

import com.codecool.dao.model.User;
import com.codecool.postgresDb.PsqlConnector;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.UUID;

@Repository
public class UserDAOImpl implements UserDAO{
    private final PsqlConnector psqlConnector;

    public UserDAOImpl(PsqlConnector psqlConnector) {
        this.psqlConnector = psqlConnector;
    }

    private Connection getPsqlConnector(){
        return psqlConnector.getConnection();
    }

    @Override
    public void deleteUser(UUID uuid) {

    }

    @Override
    public void updatePassword(UUID uuid) {

    }

    @Override
    public void updateEmail(UUID uuid) {

    }

    @Override
    public void updateUsername(UUID uuid) {

    }

    @Override
    public User getUser(String email, String password) {
        return null;
    }

    @Override
    public void addUser(String email, String username, boolean isAdmin) {

    }
}
