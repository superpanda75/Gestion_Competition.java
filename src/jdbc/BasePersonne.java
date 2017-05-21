package jdbc;

import java.sql.*;
import java.util.*;
import inscriptions.*;


public class BasePersonne {

	public BasePersonne()
	{

	}
	//SUPPRIMER 
	/*public static void deletePers(Personne personne, Candidat candidat)
	{
		try 
		{
			String requete = "DELETE FROM java_personne p"
						+"WHERE p.id_personne ="+personne.getId();
			Connection c = jdbc.Base.connexion();
			Statement smt = c.createStatement();
			ResultSet rs = smt.executeQuery(requete);
		
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	//MODIFIE LE NOM, PRENOM, MAIL  de la personne 
	public static void updatePers(Personne personne)
	{
		try 
		{
			String sql = "UPDATE java_personne p, java_candidat c "
						+ "SET p.prenom_personne = "+personne.getPrenom()
						+"p.mail_personne ="+personne.getMail()
						+"c.nom_candidat =" +personne.getNom()
						+ "WHERE c.id_candidat =" +personne.getId();
			Connection c = jdbc.Base.connexion();
			Statement smt = c.createStatement();
			ResultSet rs = smt.executeQuery(sql);			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}*/
	 
		 //FONCTIONNE AJOUTER PERSONNE & candidat (on recupere le prenom de la classe mere personne et le nm pour la classe fille candidat )->notion héritage
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
						System.out.println("Personne bien ajouté!");
					}
					smt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
					ResultSet resultat = smt.getGeneratedKeys();
					while(resultat.next())
					{
						personne.setNom(resultat.getString(1));
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