package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import inscriptions.Inscriptions;

public class BaseCompetition {
	
	public BaseCompetition(){
		
	}
	
	
	// AFFICHER UNE COMPETITION
	public static void afficheComp()
	{
		try {
			String req = "SELECT * FROM java_competition";
			Connection c = jdbc.Base.connexion();
			Statement smt = c.createStatement();
			ResultSet rs = smt.executeQuery(req);
			System.out.println("Liste des competition");
			while (rs.next())
			{
				System.out.println(rs.getInt("id_competition") + rs.getString("nom_competition"));
			}
		}	
		catch (SQLException e) {
		System.out.println(e.getMessage());
		}
	}
	//MODIFIER UNE COMPETITION
		 public static void ModifP(int id,String nom, String date_cloture, String equipe){
			 try{
				 String sql = "UPDATE Competition SET nom='"+nom+"', date_cloture='"+date_cloture+"', equipe='"+equipe+"' WHERE id_co="+id+"";
				Connection c =  jdbc.Base.connexion();
				 Statement smt = c.createStatement();
				 int rs = smt.executeUpdate(sql);
				 System.out.println("Modifications réussi");
			 }catch(SQLException e){
				 System.out.println(e.getMessage());
			 } 	
		 }

//AJOUTER COMPETITION
	 public static void AjouterComp( String nom, String date_cloture, String equipe){
		 try{
			 
			 String query="INSERT INTO competition VALUES( "+nom+","+date_cloture+", "+equipe+")";
			 Connection c = jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 boolean rs = smt.execute(query);
			 System.out.println("La compétition a bien été ajouté!");
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
			 
		 }
	 }
	 
	 // SUPPRIMER UNE COMPETITION
	 public static void supprimerComp(int id){
		 try{ 
		 String query= "DELETE FROM Competition id_co="+id+"";
		 Connection c = jdbc.Base.connexion();
		 Statement smt = c.createStatement();
		 int rs = smt.executeUpdate(query);
		 System.out.println("Competition supprimé");
		 }
		 catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }

//RECHERCHER UNE PERSONNE
public static void RecherchePersonne(String nom) {
	 try{
	 String query="SELECT* FROM Competition WHERE nom ='"+nom+"' ";
	 Connection c = jdbc.Base.connexion();
	 Statement smt = c.createStatement();
	 ResultSet rs = smt.executeQuery(query);
	 System.out.println("Rechercher les Compétitions");
	 rs.last();
	 int intComp = rs.getRow();
	 if(intComp != 0 ){
		 System.out.println("Compétition trouver");
	 }else{
		 System.out.println("Compétition trouver");
	 }
	 }catch(SQLException e){
		 System.out.println(e.getMessage());
	 }
	 
}
}