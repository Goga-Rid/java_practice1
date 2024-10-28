package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        try {
            dbManager.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}