package jdbc;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import inscriptions.Inscriptions;
import inscriptions.Personne;

public class BasePersonne implements Serializable {

	public BasePersonne()
	{

	}
	
	//AFFICHER TOUTES LES PERSONNE
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

		//AFFICHER PERSONNE
		 public static void AffichePersonneNonEquipe()
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
	 
		 //AJOUTER PERSONNE & candidat (on recupere le prenom de la classe mere personne et le nm pour la classe fille candidat )->notion héritage
		 public static void sauvegarder(Personne personne){
			 try{
				 String sql="INSERT INTO java_personne(prenom_personne,mail_personne) VALUES('"+personne.getPrenom()+"','"+personne.getMail()+"')";
				 Connection c = jdbc.Base.connexion();
				 Statement smt = c.createStatement();
				 smt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
					ResultSet rs = smt.getGeneratedKeys();
					while(rs.next())
					{
						sql ="INSERT INTO java_candidat(id_candidat,nom_candidat) VALUES ('"+rs.getInt(1)+"','"+personne.getNom()+"')";
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
		 //MODIFIER UNE PERSONNE
		 public static void ModificationPersonne(Personne personne,Inscriptions inscriptions) 
			{		
					try {
						 Connection c = jdbc.Base.connexion();
						 Statement smt = c.createStatement();
						

						 String requete =" UPDATE java_personne SET prenom_personne='"+personne.getPrenom()+"' WHERE java_personne.id_personne = '"+personne.getPrenom()+"'";
						 smt.executeUpdate(requete);
						 requete ="UPDATE java_candidat SET nom_candidat='"+personne.getNom()+"' WHERE java_candidat.id_candidat='"+personne.getNom()+"'";

						 smt.executeUpdate(requete);
						
					} 
					catch (SQLException e) 
					{
						System.out.println(e.getMessage());
					}
						
			}

		 public static void supprimerP(Personne personne)
			{
				try {
					String query= "DELETE FROM java_personne WHERE id_personne="+personne+"";
					 Connection c = jdbc.Base.connexion();
					 Statement smt = c.createStatement();
					 ResultSet rs = smt.executeQuery(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		 //RECHERCHER UNE PERSONNE
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
		 //MODIFIER UNE PERSONNE
		 public static void ModifP(int id,String nom, String prenom, String email){
			 try{
				 String sql = "UPDATE personne SET nom='"+nom+"', prenom='"+prenom+"', email='"+email+"' WHERE id_p="+id+"";
				Connection c =  jdbc.Base.connexion();
				 Statement smt = c.createStatement();
				 ResultSet rs = smt.executeQuery(sql);
				 System.out.println("Modifications reussi");
			 }catch(SQLException e){
				 System.out.println(e.getMessage());
			 }
		 }
	}
