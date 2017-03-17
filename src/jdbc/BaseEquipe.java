package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseEquipe {
	
	public BaseEquipe(){
		
	} 
	//AFFICHER UN CANDIDAT QUI EST UN EQUIPE
	public static void AffichEquipe(){
		try{
			String requete = "SELECT * FROM java_appartenir e, java_candidat c WHERE e.id_equipe = c.id_candidat";
			Connection c = jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(requete);
			 System.out.println("Liste des equipes");
			 while (rs.next())
				{
					System.out.println(rs.getInt("id_candidat") + rs.getString("nom_candidat") + "");
				}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

}
