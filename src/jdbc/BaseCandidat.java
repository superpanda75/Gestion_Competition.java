package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseCandidat {
	
	//AFFICHER CANDIDAT
	public BaseCandidat(){
		
	}
	public static void AffichCand(){
		try{
			String query="SELECT * FROM java_candidat";
			Connection c =jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(query);
			 System.out.println("Liste des candidats");
			 while (rs.next())
				{
					System.out.println(rs.getInt("id_candidat")  +  rs.getString("nom_candidat") + "");
				}
			
		}catch(SQLException e){	
			System.out.println(e.getMessage());
	}
	}
	
}
