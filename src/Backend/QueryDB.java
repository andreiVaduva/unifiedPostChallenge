package Backend;
import java.sql.*;

import javax.swing.JOptionPane;

public class QueryDB {
	
	
	public static User isUser(String username,String passwd) {
		
		String firstname = null;
		String lastname = null;
		int Group_id = -1;
		int isAdmin = -1;
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		boolean found = false;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "SELECT *FROM User WHERE Username = ? and Password = ?";
			
			st = conn.prepareStatement(query);
			st.setString(1,username);
			st.setString(2,passwd);
			ResultSet res = st.executeQuery();
			if (res.first()){
				firstname = res.getString("Firstname");
				lastname = res.getString("Lastname");
				Group_id = res.getInt("Group_id");
				isAdmin = res.getInt("isAdmin");
				found = true;
		}
			
		conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(found == true) {
			User u;
			if(isAdmin == 1)
				u = new Administrator(username,passwd,firstname,lastname,Group_id,isAdmin);
			else 
				u = new User(username,passwd,firstname,lastname,Group_id,isAdmin);
			return u;
		}
		else return null;
	}
	
	//public static ArrayList<User>
}
