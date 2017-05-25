package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	
public class Base {

	private static Connection c;
	private static boolean sauvegarder = false;

	
	public Base()
	{
		
	}
	
	//-> fonctionne la BDD dans le serveur M2L et local
	public Connection connexion() throws SQLException
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//jdbc:mysql://localhost:3306/java", "root", ""
			//jdbc:mysql://mysql.m2l.local/ahouri", "ahouri", "azerty"
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?autoReconnect=true&useSSL=false", "root", "");	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		return c;
	}
	
	public static void setSauvegarder(boolean sauvegarder)
	{
		Base.sauvegarder = sauvegarder;
	}	
	

	public static void main(String[] args) throws SQLException {
	}
}