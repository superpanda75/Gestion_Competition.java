package jdbc;

import java.sql.*;
import java.util.*;
import inscriptions.*;

public class BaseEquipe {
	public static Base bdd = new Base();

	
	public BaseEquipe(){
		
	} 
	//AFFICHER CANDIDAT - Equipe --> fonctionne 
	 public static SortedSet<Candidat> SelectEquipe(Inscriptions inscription){
			SortedSet<Candidat> listeEquipe = new TreeSet();
		 try{
			 String query="SELECT * FROM java_candidat WHERE id_candidat NOT IN ("
				 		+ " SELECT id_personne FROM java_personne)";
			Connection c = bdd.connexion();
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
	 //AJOUTER UNE EQUIPE -> fonctionne procedure stockee
	public static void sauvegarder(Equipe equipe)
		{
			try	
			{
				Connection c = bdd.connexion();
				String sql = "{call addEquipe( ? )}";
				java.sql.CallableStatement smt = c.prepareCall(sql);
				smt.setInt(1, equipe.getId());
				smt.executeUpdate(); 
				equipe.setId(smt.RETURN_GENERATED_KEYS);
			}
			catch (SQLException e)
			{
				System.out.println(e.getMessage());
			}
		}

	 //Afficher les membres d'une equipe -> fonctionne REQUETE 
	 public void selectMembreEquipe(Inscriptions inscriptions){
		 try{
			 for(Equipe e: inscriptions.getEquipes())
			 {
					 Connection c = bdd.connexion();
					 
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
	 //Ajouter un membre -> fonctionne PROCEDURE STOCKEE
	 public static void addMembreEquipe(Equipe equipe, Personne personne){
		 try{
			 Connection c = bdd.connexion();			 
			 String sql = "{call addMembreEquipe( ? , ?)}";
			 java.sql.CallableStatement smt = c.prepareCall(sql);
	         smt.setInt(1,personne.getId());
	         smt.setInt(2,equipe.getId());
			 smt.executeUpdate();
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	}
	 //Modifie le nom de l'equipe -> FONCTIONNE : PROCEDURE STOCKEE
	 public static void modifEquipe(Candidat candidat){
		 try{
			 Connection c = bdd.connexion();			 
			 String sql = "{call updateCand( ? , ? )}";
			 java.sql.CallableStatement smt = c.prepareCall(sql);
	         smt.setInt(1, candidat.getId());
	         smt.setString(2, candidat.getNom());
	         smt.executeUpdate();
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
	 //Supprime l'equipe -> fonctionne PROCEDURE STOCKEE
	 public static void suppEquipe(Candidat candidat){
		 try{
			 Connection c = bdd.connexion();			 
			 String sql = "{call deleteCand( ? )}";
			 java.sql.CallableStatement smt = c.prepareCall(sql);
			 smt.setInt(1,candidat.getId());
			 smt.executeUpdate();	
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
	 //remove membre equipe -> fonctionne PROCEDURE STOCKEE
	 public static void suppMembreEquipe(Equipe equipe, Personne personne){
		 try{
			 Connection c = bdd.connexion();			 
			 String sql = "{call deletePersonneEquipe( ? , ? )}";
			 java.sql.CallableStatement smt = c.prepareCall(sql);
			 smt.setInt(1,equipe.getId());
			 smt.setInt(2,personne.getId());
			 smt.executeUpdate(); 
			 
		 }catch(SQLException e){ 
			 System.out.println(e.getMessage());
		 }
	 }
}