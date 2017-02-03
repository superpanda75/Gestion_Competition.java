package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {
	public static void main(String[] args)
	{
		Connection c = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/ppe", user = "root", password = "";
			c = DriverManager.getConnection(url, user, password);
			String req = "select * from personne";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(req);
			while (rs.next())
			{
				System.out.println(rs.getInt(1) + " : " + rs.getString(2));
			}
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			try
			{
				if (c != null)
					c.close();
			}
			catch (SQLException e)
			{
				System.out.println("Impossible de fermer la connection.");
			}
		}
	}
	
	
}
