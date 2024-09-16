package jdbc;
import java.sql.*;

public class Delete {
    public static void main(String args[]) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/harshi", "root", "1316");

        PreparedStatement st = conn.prepareStatement("DELETE FROM student WHERE id=?");

        st.setInt(1, 127);

        int i = st.executeUpdate();

        if (i > 0) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }

        st.close();
        conn.close();
    }
}