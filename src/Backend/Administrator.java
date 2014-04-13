package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.DefaultListModel;

class MyComparator implements Comparator<Pair> {

	public int compare(Pair o1, Pair o2) {
		// TODO Auto-generated method stub
		return o1.votes - o2.votes;
	}

	
}

public class Administrator extends User{
	
	public Administrator(String username, String passwd, String firstname,
			String lastname, int Group_Id, int isAdmin) {
		super(username, passwd, firstname, lastname, Group_Id, isAdmin);
		// TODO Auto-generated constructor stub
	}

	public void addGroup(String groupname, int points) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "INSERT INTO UserGroup (Name,Points)"
					+ "VALUES (?,?)";
			st = conn.prepareStatement(query);
			st.setString(1,groupname);
			st.setInt(2,points);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void modifyGroupname(String oldgroupname, String newgroupname) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			
			String query = "UPDATE UserGroup SET Name = ? WHERE Name = ?";
			st = conn.prepareStatement(query);
			st.setString(1,newgroupname);
			st.setString(2,oldgroupname);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void modifyGrouppoints(int oldgrouppoints, int newgrouppoints) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			
			String query = "UPDATE UserGroup SET Name = ? WHERE Name = ?";
			st = conn.prepareStatement(query);
			st.setInt(1,newgrouppoints);
			st.setInt(2,oldgrouppoints);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public DefaultListModel<User> listUsers() {
		DefaultListModel<User> listU = new DefaultListModel<User>();
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		User u;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM User");
			while (res.next()) {
				if(res.getInt("isAdmin") == 1) {
					u = new Administrator(res.getString("Username"),res.getString("Password"),res.getString("Firstname"),res.getString("Lastname"),
							res.getInt("Group_Id"),res.getInt("isAdmin"));
				}
				else {
					u = new User(res.getString("Username"),res.getString("Password"),res.getString("Firstname"),res.getString("Lastname"),
						res.getInt("Group_Id"),res.getInt("isAdmin"));}
				listU.addElement(u);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listU;
	}
	

	public DefaultListModel<Group> listGroups() {
		DefaultListModel<Group> listG = new DefaultListModel<Group>();
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		Group g;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM UserGroup");
			while (res.next()) {
				g = new Group(res.getInt("ID"),res.getString("Name"),res.getInt("Points"));
				
				listG.addElement(g);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listG;
	}
	
	public DefaultListModel<Category> listCategory() {
		DefaultListModel<Category> listG = new DefaultListModel<Category>();
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		Category g;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Category");
			while (res.next()) {
				g = new Category(res.getInt("ID"),res.getString("Name"));
				listG.addElement(g);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listG;
	}
	
	public DefaultListModel<Product> listProduct() {
		DefaultListModel<Product> listG = new DefaultListModel<Product>();
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		Product p;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Product");
			while (res.next()) {
				p = new Product(res.getInt("Category_Id"),res.getInt("Cost"),res.getString("Name"));
				listG.addElement(p);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listG;
	}
	
	public void addUser(String username,String passwd,String firstname,String lastname,
			int group_id, int isAdmin) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
	
		//User u = new User(username,passwd,firstname,lastname,group_id,isAdmin);
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "INSERT INTO User (Firstname,Lastname,Password,isAdmin,Group_Id)"
					+ "VALUES (?, ?, ?, ?, ?)";
			st = conn.prepareStatement(query);
			st.setString(1,firstname);
			st.setString(2,lastname);
			st.setString(3, passwd);
			st.setInt(4, group_id);
			st.setInt(5, isAdmin);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
		
	}
	
	public void modifyUser(String changed, String oldfield,String newfield) {
		//aici selectam din baza de date si adaugam modificarile
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "UPDATE User SET " + changed + " = ? WHERE " + changed + " = ?";
			st = conn.prepareStatement(query);
			st.setString(1,newfield);
			st.setString(2,oldfield);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void modifyGroupUser(int oldfield, int newfield) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "UPDATE User SET Group_id = ? WHERE Group_id = ?";
			st = conn.prepareStatement(query);
			st.setInt(1,newfield);
			st.setInt(2,oldfield);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	//public void modifyProduct()
	
	public void removeGroup(String groupname) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "DELETE FROM UserGroup WHERE Name = ?";
			st = conn.prepareStatement(query);
			st.setString(1,groupname);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}	
	}
	
	public void removeUser(String username) {
		//aici selectam din baza de date si adaugam modificarile
				String url = "jdbc:mysql://localhost:3306/";
				String dbName = "ITEC";
				String driver = "com.mysql.jdbc.Driver";
				String userName = "root";
				String password = "denu";
				PreparedStatement st = null;
				Connection  conn = null;
				try {
					Class.forName(driver).newInstance();
					conn = DriverManager.getConnection(url + dbName,userName, password);
					String query = "DELETE FROM User WHERE Username = ?";
					st = conn.prepareStatement(query);
					st.setString(1,username);
					int res = st.executeUpdate();
					conn.close();
				
				} catch (Exception e) {
				e.printStackTrace();
				}
	}
	
	public void removeCategory(String categoryname) {
		//aici selectam din baza de date si adaugam modificarile
				String url = "jdbc:mysql://localhost:3306/";
				String dbName = "ITEC";
				String driver = "com.mysql.jdbc.Driver";
				String userName = "root";
				String password = "denu";
				PreparedStatement st = null;
				Connection  conn = null;
				try {
					Class.forName(driver).newInstance();
					conn = DriverManager.getConnection(url + dbName,userName, password);
					String query = "DELETE FROM Category WHERE Name = ?";
					st = conn.prepareStatement(query);
					st.setString(1,categoryname);
					int  res= st.executeUpdate();
					conn.close();
				
				} catch (Exception e) {
				e.printStackTrace();
				}
	}
	
	public void addCategory(String categoryName) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		
		//User u = new User(username,passwd,firstname,lastname,group_id,isAdmin);
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "INSERT INTO Category (Name)"
					+ "VALUES (?)";
			st = conn.prepareStatement(query);
			st.setString(1,categoryName);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void addProduct(int categoryid,String prodname,int cost) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		
		//User u = new User(username,passwd,firstname,lastname,group_id,isAdmin);
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "INSERT INTO Product (Category_Id,Name,Cost)"
					+ "VALUES (?,?,?)";
			st = conn.prepareStatement(query);
			st.setInt(1,categoryid);
			st.setString(2,prodname);
			st.setInt(3,cost);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void deleteCategory(String categoryName) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		
		//User u = new User(username,passwd,firstname,lastname,group_id,isAdmin);
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "DELETE FROM	Category WHERE Name = ?";
			st = conn.prepareStatement(query);
			st.setString(1,categoryName);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void addProduct(String productName, String cost, int categoryId) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		
		//User u = new User(username,passwd,firstname,lastname,group_id,isAdmin);
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "INSERT INTO Product (Name,CategoryId,Cost)"
					+ "VALUES (?, ?, ?)";
			st = conn.prepareStatement(query);
			st.setString(1,productName);
			st.setInt(2,categoryId);
			st.setString(3, cost);
			
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void modifyProduct(int oldfield, int newfield) {
		//aici selectam din baza de date si adaugam modificarile
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "UPDATE Product SET Cost = ? WHERE Cost = ?";
			st = conn.prepareStatement(query);
			st.setInt(1,newfield);
			st.setInt(2,oldfield);
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public Pair[] calc_voted_products() {
		DefaultListModel<Product> plist = listProduct();
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		String query;
		Pair[] number_vote = new Pair[plist.size()];
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			for(int i = 0; i< plist.size(); i++ ) {
				int prodid = -1;
				int number_voters = 0;
				Product currentP = plist.elementAt(i);
				query = "SELECT ID FROM Product WHERE Name = ?";
				st= conn.prepareStatement(query);
				st.setString(1,currentP.name);
				ResultSet res = st.executeQuery();
				while(res.next()) 
					prodid = res.getInt("ID");
				
				query = "SELECT U.* FROM User U JOIN UserProduct UP ON U.ID = UP.User_id WHERE UP.Product_id = ?" ;
				st = conn.prepareStatement(query);
				
				st.setInt(1,prodid);
				res = st.executeQuery();
				while(res.next()) {
					number_voters ++;
				}
				number_vote[i] = new Pair(plist.get(i).name,number_voters);	
			}
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		Arrays.sort(number_vote,new MyComparator());
		return number_vote;
	}
	
	public DefaultListModel<Product> listProductCategory(int ID) {
		DefaultListModel<Product> prod = new DefaultListModel<Product>(); 
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		
		//User u = new User(username,passwd,firstname,lastname,group_id,isAdmin);
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "SELECT * FROM Product WHERE Category_id = ?";
			st = conn.prepareStatement(query);
			st.setInt(1,ID);
			
			ResultSet res = st.executeQuery();
			while(res.next()) {
				
				Product p = new Product(res.getInt("Category_Id"),res.getInt("Cost"),res.getString("Name"));
				//System.out.println(p.name);
				prod.addElement(p);
			}
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		return prod;
	}
	
	public void deleteProduct(String productName) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ITEC";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "denu";
		PreparedStatement st = null;
		Connection  conn = null;
		
		//User u = new User(username,passwd,firstname,lastname,group_id,isAdmin);
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName,userName, password);
			String query = "DELETE FROM Product WHERE Name = ?";
			st = conn.prepareStatement(query);
			st.setString(1,productName);
			
			int res = st.executeUpdate();
			conn.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
}
