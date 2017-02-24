package jdbc;

import inscriptions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasePersonne {

	public static ResultSet rs;
	public static String req;
	public static Statement smt;
	public static Connection con;
	static boolean bdd = true;
	static Inscriptions inscription;

	public BasePersonne()
	{

	}
	//AFFICHER PERSONNE
	 public static void main(String[] args)
	{
		try {
			
			jdbc.Base.connexion();
			req = "SELECT * FROM personne";
			smt = con.createStatement();
			rs = smt.executeQuery(req);
				while (rs.next())
				{
					inscription.createPersonne(rs.getString("nom"), rs.getString("prenom"),rs.getString("email"));
				}
		}	
		catch (Exception e) {
			//System.out.println( e.getMessage() );
		}
	}
}