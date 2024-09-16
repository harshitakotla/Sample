package jdbc;
import java.sql.*;

public class Update {
    public static void main(String args[]) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/harshi", "root", "1316");

       
        PreparedStatement st = conn.prepareStatement("UPDATE student set name=? WHERE id=?");

        
        st.setString(1, "Kotla Harshita");
        st.setInt(2,169);
        st.setString(1, "mouni");
        st.setInt(2,132);
       
       
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
