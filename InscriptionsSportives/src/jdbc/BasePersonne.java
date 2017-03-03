package jdbc;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasePersonne {

	public static ResultSet rs;
	public static String req;
	public static Statement smt;
	public static Connection con;


	public BasePersonne()
	{

	}
	//AFFICHER PERSONNE
	 public static void getPersonnes()
	{
		try {
			 Connection c = jdbc.Base.connexion();
			 String query="SELECT * FROM personne";
			 Statement smt = c.createStatement() ;
			 ResultSet rs = smt.executeQuery(query) ;
				while (rs.next())
				{
					System.out.println(rs.getString("id_p")  + rs.getString("nom") + " :" + rs.getString("prenom"));
				}
			 System.out.println("Voici les personnes");
		}	
		catch (Exception e) {
			System.out.println( e.getMessage() );
		}
	}
	 
	 //AJOUTER PERSONNE
	 public static void AjouterP( String nom, String prenom, String email){
		 try{
			 
			 String query="INSERT INTO personne VALUES( "+nom+","+prenom+", "+email+")";
			 Connection c = jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(query);
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