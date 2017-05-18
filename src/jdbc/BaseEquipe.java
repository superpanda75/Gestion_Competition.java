package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.SortedSet;
import java.util.TreeSet;

import inscriptions.*;

public class BaseEquipe {
	
	public BaseEquipe(){
		
	} 
	// fonctionne 
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
	
	}