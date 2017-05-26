package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

	
public class Base {

	public static Connection c;
	public static Class cl;
	
	public Base()
	{
		
	}
	
	//-> fonctionne la BDD dans le serveur M2L et local
	public static Connection connexion() throws SQLException
	{
		try{
			cl = Class.forName("com.mysql.jdbc.Driver") ;
			Driver pilote = (Driver)cl.newInstance() ;
			DriverManager.registerDriver(pilote);
			//jdbc:mysql://localhost:3306/java", "root", ""
			//jdbc:mysql://mysql.m2l.local/ahouri", "ahouri", "azerty"
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?autoReconnect=true&useSSL=false", "root", "");	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Erreur" + e.getMessage() );
		}
		return c;
	}
	
	public static void connexionExe(String req)
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
		Connection c = null;
		try {
			connexion();
			String sql = req ;
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