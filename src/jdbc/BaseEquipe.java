package jdbc;

import java.sql.*;
import java.util.*;
import inscriptions.*;

public class BaseEquipe {
	
	public BaseEquipe(){
		
	} 
	//AFFICHER CANDIDAT - Equipe --> fonctionne 
	 public static SortedSet<Candidat> SelectEquipe(Inscriptions inscription){
			SortedSet<Candidat> listeEquipe = new TreeSet();
		 try{
			 String query="SELECT * FROM java_candidat WHERE id_candidat NOT IN ("
				 		+ " SELECT id_personne FROM java_personne)";
			Connection c =jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(query);
			 while(rs.next())
				{
				 Candidat leCandidat = inscription.createEquipe(rs.getString("nom_candidat"));
				 leCandidat.setId(rs.getInt("id_candidat"));
				 listeEquipe.add(leCandidat);
				}
			
		}catch(SQLException e){	
			System.out.println(e.getMessage());
	}
		return listeEquipe;
	}
	 //Afficher les membres d'une equipe
	 public void selectMembreEquipe(Inscriptions inscriptions){
		 try{
			 for(Equipe e: inscriptions.getEquipes())
			 {
					 Connection c =jdbc.Base.connexion();
					 
					 String sql = "SELECT a.id_personne ";
					 sql += "FROM java_candidat c , java_personne p, java_appartenir a ";
					 sql += "WHERE p.id_personne = c.id_candidat ";
					 sql += "AND c.id_candidat = a.id_personne AND a.id_equipe = " + e.getId();
					 Statement smt = c.createStatement();
					 ResultSet rs = smt.executeQuery(sql);
					 while(rs.next()){
						 for (Personne pers: inscriptions.getPersonnes()) 
				            {
								if(rs.getInt("id_personne") == pers.getId())
								{
									e.add(pers);
								}
	
				            } 
					 }
			 }
		 }catch(SQLException eq){
			 System.out.println(eq.getMessage());
		 } 
	 }
	 //Ajouter un membre 
	 public static void addEquipe(Equipe equipe, Personne personne){
		 try{
			 Connection c =jdbc.Base.connexion();			 
			 String sql = "INSERT INTO java_appartenir(id_equipe, id_personne) "
			 			+ "	VALUES('"+equipe.getId()+"', '"+personne.getId()+"'); ";
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	}
	 //Modifie 
	 //Supprime
	 //Creation d'une equipe (nom_candidat)
	 //remove membre equipe
}