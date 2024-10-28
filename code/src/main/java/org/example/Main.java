package org.example;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        try {
            dbManager.connect();
            dbManager.addBook("Принц и нищий", "Марк Твен", Date.valueOf("1881-01-11"), "2131321");
            System.out.println(dbManager.getAllBooks());
            System.out.println(dbManager.findBookByTitle("Принц и нищий"));
            dbManager.deleteBook(1);
            dbManager.addReader("Goga", "dsfda@gmail.com");
            System.out.println(dbManager.getAllReaders());
            System.out.println(dbManager.findReaderByEmail("dsfda@gmail.com"));
            dbManager.deleteReader(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dbManager.disconnect();
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

    }

}