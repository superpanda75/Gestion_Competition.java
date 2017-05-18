package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.SortedSet;
import java.util.TreeSet;

import inscriptions.*;

public class BaseCandidat {
	
	//AFFICHER CANDIDAT
	public BaseCandidat(){
		
	}
	//AFFICHER CANDIDAT - Equipe --> fonctionne 
		 public static SortedSet<Candidat> SelectCand(Inscriptions inscription){
				SortedSet<Candidat> listeCand = new TreeSet();
				for (Candidat candidat : BasePersonne.SelectPers(inscription)) {
					listeCand.add(candidat);
					candidat.getId();
				}
				for (Candidat candidat : BaseEquipe.SelectEquipe(inscription)) {
					listeCand.add(candidat);
				}

			return listeCand;
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
	
	 //MODIFIER UN CANDIDAT --> ne fonctionne pas
	 public static void ModifierNomCand(Candidat candidat){
		 try{
			 Connection c = jdbc.Base.connexion();
			 String sql = "UPDATE java_candidat c SET c.id_candidat= ? ";
			 PreparedStatement smt = c.prepareStatement(sql);
			 smt.setInt(candidat.getId(), 1);
			 int rs = smt.executeUpdate(sql);
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
	//SUPPRIMER UN CANDIDAT --> ne fonctionne pas
	public void SupprimerCand(Candidat candidat){
		try{
			String query = "DELETE FROM java_candidat c WHERE c.id_candidat = ? ";
			Connection c =jdbc.Base.connexion();
			PreparedStatement  smt = c.prepareStatement(query);
			smt.setInt(candidat.getId(), 1);
			boolean rs = smt.execute(query);
			
			}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
}