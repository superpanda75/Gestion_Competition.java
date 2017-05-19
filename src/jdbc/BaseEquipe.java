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
	 public static void selectMembreEquipe(Inscriptions inscriptions){
		 try{
			 for(Equipe e: inscriptions.getEquipes())
			 {
					 Connection c =jdbc.Base.connexion();
					 
					 String sql = "SELECT c.id_candidat "
					 		+ "FROM java_candidat c , java_personne p, java_appartenir a "
					 		+ "WHERE p.id_candidat = c.id_candidat "
					 		+ "AND a.id_personne = c.id_candidat "
					 		+ "AND a.id_equipe = ? ";
					 PreparedStatement smt = c.prepareStatement(sql);
					 smt.setInt(e.getId(), 1);
					 ResultSet rs = smt.executeQuery(sql);
					 //test
					 while(rs.next()){
						 for (Personne pers: inscriptions.getPersonnes()) 
				            {
								if(rs.getInt("id_candidat") == pers.getId())
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
	 public static void addEquipe(Equipe equipe, Personne personne){
		 try{
			 Connection c =jdbc.Base.connexion();			 
			 String sql = "INSERT INTO java_appartenir(id_equipe, id_personne) "
			 			+ "	VALUES('"+equipe.getId()+"', '"+personne.getId()+"'); ";
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
		 }
}