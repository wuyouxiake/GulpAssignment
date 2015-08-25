import java.sql.Connection;
import java.sql.DriverManager;
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
	public static  void closeConnection()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
