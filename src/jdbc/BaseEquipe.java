package jdbc;

import java.sql.*;
import java.util.*;
import inscriptions.*;

public class BaseEquipe {
	private static Map<Integer, Equipe> equipes = new TreeMap<>();	
	private final static Connection c = Base.getConnexion();


	public BaseEquipe(){
		
	} 
	
	 public static Equipe getEquipe(int id)
	 {
		 return equipes.get(id);
	 }
	//AFFICHER CANDIDAT - Equipe --> fonctionne 
	 public static /*SortedSet<Candidat>*/ void SelectEquipe(Inscriptions inscription){
//			SortedSet<Candidat> listeEquipe = new TreeSet<>();
		 // TODO idem dans Basecandidat et BASECOMPETITION-> fais 
		 try{
			 String query="SELECT * FROM java_candidat WHERE id_candidat NOT IN ("
				 		+ " SELECT id_personne FROM java_personne)";
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(query);
			 while(rs.next())
				{
				 Equipe leCandidat = inscription.createEquipe(rs.getString("nom_candidat"));
				 int id = rs.getInt("id_competition");
				 leCandidat.setId(id);
				 equipes.put(id, leCandidat);
//				 listeEquipe.add(leCandidat);
				 // TODO dans une MAP qui contient les equipes -> fais 
				}
			
		}catch(SQLException e){	
			System.out.println(e.getMessage());
	}
//		return listeEquipe;
	}
	 
	 //AJOUTER UNE EQUIPE -> fonctionne procedure stockee
	public static void sauvegarder(Equipe equipe)
		{
			try	
			{
				// TODO mettre c n attribut de la classe -> fais 
				String sql = "{call addEquipe( ? )}";// TODO passer le nom en paramètre dans la procedure stockee-> fais 
				// TODO faire pareil pour personne -> sauvegarder Personne -> fais
				java.sql.CallableStatement smt = c.prepareCall(sql);
				smt.setString(1, equipe.getNom());
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
					 Connection c = Base.getConnexion();
					 
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
	 public static void addMembreEquipe(Equipe equipe, Personne membre){
		 try{
			 String sql = "{call addMembreEquipe( ? , ?)}";
			 java.sql.CallableStatement smt = c.prepareCall(sql);
	         smt.setInt(1,membre.getId());
	         smt.setInt(2,equipe.getId());
			 smt.executeUpdate();
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	}
	 //Modifie le nom de l'equipe -> FONCTIONNE : PROCEDURE STOCKEE
	 public static void modifEquipe(Candidat candidat){
		 try{
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
			 String sql = "{call deleteCand( ? )}";
			 java.sql.CallableStatement smt = c.prepareCall(sql);
			 smt.setInt(1,candidat.getId());
			 smt.executeUpdate();	
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
	 //remove membre equipe -> fonctionne PROCEDURE STOCKEE
	 public static void suppMembreEquipe(Equipe equipe, Personne membre){
		 try{
			 String sql = "{call deletePersonneEquipe( ? , ? )}";
			 java.sql.CallableStatement smt = c.prepareCall(sql);
			 smt.setInt(1, equipe.getId());
			 smt.setInt(2, membre.getId());
			 smt.executeUpdate(); 
			 
		 }catch(SQLException e){ 
			 System.out.println(e.getMessage());
		 }
	 }
}