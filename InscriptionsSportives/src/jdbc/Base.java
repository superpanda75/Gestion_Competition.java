package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	
public class Base {
	
	public static Connection con;
	public static Connection c;
	
	public Base()
	{
		
	}
	
	
	public static Connection connexion() throws SQLException
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://mysql.m2l.local/ahouri", user = "ahouri", password = "azerty";
			c = DriverManager.getConnection(url, user, password);			
			String req = "select * from personne";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(req);
			while (rs.next())
			{
				System.out.println(rs.getString("id_p")  + rs.getString("nom") + " :" + rs.getString("prenom"));
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("Pilote JDBC non intallé" );
		}
		return con;
	}

	public static void connexionExe (String req)
	{
		try {
			connexion();
			String sql = req  ;
			Statement smt = con.createStatement() ;
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
			Statement smt = con.createStatement() ;
			ResultSet rs = smt.executeQuery(sql) ;
			return rs;
		}
		catch (Exception e) {
			System.out.println( e.getMessage() );
		}

		finally
		{
			try {
				con.close();
			} catch (Exception e) {
				
				
			}
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		
		
	}
}