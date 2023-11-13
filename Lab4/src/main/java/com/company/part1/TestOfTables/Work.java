package com.company.part1.TestOfTables;

import com.company.part1.Connection.JDBC;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class Work {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Statement stmt = null;
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            System.out.println("1) Выборка всех авторов\n2)Добавить издателя\n3)Изменить имя издaтеля\n4)Книги определенного издателя\n5)Добавление автора\n6)Обновить имя автора\n7)Добавить книгу\n8)Добавить authorISBN\n0)Выход");
            while (true) {
                System.out.printf("Ваш выбор: ");
                int ch = scanner.nextInt();
                switch (ch) {
                    case 1:
                        QueryAuthors(stmt);
                        break;
                    case 2:
                        AddPublisher(stmt, scanner);
                        break;
                    case 3:
                        ChangePublisherName(stmt, scanner);
                        break;
                    case 4:
                        QueryTitles(stmt, scanner);
                        break;
                    case 5:
                        AddAuthor(stmt, scanner);
                        break;
                    case 6:
                        UpdateAuthor(stmt, scanner);
                        break;
                    case 7:
                        AddTitle(stmt, scanner);
                        break;
                    case 8:
                        AddISBN(stmt, scanner);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Неверный выбор");
                }
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block, used to close resources
            JDBC.close();
            scanner.close();
        }
    }

    public static void QueryAuthors(Statement statement) {
        String query = "select * from authors order by firstName, lastName";
        System.out.println("Authors:");
        try {
            ResultSet resultSet = statement.executeQuery(query);
            System.out.printf("%-15s%-15s%-15s\n", "ID", "Имя", "Фамилия");
            System.out.println("---------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("authorID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                System.out.printf("%-15d%-15s%-15s\n", id, firstName, lastName);
            }
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }
    }

    public static void AddPublisher(Statement statement, Scanner scanner) {
        System.out.printf("Введите имя издателя: ");
        String name = scanner.nextLine();
        String query = "insert into publishers (publisherName) values ('" + name + "')";
        try {
            statement.executeUpdate(query);
            System.out.println("Издатель добавлен");
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }
    }

    public static void ChangePublisherName(Statement statement, Scanner scanner) {
        String query = "select * from publishers";
        System.out.println("Publishers:");
        try {
            ResultSet resultSet = statement.executeQuery(query);
            System.out.printf("%-5s%-15s\n", "ID", "Имя");
            System.out.println("----------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("publisherID");
                String name = resultSet.getString("publisherName");
                System.out.printf("%-5s%-15s\n", id, name);
            }
            System.out.printf("Введите ID издателя для изменения: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("Введите новое имя: ");
            String name = scanner.nextLine();
            String update = "update publishers set publisherName = '" + name + "' where publisherID = '" + id + "'";
            int rowsAffected = statement.executeUpdate(update);
            if (rowsAffected > 0) {
                System.out.println("Имя издателя успешно изменено.");
            } else {
                System.out.println("Издатель с указанным ID не найден.");
            }

        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }
    }

    public static void QueryTitles(Statement statement, Scanner scanner) {
        System.out.printf("Введите ID издателя для поиска его книг: ");
        int publisherID = scanner.nextInt();
        String query = "select * from titles where publisherID = '" + publisherID + "' order by title";
        System.out.println("Titles:");
        try {
            ResultSet resultSet = statement.executeQuery(query);
            System.out.printf("%-15s%-50s%-10s%-10s%-10s%-10s\n", "ISBN", "Название", "Номер", "Год", "ID издателя", "Цена");
            System.out.println("------------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                int editionNumber = resultSet.getInt("editionNumber");
                String year = resultSet.getString("year");
                int publisherid = resultSet.getInt("publisherID");
                double price = resultSet.getDouble("price");
                System.out.printf("%-15s%-50s%-10s%-10s%-10s%-10s\n", isbn, title, editionNumber, year, publisherid, price);
            }
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }
    }

    public static void AddAuthor(Statement statement, Scanner scanner) {
        scanner.nextLine();
        System.out.printf("Введите имя автора: ");
        String firstName = scanner.nextLine();
        scanner.nextLine();
        System.out.printf("Введите фамилию автора: ");
        String secondName = scanner.nextLine();
        String query = "insert into authors (firstName, lastName) values ('" + firstName + "','" + secondName + "')";
        try {
            statement.executeUpdate(query);
            System.out.println("Автор добавлен");
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }
    }

    public static void UpdateAuthor(Statement statement, Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.printf("Введите ID автора для изменения: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("Введите новое имя: ");
            String name = scanner.nextLine();
            String update = "update authors set firstName = '" + name + "' where authorID = '" + id + "'";
            int rowsAffected = statement.executeUpdate(update);
            if (rowsAffected > 0) {
                System.out.println("Имя автора успешно изменено.");
            } else {
                System.out.println("Автор с указанным ID не найден.");
            }

        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }
    }

    public static void AddTitle(Statement statement, Scanner scanner) {
        scanner.nextLine();
        System.out.printf("Введите ISBN: ");
        String isbn = scanner.nextLine();
        System.out.printf("Введите название: ");
        String title = scanner.nextLine();
        System.out.printf("Введите номер: ");
        int editionNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("Введите год выпуска: ");
        String year = scanner.nextLine();
        System.out.printf("Введите имя издателя: ");
        String name = scanner.nextLine();
        System.out.printf("Введите цену: ");
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine();
        String query = "insert into titles (isbn, title, editionNumber, year, publisherID, price) values ('" + isbn + "','" + title + "','" + editionNumber + "','" + year + "', (select publisherID from publishers where publisherName = '" + name + "'), '" + price + "')";
        try {
            statement.executeUpdate(query);
            System.out.println("Книга добавлена");
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }
    }

    public static void AddISBN(Statement statement, Scanner scanner) {
        scanner.nextLine();
        System.out.printf("Введите имя автора: ");
        String firstName = scanner.nextLine();
        System.out.printf("Введите фамилию автора: ");
        String secName = scanner.nextLine();
        System.out.printf("Введите ISBN: ");
        String isbn = scanner.nextLine();
        String query = "insert into authorisbn (authorID, isbn) values ((select authorID from authors where firstName = '" + firstName + "' and lastName = '" + secName + "'), '" + isbn + "')";
        try {
            statement.executeUpdate(query);
            System.out.println("Книга добавлена");
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }
    }
}
