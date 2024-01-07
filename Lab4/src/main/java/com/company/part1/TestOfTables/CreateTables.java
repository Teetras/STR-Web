package com.company.part1.TestOfTables;

import com.company.part1.Connection.JDBC;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateTables {
    public static void main(String[] args) {
        Statement stmt = null;
        try {
            System.out.println("This will DELETE all data, do you want to continue? (y/n) ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            input = input.toLowerCase();

            if (input.equals("y")) {
                JDBC.connect();
                stmt = JDBC.connection.createStatement();
                String drop1 = "DROP TABLE IF EXISTS authors";
                stmt.executeUpdate(drop1);
                String drop2 = "DROP TABLE IF EXISTS titles";
                stmt.executeUpdate(drop2);
                String drop3 = "DROP TABLE IF EXISTS publishers";
                stmt.executeUpdate(drop3);
                String drop4 = "DROP TABLE IF EXISTS authorisbn";
                stmt.executeUpdate(drop4);
                System.out.println("Data deleted");
                String authorsTable = "CREATE TABLE authors " +
                        "(authorID INTEGER NOT NULL AUTO_INCREMENT, " +
                        " firstName CHAR(20), " +
                        " lastName CHAR(20), " +
                        " PRIMARY KEY (authorID))";

                stmt.executeUpdate(authorsTable);
                System.out.println("Created Authors table");

                String titlesTable = "CREATE TABLE titles " +
                        "(isbn CHAR(13) not NULL, " +
                        " title VARCHAR(255), " +
                        " editionNumber INTEGER, " +
                        " year CHAR(4), " +
                        " publisherID INTEGER REFERENCES Publishers(publisherID), " +
                        " price DECIMAL(8,2), " +
                        " PRIMARY KEY (isbn))";

                stmt.executeUpdate(titlesTable);
                System.out.println("Created Titles table");

                String publishersTable = "CREATE TABLE publishers " +
                        "(publisherID INTEGER NOT NULL AUTO_INCREMENT, " +
                        " publisherName CHAR(100), " +
                        " PRIMARY KEY (publisherID))";

                stmt.executeUpdate(publishersTable);
                System.out.println("Created Publishers table");

                String authorISBNTable = "CREATE TABLE authorisbn " +
                        "(authorID INTEGER REFERENCES Authors(authorID), " +
                        " isbn CHAR(10) REFERENCES Titles(isbn))";

                stmt.executeUpdate(authorISBNTable);
                System.out.println("Created authorISBN table");

                InsertTestData.updateTables();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (stmt != null) {
                JDBC.close();
            }

        }
    }
}

