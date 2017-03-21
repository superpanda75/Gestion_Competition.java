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
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "");	
			System.out.println("Connexion bien établie");
		
			
		}
		catch (ClassNotFoundException e) {
			System.out.println("Pilote JDBC non intallé" );
		}
		
		return c;
	}

	

	public static void main(String[] args) throws SQLException {
		BaseEquipe.AffichEquipe();
		BasePersonne.AfficheP();
		BasePersonne.AffichePersonneNonEquipe();
		BaseCandidat.AffichCandInscriptionCompet();
		
	}
}