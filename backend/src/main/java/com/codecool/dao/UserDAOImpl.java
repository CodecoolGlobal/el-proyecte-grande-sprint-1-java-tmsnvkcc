package com.codecool.dao;

import com.codecool.entity.User;
import com.codecool.postgresDb.PsqlConnector;
import com.codecool.postgresDb.PsqlConnectorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.UUID;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(PsqlConnectorImpl.class);
    private final PsqlConnector psqlConnector;

    @Autowired
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
    public void updatePassword(UUID uuid, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE uuid = ?";

        try (Connection conn = getPsqlConnector()) {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, uuid.toString()); // TODO: add the UUID to the parameter and replace here

            preparedStatement.executeUpdate();

            logger.info("Updating user's password was successfully");

        } catch (SQLException e) {
            logger.error("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public void updateEmail(UUID uuid, String newEmail) {
        String query = "UPDATE users SET username = ? WHERE uuid = ?";

        try (Connection conn = getPsqlConnector()) {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, uuid.toString());

            preparedStatement.executeUpdate();

            logger.info("Updating user's email was successfully");

        } catch (SQLException e) {
            logger.error("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public void updateUsername(UUID uuid, String newUsername) {
        String query = "UPDATE users SET username = ? WHERE uuid = ?";

        try (Connection conn = getPsqlConnector()) {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, uuid.toString());

            preparedStatement.executeUpdate();

            logger.info("Updating user's username was successfully");

        } catch (SQLException e) {
            logger.error("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public User getUser(String email, String password) {
        return null;
    }

    @Override
    public void addUser(String email, String username, boolean isAdmin) {
      String query =
        """
          INSERT INTO
            users(uuid, username, email, isadmin)
          VALUES
            (?, ?, ?, ?)
         """;

        try (Connection conn = getPsqlConnector();
          PreparedStatement preparedStatement = conn.prepareStatement(query)) {
          preparedStatement.setString(1, "asd");
          preparedStatement.setString(2, username);
          preparedStatement.setString(3, email);
          preparedStatement.setBoolean(4, isAdmin);

          preparedStatement.executeUpdate();
          logger.info("Adding a new user was successful.");
        } catch (SQLException exception) {
          logger.error("Error adding new user: " + exception.getMessage());
          throw new RuntimeException("my error");
        }
    }
}
