package jdbc;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class BasePersonne implements Serializable {

	public BasePersonne()
	{

	}
	
	//AFFICHER TOUTES LES PERSONNE -> fonctionne
	 public static void AfficheP()
	{
		 try{
			 
			 String query="SELECT * FROM java_personne WHERE java_personne.id_personne = id_personne";
			 Connection c = jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(query);
			 System.out.println("Liste des personnes");
			 while (rs.next())
				{
					System.out.println(rs.getInt("id_personne")  +  rs.getString("prenom_personne") + "");
				}
			 
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
			 
		 }
		
	}

		//AFFICHER PERSONNE ->fonctionne 
		 public static void AffichePersonneEquipe()
		{
			 try{
				 
				 String query="SELECT * FROM java_personne p, java_appartenir e WHERE p.id_personne = e.id_personne";
				 Connection c = jdbc.Base.connexion();
				 Statement smt = c.createStatement();
				 ResultSet rs = smt.executeQuery(query);
				 System.out.println("Liste des personnes");
				 while (rs.next())
					{
						System.out.println(rs.getInt("id_personne")  +  rs.getString("prenom_personne") + "");
					}
				 
				 
			 }catch(SQLException e){
				 System.out.println(e.getMessage());
				 
			 }
			
		}
		 //  -> ne fonctionne pas
		 public static void InscrirePersonneComp(Personne personne, Competition competition){
			 try{
				 Connection c = jdbc.Base.connexion();
				 String sql = "INSERT INTO java_inscription(id_candidat, id_competition) "
				 		+ " VALUES ('"+personne.getPrenom()+"','"+personne.getMail()+",'"+competition.getNom()+"','"+competition.getNom()+"','"+competition.estEnEquipe()+"',"
				 				+ "'"+competition.estEnEquipe()+"','"+competition.getDateCloture()+"'')";
				 Statement smt = c.createStatement();
				 ResultSet rs = smt.executeQuery(sql);
				 System.out.println("Personne inscrit à '"+competition.getNom()+"'");
			 }catch(SQLException e){
				 System.out.println(e.getMessage());
			 }
		 }
	 
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
//-> ne fonctionne pas

		 public static void supprimerP(Personne personne, Equipe equipe)
			{
				try {
					 Connection c = jdbc.Base.connexion();
					 Statement smt = c.createStatement();
					 String requete ="DELETE FROM java_appartenir a, java_personne p WHERE id_personne=java_personne.id_personne AND id_equipe=id_equipe";
					smt.executeUpdate(requete);	
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
		 //RECHERCHER UNE PERSONNE -> ne fonctionne pas
		 public static void RecherchePersonne(String prenom) {
			 try{
			 String query="SELECT* FROM personne WHERE prenom ='"+prenom+"' ";
			 Connection c = jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(query);
			 System.out.println("Rechercher les personnes");
			 rs.last();
			 int intP = rs.getRow();
			 if(intP != 0 ){
				 System.out.println("Personne trouver");
			 }else{
				 System.out.println("Personne non trouver");
			 }
			 }catch(SQLException e){
				 System.out.println(e.getMessage());
			 }
			 
		 }
		 //MODIFIER UNE PERSONNE -> ne fonctionne pas
 public static void ModifP(Personne personne){
			 
			 String prenom = personne.getPrenom();
			 
			 try
			 {
				 Connection c =  jdbc.Base.connexion();
				 String query = "UPDATE java_personne p SET prenom_personne = "
				 			+ prenom + " WHERE p.id_personne = id_personne";
			
				 Statement smt = c.createStatement();
				 smt.execute(query);
				 
				 System.out.println("Modifications reussi");
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e.getMessage());
			 }
		 }
	}