package com.company.part2.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=UTF-8", "root", "555vita555");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e);
        } catch (SQLException e) {
            System.out.println("SQLException " + e);
        }
        return c;
    }

}
