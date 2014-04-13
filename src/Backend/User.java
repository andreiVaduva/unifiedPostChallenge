package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;

public class User {
	
	public String username,passwd;
	public String firstname,lastname;
	public int groupId;
	public int isAdmin;
	
	public User(String username,String passwd, String firstname,String lastname, int groupId, int isAdmin) {
		this.username = username;
		this.passwd = passwd;
		this.firstname = firstname;
		this.lastname = lastname;
		this.groupId = groupId;
		this.isAdmin = isAdmin;
	}
	public String toString() {
		return firstname + " " + lastname;
	}
	
	public int calcPoints() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		int points = -1;
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "SELECT Points FROM UserGroup WHERE ID = ?";
			st = conn.prepareStatement(query);
			st.setInt(1,groupId);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				points = res.getInt("Points");
			}
			conn.close();
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		int points_sum = 0;
		DefaultListModel<Product> plist = listProductUser();
		int size = plist.size();
		for(int i = 0; i< size; i++) {
			points_sum += plist.elementAt(i).cost;
		}
		return points - points_sum;
	}
	
	public void addProductUser(String productname) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		int prodid = -1,userid = -1;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			
			String query = "SELECT ID FROM Product WHERE Name = ?";
			st= conn.prepareStatement(query);
			st.setString(1,productname);
			ResultSet res = st.executeQuery();
			while(res.next()) 
				prodid = res.getInt("ID");
			
			query = "SELECT ID FROM User WHERE Username = ?";
			st= conn.prepareStatement(query);
			st.setString(1,username);
			res = st.executeQuery();
			while(res.next()) 
				userid = res.getInt("ID");
			
			query = "INSERT INTO UserProduct (User_id,Product_id)"
					+ "VALUES (?,?)";
			
			st = conn.prepareStatement(query);
			st.setInt(1,userid);
			st.setInt(2,prodid);
			int result = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public DefaultListModel<Product> listProductUser() {
		DefaultListModel<Product> listG = new DefaultListModel<Product>();
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		int userId = -1;
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "SELECT ID FROM User WHERE Username = ?";
			st = conn.prepareStatement(query);
			st.setString(1,username);
			ResultSet res = st.executeQuery();
			while(res.next())
				userId = res.getInt("ID");
			
			query = "SELECT P.* FROM Product P JOIN UserProduct UP ON P.ID = UP.Product_id WHERE UP.user_id = ?";
			st = conn.prepareStatement(query);
			st.setInt(1,userId);
			res = st.executeQuery();
			while(res.next()) {
				Product p = new Product(res.getInt("Category_id"),res.getInt("Cost"),res.getString("Name"));
				listG.addElement(p);
			}
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return listG;
	}
	

}
