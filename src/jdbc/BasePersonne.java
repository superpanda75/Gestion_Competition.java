package jdbc;

import java.sql.*;
import java.util.*;
import inscriptions.*;


public class BasePersonne {
	private static Map<Integer, Personne> personnes = new TreeMap<>();	
	private final static Connection c = Base.getConnexion();


	public BasePersonne()
	{

	}
	
	 public static Personne getPersonne(int id)
	 {
		 return personnes.get(id);
	 }
	
	//MODIFIE LE NOM, PRENOM, MAIL du candidat(personne)-> fonctionne PROCEDURE STOCKEE
	public static void updatePers(Personne personne)
	{
		try 
		{
			String sql = "{call modifPersonne(?, ?, ?, ?)}";
        	java.sql.CallableStatement smt = c.prepareCall(sql);
        	smt.setInt(1,personne.getId());
        	smt.setString(2,personne.getNom());
        	smt.setString(3,personne.getPrenom());
        	smt.setString(4,personne.getMail());
        	smt.executeUpdate();
		}
        						
        catch (SQLException e)
        {
        	e.printStackTrace();
        }
	}
	// supprime l'id de la personne et l'id candidat -> fonctionne PROCEDURE STOCKEE
	public static void suppPersonne(Personne personne)
	{
		try 
		{
			String sql = "{call suppPersonne( ? )}";
			java.sql.CallableStatement smt = c.prepareCall(sql);
			smt.setInt(1,personne.getId());
			smt.executeUpdate(); 	
	    } 
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("La personne n'a pas été supprimé.");
	    }
	}
	 
		 //FONCTIONNE AJOUTER PERSONNE & candidat (on recupere le prenom de la classe mere personne et le nm pour la classe fille candidat )->notion héritage
		public static void sauvegarder(Personne personne){
			 try{
					String sql = "{call addPers(?, ?, ?)}";
					// TODO faire pareil pour personne -> sauvegarder Personne
					java.sql.CallableStatement smt = c.prepareCall(sql);
					smt.setString(1, personne.getNom());
					smt.setString(2, personne.getPrenom());
					smt.setString(3, personne.getMail());
					smt.executeUpdate(); 
					personne.setId(smt.RETURN_GENERATED_KEYS);
					
			 }
			 catch(SQLException e){
				System.out.print(e.getMessage());
			 }
		}
		//AFFICHER CANDIDAT de type personne --> fonctionne 
		 public static /*SortedSet<Candidat>*/ void SelectPers(Inscriptions inscription){
			 try{
				String query="SELECT c.id_candidat, c.nom_candidat, p.prenom_personne, p.mail_personne "
							+"FROM java_candidat c, java_personne p "
							+"WHERE c.id_candidat = p.id_personne";
				 Statement smt = c.createStatement();
				 ResultSet rs = smt.executeQuery(query);
				 while(rs.next())
					{
					 Candidat leCandidat = inscription.createPersonne(rs.getString("nom_candidat"),
					rs.getString("prenom_personne"), rs.getString("mail_personne"));
					 int idPers = rs.getInt("id_candidat");
					 leCandidat.setId(idPers);
					 personnes.put(idPers, (Personne) leCandidat);
					// listePers.add(leCandidat);
					}
				
			}catch(SQLException e){	
				System.out.println(e.getMessage());
		}
		}

}