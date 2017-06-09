package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	
public class Base {

	private static Connection c;	
	
	static
	{
		try{
//			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?autoReconnect=true&useSSL=false", "root", "");	
//			c = DriverManager.getConnection("jdbc:mysql://mysql.m2l.local/ahouri?autoReconnect=true&useSSL=false", "ahouri", "azerty");	
//			c = DriverManager.getConnection("jdbc:mysql://mysql.m2l.local/hbenromdhane?autoReconnect=true&useSSL=false", "hbenromdhane", "azerty");	

			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?autoReconnect=true&useSSL=false", "root", "");	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//-> fonctionne la BDD dans le serveur M2L et local
	public static Connection getConnexion()
	{
		return c;
	}
}