package jdbc;

import inscriptions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class BasePersonne {

	public static ResultSet rs;
	public static String req;
	public static Statement smt;
	public static Connection con;
	static boolean bdd = true;
	static Inscriptions inscription;
	static int i = 0;

	public BasePersonne()
	{

	}
	//AFFICHER PERSONNE
	public static void affichePersonne()
	{
		try {
			req = "SELECT * FROM personne;";
			con = jdbc.Base.connexion();
			smt = con.createStatement();
			rs =smt.executeQuery(req);
			while (rs.next())
			{
				inscription.createPersonne(rs.getString("nom"), rs.getString("prenom"),rs.getString("mail"));
			}
			i = 0;
		}	
		catch (Exception e) {
			System.out.println( e.getMessage() );
		}

	}

	//AJOUTER PERSONNE
	public static void addPers(String nom, String prenom,String mail)
	{

		String createP = "call insertPersonne('" + nom + "','" + prenom + "','" + mail + "');";
		Base.connexionExe(createP);
	}

	
	//SUPPRIMER PERSONNE
	public static void suppPers(String nom, String prenom,String mail)
	{
			
		String idpers = "call getidpers("+ nom+")";
		ResultSet rs = Base.connexionQuery(idpers);
		
		try {
			int idPers = rs.getInt("idPersonne");
			String req = "call suppers("+ idPers +","+nom+")";
			jdbc.Base.connexionExe(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		
	//MODIFIER PRENOM
	public static void modifPren(String oldPrenom, String prenom,Personne personne )
	{
		String modifprenom;
		int id;
		modifprenom = "updatenom('"+ personne.getNom()+"','"+personne.getPrenom()+"','"+personne.getMail()
				+"')";
		rs = Base.connexionQuery(modifprenom);
		try {
		id = rs.getInt(0);
		String updatePers  = "updateprenom("+id+",'"+ prenom +"','"+oldPrenom+"';";
		jdbc.Base.connexionExe(updatePers);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//MODIFIER NOM
	public static void modifnom(String oldnom, String nom,Personne personne )
	{
		String updatenom;
		int id;
		updatenom = "modifidPersonne('"+ personne.getNom()+"','"+personne.getPrenom()+"','"+personne.getMail()
				+"')";
		rs = Base.connexionQuery(updatenom);
		try {
		id = rs.getInt(0);
		String updatePers  = "updatenom("+id+",'"+ nom +"','"+oldnom+"';";
		jdbc.Base.connexionExe(updatePers);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//MODIFIER MAIL
	public static void modifmail(String oldmail, String mail,Personne personne )
	{
		String updatemail;
		int id;
		updatemail= "call updateidpers('"+ personne.getNom()+"',"
				+ "'"+personne.getPrenom()+"','"+personne.getMail()+"')";
		rs = Base.connexionQuery(updatemail);
		try {
		id = rs.getInt(0);
		String updatePers  = "updatemail("+id+",'"+ mail +"','"+oldmail+"';";
		jdbc.Base.connexionExe(updatePers);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	//AJOUTER UNE COMPETITION
		public static void addComp(String nom, LocalDate dateCloture,Boolean enEquipe)
		{
			String createC  = "Call insertCompet ('"+ nom +"','"+dateCloture+"','"+ enEquipe + "');";
			Base.connexionExe(createC);
		}



}