package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	
public class Base {
	

	public static Connection c;
	
	public Base()
	{
		
	}
	
	
	public static Connection connexion() throws SQLException
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://mysql.m2l.local/ahouri", "ahouri", "azerty");	
			System.out.println("Connexion bien �tablie");
		
			
		}
		catch (ClassNotFoundException e) {
			System.out.println("Pilote JDBC non intall�" );
		}
		
		return c;
	}

	

	public static void main(String[] args) throws SQLException {
		
		
	}
}