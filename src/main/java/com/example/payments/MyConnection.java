package com.example.payments;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.*;

public class MyConnection {

    public Connection getConnection() throws SQLException {
        java.sql.Connection conn;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "postgres");
        connectionProps.put("password", "postgres");

        conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Family", connectionProps
        );
        return conn;
    }

}
