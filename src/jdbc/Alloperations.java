package jdbc;

import java.sql.*;
import java.util.Scanner;

public class Alloperations {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/harshi";
    private static final String USER = "root";
    private static final String PASS = "1316";

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Connected to database");

            while (true) {
                System.out.println("Enter operation (1=Insert, 2=Delete, 3=Update, 4=Read, 5=Exit):");
                int operation = obj.nextInt();

                switch (operation) {
                    case 1:
                        insertData(conn, obj);
                        break;
                    case 2:
                        deleteData(conn, obj);
                        break;
                    case 3:
                        updateData(conn, obj);
                        break;
                    case 4:
                        readData(conn);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid operation. Try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    private static void insertData(Connection conn, Scanner scanner) {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        System.out.println("Enter Name:");
        String name = scanner.next();
        System.out.println("Enter Gender:");
        String gender = scanner.next();
        System.out.println("Enter Phone number:");
        String phoneNumber = scanner.next();

        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employee (id, name, gender, `Phone number`) VALUES (?, ?, ?, ?)")) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, gender);
            pstmt.setString(4, phoneNumber);
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully");
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }

    private static void deleteData(Connection conn, Scanner scanner) {
        System.out.println("Enter ID to delete:");
        int id = scanner.nextInt();

        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Data deleted successfully");
        } catch (SQLException e) {
            System.out.println("Error deleting data: " + e.getMessage());
        }
    }

    private static void updateData(Connection conn, Scanner scanner) {
        System.out.println("Enter ID to update:");
        int id = scanner.nextInt();
        System.out.println("Enter new name:");
        String name = scanner.next();
        System.out.println("Enter new gender:");
        String gender = scanner.next();
        System.out.println("Enter new phone number:");
        String phoneNumber = scanner.next();

        try (PreparedStatement pstmt = conn.prepareStatement("UPDATE employee SET name = ?, gender = ?, `Phone number` = ? WHERE id = ?")) {
            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setString(3, phoneNumber);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Data updated successfully");
        } catch (SQLException e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
    }

    private static void readData(Connection conn) {
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employee")) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Gender: " + rs.getString("gender"));
                    System.out.println("Phone Number: " + rs.getString("Phone number"));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }
}