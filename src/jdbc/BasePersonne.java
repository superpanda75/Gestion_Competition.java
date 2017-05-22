package jdbc;

import java.sql.*;
import java.util.*;
import inscriptions.*;


public class BasePersonne {

	public BasePersonne()
	{

	}
	//SUPPRIMER 
	public static void deletePers(Personne personne)
	{
		try 
		{
			String requete = "DELETE FROM java_personne p"
						+"WHERE p.id_personne = ?";
			Connection c = jdbc.Base.connexion();
			PreparedStatement smt = c.prepareStatement(requete);
			smt.setInt(1, personne.getId());
			boolean rs = smt.execute(requete);
		
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	//MODIFIE LE NOM, PRENOM, MAIL  de la personne 
	public static void updatePers(Personne personne)
	{
		try 
		{
			String sql = "UPDATE java_personne p "
						+ "SET p.prenom_personne = ?"
						+" p.mail_personne = ?"
						+ "WHERE p.id_personne = ?";
			Connection c = jdbc.Base.connexion();
			PreparedStatement smt = c.prepareStatement(sql);
			smt.setString(1, personne.getPrenom());
			smt.setString(2, personne.getMail());
			smt.setInt(3, personne.getId());
			int rs = smt.executeUpdate(sql);			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	 
		 //FONCTIONNE AJOUTER PERSONNE & candidat (on recupere le prenom de la classe mere personne et le nm pour la classe fille candidat )->notion h�ritage
		public static void sauvegarder(Personne personne){
			 try{
				 String sql="INSERT INTO java_personne(prenom_personne,mail_personne) VALUES('"+personne.getPrenom()+"','"+personne.getMail()+"')";
				 Connection c = jdbc.Base.connexion();
				 Statement smt = c.createStatement();
				 smt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
					ResultSet rs = smt.getGeneratedKeys();
					while(rs.next())
					{
						sql ="INSERT INTO java_candidat(nom_candidat) VALUES ('"+personne.getNom()+"')";
					}
					smt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
					ResultSet resultat = smt.getGeneratedKeys();
					while(resultat.next())
					{
						personne.setId(resultat.getInt(1));
					}
					
			 }
			 catch(SQLException e){
				System.out.print(e.getMessage());
			 }
		 }
		//AFFICHER CANDIDAT --> fonctionne 
		 public static SortedSet<Candidat> SelectPers(Inscriptions inscription){
				SortedSet<Candidat> listePers = new TreeSet();
			 try{
				String query="SELECT C.*, P.prenom_personne, P.mail_personne"
						+ " FROM java_candidat C, java_personne P"
						+ " WHERE id_candidat IN ("
				 		+ " SELECT id_personne FROM java_personne)";
				Connection c =jdbc.Base.connexion();
				 Statement smt = c.createStatement();
				 ResultSet rs = smt.executeQuery(query);
				 while(rs.next())
					{
					 Candidat leCandidat = inscription.createPersonne(rs.getString("nom_candidat"),
							 rs.getString("prenom_personne"), rs.getString("mail_personne"));
					 leCandidat.setId(rs.getInt("id_candidat"));
					 listePers.add(leCandidat);
					}
				
			}catch(SQLException e){	
				System.out.println(e.getMessage());
		}
			return listePers;
		} 

}