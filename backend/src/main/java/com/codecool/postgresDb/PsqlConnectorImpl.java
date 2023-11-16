package com.codecool.postgresDb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class PsqlConnectorImpl implements PsqlConnector {
    private static final Logger logger = LoggerFactory.getLogger(PsqlConnectorImpl.class);

    public Connection getConnection() {
        Connection conn = null;
        try {
            Map<String, String> env = System.getenv();
            String hostName = env.get("DB_HOST");
            String port = env.get("DB_PORT");
            String databaseName = env.get("DB_NAME");
            String connectionString = "jdbc:postgresql://" + hostName + ":" + port + "/"+ databaseName;
            conn = DriverManager.getConnection(connectionString, env.get("DB_USERNAME"), env.get("DB_PASSWORD"));

            logger.info("Connection to postgresql has been established.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return conn;
    }
}
