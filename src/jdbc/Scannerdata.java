package jdbc;

import java.sql.*;
import java.util.Scanner;

public class Scannerdata {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/harshi", "root", "1316");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter table name: ");
            String tableName = scanner.nextLine().trim();

            System.out.print("Enter number of columns: ");
            int numColumns = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            String[] columnNames = new String[numColumns];
            String[] columnTypes = new String[numColumns];

            for (int i = 0; i < numColumns; i++) {
                System.out.print("Enter column name " + (i + 1) + ": ");
                columnNames[i] = scanner.nextLine();

                System.out.print("Enter column type " + (i + 1) + " (e.g. INT, VARCHAR): ");
                String columnType = scanner.nextLine();
                if (columnType.equalsIgnoreCase("VARCHAR")) {
                    System.out.print("Enter length for VARCHAR: ");
                    int length = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    columnTypes[i] = "VARCHAR(" + length + ")";
                } else {
                    columnTypes[i] = columnType;
                }
            }

            StringBuilder createTableQuery = new StringBuilder("CREATE TABLE " + tableName + " (");
            for (int i = 0; i < numColumns; i++) {
                createTableQuery.append("`").append(columnNames[i]).append("` ").append(columnTypes[i]);
                if (i < numColumns - 1) {
                    createTableQuery.append(", ");
                }
            }
            createTableQuery.append(")");

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createTableQuery.toString());

            System.out.print("Enter number of rows: ");
            int numRows = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            for (int i = 0; i < numRows; i++) {
                String[] rowValues = new String[numColumns];
                for (int j = 0; j < numColumns; j++) {
                    System.out.print("Enter value for column " + columnNames[j] + " in row " + (i + 1) + ": ");
                    rowValues[j] = scanner.nextLine();
                }

                StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + " (");
                for (int j = 0; j < numColumns; j++) {
                    insertQuery.append("`").append(columnNames[j]).append("`");
                    if (j < numColumns - 1) {
                        insertQuery.append(", ");
                    }
                }
                insertQuery.append(") VALUES (");
                for (int j = 0; j < numColumns; j++) {
                    insertQuery.append("?");
                    if (j < numColumns - 1) {
                        insertQuery.append(", ");
                    }
                }
                insertQuery.append(")");

                PreparedStatement pstmt = conn.prepareStatement(insertQuery.toString());
                for (int j = 0; j < numColumns; j++) {
                    pstmt.setString(j + 1, rowValues[j]);
                }
                pstmt.executeUpdate();
            }
            System.out.println("Success");
            conn.close();
            scanner.close();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL error occurred.");
            e.printStackTrace();
        }
    }
}
