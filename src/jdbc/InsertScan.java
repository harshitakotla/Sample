package jdbc;
import java.sql.*;
import java.util.Scanner;

public class InsertScan {

    public static void main(String[] args) {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

           
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/harshi", "root", "1316");

            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO student (id, name, sec) VALUES (?,?,?)");
            Scanner obj = new Scanner(System.in);

           
            System.out.print("Enter id: ");
            int id = obj.nextInt();
            obj.nextLine(); 

            System.out.print("Enter name: ");
            String name = obj.nextLine();

            System.out.print("Enter section: ");
            String sec = obj.nextLine();

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, sec);

            int rowsAffected = pstmt.executeUpdate();

          
            if (rowsAffected > 0) {
                System.out.println("Success");
            }

            
            pstmt.close();
            conn.close();
            obj.close();

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL error occurred.");
            e.printStackTrace();
        }
    }
}
