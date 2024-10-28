package org.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void addBook (String title, String author, Date publishedDate, String isbn) throws SQLException {
        String sql = "INSERT INTO books (title, author, published_date, isbn) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setDate(3, publishedDate);
            stmt.setString(4, isbn);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("SUCCESSFULLY add book!");
        }
    }

    public List<String> getAllBooks() throws SQLException {
        String sql = "SELECT * FROM books";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet resultSet = stmt.executeQuery()) {
            List<String> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(resultSet.getString("title"));
            }
            return books;
        }
    }

    public String findBookByTitle (String title) throws SQLException {
        String sql = "SELECT * FROM books WHERE title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return "\n" + "id книги:" + resultSet.getString("id") + " || автор книги: " + resultSet.getString("author") + "\n";
                }
            }
        }
        return "not found";
    }

    public String deleteBook (int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        return "SUCCESS delete book";
    }

    public void addReader (String name, String email) throws SQLException {
        String sql = "INSERT INTO readers (name, email) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("SUCCESSFULLY add reader!");
        }
    }

    public List<String> getAllReaders () throws SQLException {
        String sql = "SELECT * FROM readers";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet resultSet = stmt.executeQuery()) {
            List<String> readers = new ArrayList<>();
            while (resultSet.next()) {
                readers.add(resultSet.getString("name"));
            }
            return readers;
        }
    }

    public String findReaderByEmail (String email) throws SQLException {
        String sql = "SELECT * FROM readers WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return "\n" + "id пользователя: " + resultSet.getString("id") + " || Имя: " + resultSet.getString("name") + "\n";
                }
            }
        }
        return "not found";
    }

    public String deleteReader (int id) throws SQLException {
        String sql = "DELETE FROM readers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        return "SUCCESS delete reader";
    }
}

