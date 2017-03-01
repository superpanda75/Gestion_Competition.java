package jdbc;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasePersonne {

	public static ResultSet rs;
	public static String req;
	public static Statement smt;
	public static Connection con;
	static boolean bdd = true;


	public BasePersonne()
	{

	}
	//AFFICHER PERSONNE
	 public static void main(String[] args)
	{
		try {
			jdbc.Base.connexion();
			String req = "{call getListeVoirUnePersonne()}";
			
		}	
		catch (Exception e) {
			System.out.println( e.getMessage() );
		}
	}
}