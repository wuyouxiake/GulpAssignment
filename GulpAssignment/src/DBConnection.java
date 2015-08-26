import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {
	
	
	private static Connection conn=null;
	private static Properties props =null;
	
	
	public static Connection getConnection(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:testuser/password@localhost";
			//properties for creating connection to Oracle database
		    props = new Properties();
		    props.setProperty("user", "testdb");
		    props.setProperty("password", "password");
		    conn=DriverManager.getConnection(url,props);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return conn;
	}
	public static void closeConnection()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean hasReview(String rest_name, String user_id, Connection conn)
			throws SQLException {
		// judge if the account Number exists in the database
		String sql = "select * from review where restaurant_name = '"
				+ rest_name + "' and user_id = "+ user_id;
		System.out.println(sql);
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		if (result.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasAccount(String user_name, String email, Connection conn)
			throws SQLException {
		// judge if the account Number exists in the database
		String sql = "select * from users where user_name = '"
				+ user_name + "' and email = '"+ email+"'";

		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		if (result.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasRestaurant(String r_name, Connection conn)
			throws SQLException {
		// judge if the account Number exists in the database
		String sql = "select * from restaurant where restaurant_name = '"
				+ r_name +"'";

		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		if (result.next()) {
			return true;
		} else {
			return false;
		}
	}
	
}
