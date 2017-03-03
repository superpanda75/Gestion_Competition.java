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
			System.out.println("Connexion bien établie");
		
			
		}
		catch (ClassNotFoundException e) {
			System.out.println("Pilote JDBC non intallé" );
		}
		
		return c;
	}

	public static void connexionExe (String req)
	{
		try {
			connexion();
			String sql = req  ;
			Statement smt = c.createStatement() ;
			smt.executeUpdate(sql) ;
		}  catch (Exception e) {
			System.out.println( e.getMessage() );
		}
	}
	


	public static ResultSet connexionQuery (String req)
	{

		try {
			connexion();
			String sql = req  ;
			Statement smt = c.createStatement() ;
			ResultSet rs = smt.executeQuery(sql) ;
			return rs;
		}
		catch (Exception e) {
			System.out.println( e.getMessage() );
		}

		finally
		{
			try {
				c.close();
			} catch (Exception e) {
				
				
			}
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		
		
	}
}