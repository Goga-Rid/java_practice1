package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection;
    Dotenv dotenv =  Dotenv.load();
    String url = dotenv.get("DB_URL");
    String user = dotenv.get("DB_USERNAME");
    String password = dotenv.get("DB_PASSWORD");

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to database");
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Disconnected from database");
        }
    }
}
