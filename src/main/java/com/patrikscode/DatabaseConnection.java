package com.patrikscode;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL  = "jdbc:postgresql://localhost:5432/bugmonitor";
    private static final String USER = "postgres";
    private static final String PASS = "Zeropo789!";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
            return null;
        }
    }
}