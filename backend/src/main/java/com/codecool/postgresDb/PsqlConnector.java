package com.codecool.postgresDb;

import java.sql.Connection;

public interface PsqlConnector {
    Connection getConnection();
}
