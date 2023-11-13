package com.company.part1.Connection;

import java.util.*;
import java.sql.*;

public class JDBC {

    public static Connection connection = null;
    public static void connect() throws SQLException{
        String url = "jdbc:mysql://127.0.0.1:3306/book3";
        String username = "root";
        String password = "555vita555";
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Успешное подключение к базе данных.");
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }


    public static void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Closing connection");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection!");
        }
    }

}
