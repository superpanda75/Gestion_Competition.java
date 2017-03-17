package jdbc;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasePersonne {

	public BasePersonne()
	{

	}
	
	//AFFICHER PERSONNE
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

	 
	 //AJOUTER PERSONNE
	 public static void AjouterP(String prenom_personne){
		 try{
			 String sql="INSERT INTO java_personne VALUES("+prenom_personne+")";
			 Connection c = jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 int rs = smt.executeUpdate(sql);
			 System.out.println("Personne bien ajouté!");
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
	 //SUPPRIMER UNE PERSONNE PAR ID
	 public static void supprimerP(int id){
		 try{ 
		 String query= "DELETE FROM personne WHERE id_p="+id+"";
		 Connection c = jdbc.Base.connexion();
		 Statement smt = c.createStatement();
		 ResultSet rs = smt.executeQuery(query);
		 System.out.println("Voici les personnes");
		 }
		 catch(SQLException e){
			 System.out.println(e.getMessage());
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
