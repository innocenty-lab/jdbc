package com.enigmacamp.simplejdbc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataSourceFactory {
    private static Connection conn = null;

    private static void createConnection() throws SQLException {
        String dbHost = "localhost";
        String dbPort = "5432";
        String dbName = "enigma";
        String dbUser = "postgres";
        String dbPassword = "12345";

        String dbURL = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);
        conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

//        transaction
        conn.setAutoCommit(false);
    }

    public static Connection getConn() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        return conn;
    }

    public static void connClose() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
