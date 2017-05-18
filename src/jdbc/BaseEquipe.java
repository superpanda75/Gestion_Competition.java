package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.SortedSet;
import java.util.TreeSet;

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
			
			 for(Equipe equipe: inscriptions.getEquipes()){
				 Connection c =jdbc.Base.connexion();
				 String sql = "SELECT c.nom_candidat FROM java_candidat c,java_appartenir a"
				 		+ " WHERE c.id_candidat = a.id_equipe "
				 		+ " AND a.id_equipe = id_equipe "
				 		+ "	GROUP BY c.id_candidat";
				 PreparedStatement smt = c.prepareStatement(sql);
				 smt.setInt(equipe.getId(), 1);
				 ResultSet rs = smt.executeQuery();
				 while(rs.next()){
					 for (Personne equip: inscriptions.getPersonnes()) 
			            {
							if(rs.getInt("id_candidat") == equip.getId())
							{
								equipe.add(equip);
							}

			            } 
				 }
			 }
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
	 

	
	}