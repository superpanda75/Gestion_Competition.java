package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.SortedSet;

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
	//AFFICHER CANDIDAT
	 public static void AffichCand(SortedSet<Candidat> candidats){
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
	//SUPPRIMER UN CANDIDAT
	public static void SupprimerCand(Candidat candidat){
		try{
			String query = "DELETE FROM java_candidat c WHERE c.id_candidat='"+candidat+"'";
			Connection c =jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 int rs = smt.executeUpdate(query);
			 System.out.println("candidat supprimer");
			
			}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
}
