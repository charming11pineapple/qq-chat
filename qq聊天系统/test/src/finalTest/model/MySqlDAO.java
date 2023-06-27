package finalTest.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDAO {
	public static Connection getConnection() throws Exception{
		String drivername = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/qqdemo";
		String userName = "root";
		String password = "123456";
		Class.forName(drivername);
		Connection con = DriverManager.getConnection(url,userName,password);
		return con;
	}
	
	public static Connection getConnection(String dataName) throws Exception{
		String drivername = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/" + dataName + "?useUnicode=true&characterEncoding=UTF-8";
		String userName = "root";
		String password = "123456";
		Class.forName(drivername);
		Connection con = DriverManager.getConnection(url,userName,password);
		return con;
	}
}
