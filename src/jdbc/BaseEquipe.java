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
	 //AJOUTER UNE EQUIPE
	 /*public static void sauvegarder(Equipe equipe)
		{
			try	
			{
				Connection c =jdbc.Base.connexion();
				String sql = "{call addEquipe( ? )}";
				java.sql.CallableStatement smt = c.prepareCall(sql);
				smt.setInt(1, equipe.getId());
				smt.executeUpdate(); 
				equipe.setId(smt.RETURN_GENERATED_KEYS);
			}
			catch (SQLException e)
			{
				System.out.println(e.getMessage());
				System.out.println("L'équipe n'a pas été créée.");
			}
		}	*/

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
	 public static void addMembreEquipe(Equipe equipe, Personne pers){
		 try{
			 Connection c =jdbc.Base.connexion();			 
			 String sql = "INSERT INTO java_appartenir(id_equipe, id_personne) "
			 			+ "	VALUES('"+equipe.getId()+"', '"+pers.getId()+"')";
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	}
	 //Modifie le nom de l'equipe
	 public static void modifEquipe(Equipe equipe){
		 try{
			 Connection c =jdbc.Base.connexion();			 
			 String sql = " UPDATE java_candidat c "
			 		+ "SET c.nom_candidat "
			 		+ "WHERE id_candidat = "+equipe.getId();
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
	 //Supprime l'equipe
	 public static void suppEquipe(Equipe equipe){
		 try{
			 Connection c =jdbc.Base.connexion();			 
			 String sql = "DELETE FROM java_candidat c "
			 		+ "WHERE c.id_candidat =" +equipe.getId();
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
	 //remove membre equipe
	 public static void suppMembreEquipe(Equipe equipe , Personne pers){
		 try{
			 Connection c =jdbc.Base.connexion();			 
			 String sql = "DELETE FROM appartenir"
			 		+ "WHERE id_equipe=" +equipe.getId()
			 		+ "AND id_personne=" +pers.getId();
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
}