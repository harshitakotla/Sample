package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Read {
	 public static void main(String args[]) throws Exception {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/harshi", "root", "1316");

	        PreparedStatement st = conn.prepareStatement("SELECT * FROM student");


	        ResultSet rs = st.executeQuery();

	        while(rs.next()) {
	        	int ID=rs.getInt(1);
	        	String name=rs.getString(2);
	        	String section=rs.getString(3);
				System.out.println(ID + " "+ name+" "+section);
	        }
	        st.close();
	        conn.close();
	    }
}
