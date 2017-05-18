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
	
	//affiche les candidats qui s'inscrivent a la competition-> fonctionne 
	public static void AffichCandInscriptionCompet(){
		try{
			String sql ="Select * From java_competition co, java_inscription i,java_candidat c WHERE i.id_candidat= c.id_candidat AND i.id_competition = co.id_competition";
			Connection c = Base.connexion();
			Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(sql);
			 while ( rs.next() )
				{
				 System.out.println(rs.getInt("id_candidat")  +  rs.getString("nom_candidat") + "\n" +  rs.getString("nom_competition")+"");
				}
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	//AFFICHER CANDIDAT --> fonctionne 
	 public static void AffichCand(SortedSet<Candidat> candidats){
		try{
			String query="SELECT * FROM java_candidat";
			Connection c =jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(query);
			 while (rs.next())
				{
					System.out.println( rs.getString("nom_candidat") + "");
				}
			
		}catch(SQLException e){	
			System.out.println(e.getMessage());
	}
	} 
	 //MODIFIER UN CANDIDAT --> ne fonctionne pas
	 public static void ModifierNomCand(Candidat candidat){
		 try{
			 Connection c = jdbc.Base.connexion();
			 String sql = "UPDATE java_candidat SET nom_candidat='"+candidat.getNom()+"'";
			 Statement smt = c.createStatement();
			 int rs = smt.executeUpdate(sql);
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
	//SUPPRIMER UN CANDIDAT --> ne fonctionne pas
	public void SupprimerCand(){
		try{
			String query = "DELETE FROM java_candidat c WHERE c.id_candidat='id_candidat'";
			Connection c =jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 boolean rs = smt.execute(query);
			
			}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
}