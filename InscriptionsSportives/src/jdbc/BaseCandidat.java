package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseCandidat {
	
	//AJOUTER CANDIDAT
	public static void addCand(String nom)
	{
		String createCand  = "INSERT INTO candidat (nom) "
				+ "VALUES ('"+ nom +"');";
		Base.connexionExe(createCand);
	}
	
	//MODIFIER CANDIDAT
	public static void modifCand(String nom){
		
	}
	
	//SUPPRIMER CANDIDAT
	public static void suppCand(String nom)
	{

		String nom_c;
		
		nom_c = "call suppidcand("+ nom+")";
		ResultSet rs = Base.connexionQuery(nom_c);
		
		try {
			int id_c = rs.getInt("id_c");
			String req = "DELETE candidat	("+ id_c +","+nom_c+")";
			jdbc.Base.connexionExe(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
