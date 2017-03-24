package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;

public class BaseCandidat {
	
	//AFFICHER CANDIDAT
	public BaseCandidat(){
		
	}
	//affiche les candidats qui s'inscrivent a la competition
	public static void AffichCandInscriptionCompet(){
		try{
			String sql ="Select * From java_competition co, java_inscription i,java_candidat c WHERE i.id_candidat= c.id_candidat AND i.id_competition = co.id_competition";
			Connection c = Base.connexion();
			Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(sql);
			 System.out.println("Liste des candidats inscrit a une competition");
			 while ( rs.next() )
				{
				 System.out.println(rs.getInt("id_candidat")  +  rs.getString("nom_candidat") + "");
				}
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
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
	public static void SupprimerCand(BaseCandidat baseCandidat){
		try{
			String query = "DELETE FROM java_candidat WHERE id_candidat="+baseCandidat+"";
			Connection c =jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 int rs = smt.executeUpdate(query);
			
			}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
}
