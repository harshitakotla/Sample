package jdbc;
import java.sql.*;

public class Insert {
    public static void main(String args[]) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/harshi", "root", "1316");

        PreparedStatement st = conn.prepareStatement("INSERT INTO student(id, name, sec) VALUES (?, ?, ?)");

        st.setInt(1, 173); 
        st.setString(2, "niha");
        st.setString(3, "G");
        
        

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