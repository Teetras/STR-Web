package com.company.part1.TestOfTables;

import com.company.part1.Connection.JDBC;

import java.math.BigDecimal;
import java.sql.*;

public class GetAllRows {

    public static void main(String[] args) {
        Statement stmt = null;
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String exampleQuery1 = "SELECT * FROM authors";
            String exampleQuery2 = "SELECT * FROM authorisbn";
            String exampleQuery3 = "SELECT * FROM publishers";
            String exampleQuery4 = "SELECT * FROM titles";

            System.out.println("Authors:");
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            while (rs1.next()) {
                int id = rs1.getInt("authorID");
                String firstName = rs1.getString("firstName");
                String lastName = rs1.getString("lastName");
                System.out.println(id + "\t" + firstName + "\t" + lastName);
            }

            System.out.println("authorISBN:");
            ResultSet rs2 = stmt.executeQuery(exampleQuery2);
            while (rs2.next()) {
                int id = rs2.getInt("authorID");
                String isbn = rs2.getString("ISBN");
                System.out.println(id + "\t" + isbn);
            }

            System.out.println("Publishers:");
            ResultSet rs3 = stmt.executeQuery(exampleQuery3);
            while (rs3.next()) {
                int id = rs3.getInt("publisherID");
                String name = rs3.getString("publisherName");
                System.out.println(id + "\t" + name);
            }

            System.out.println("Titles:");
            ResultSet rs4 = stmt.executeQuery(exampleQuery4);
            while (rs4.next()) {
                String isbn = rs4.getString("isbn");
                String title = rs4.getString("title");
                int editionNumber = rs4.getInt("editionNumber");
                String year = rs4.getString("year");
                int publisherID = rs4.getInt("publisherID");
                BigDecimal price = rs4.getBigDecimal("price");
                System.out.println(isbn + "\t" + title + "\t" + editionNumber + "\t" + title + "\t" + year + "\t" + publisherID + "\t" + price);
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block, used to close resources
            JDBC.close();
        }
    }
}
