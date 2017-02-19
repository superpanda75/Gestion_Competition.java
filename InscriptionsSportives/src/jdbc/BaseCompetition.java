package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import inscriptions.Competition;
import inscriptions.Inscriptions;

public class BaseCompetition {
	
	public static ResultSet rs;
	public static String req;
	public static Statement smt;
	public static Connection con;
	static boolean bdd = true;
	static Inscriptions inscription;
	static int i = 0;
	
	// AFFICHER UNE COMPETITION
	public static void afficheComp()
	{
		try {
			req = "SELECT * FROM competition;";
			con = jdbc.Base.connexion();
			smt = con.createStatement();
			rs =smt.executeQuery(req);
			while (rs.next())
		{
				i ++;
				System.out.println(i + " - " + rs.getString("nom_co"));
			}
			i = 0;
		}	
		catch (Exception e) {
		System.out.println( e.getMessage() );
		}
	}
		
	public static void modifcomp(String oldnom, String nom, Competition competition )
	{
		String modifnom;
		int id;
		modifnom = "call getidcomp('"+ competition.getNom() +"')";
		rs = Base.connexionQuery(modifnom);
		try {
		id = rs.getInt(0);
		String modifcomp  = "call modifnomcomp("+id+",'"+ oldnom +"','"+nom+"';";
		jdbc.Base.connexionExe(modifcomp);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
