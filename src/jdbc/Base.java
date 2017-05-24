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
	
	//-> fonctionne la BDD dans le serveur M2L et local
	public static Connection connexion() throws SQLException
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//jdbc:mysql://mysql.m2l.local/ahouri", "ahouri
			c = DriverManager.getConnection("jdbc:mysql://mysql.m2l.local/hbenromdhane?autoReconnect=true&useSSL=false", "hbenromdhane", "Shaco1994");	
		
			
		}
		catch (ClassNotFoundException e) {
			System.out.println("Pilote JDBC non intallé" );
		}
		catch(SQLException sql){
            System.out.println("SQLException: " + sql.getMessage());
            System.out.println("SQLState: " + sql.getSQLState());
            System.out.println("Erro: " + sql.getErrorCode());
            System.out.println("StackTrace: " + sql.getStackTrace());
            return null;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
		
		return c;
	}

	

	public static void main(String[] args) throws SQLException {
		
	}
}