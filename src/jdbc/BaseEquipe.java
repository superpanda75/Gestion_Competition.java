package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import inscriptions.Equipe;

public class BaseEquipe {
	
	public BaseEquipe(){
		
	} 
	public static void affichPersonneEquipe(){
		try{
			String requete = "SELECT * FROM java_appartenir e, java_personne p  WHERE e.id_equipe = p.id_personne; ";
			Connection c = jdbc.Base.connexion();
			Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(requete);
			 System.out.println("Liste des personne formant les equipes");
			 while (rs.next())
				{
					System.out.println(rs.getInt("prenom_personne") + rs.getString("mail_personne") + "");
				}
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	//AFFICHER UN CANDIDAT QUI EST UNE EQUIPE
	public static void AffichEquipe(){
		try{
			String requete = "SELECT * FROM java_appartenir e, java_candidat c WHERE e.id_equipe = c.id_candidat";
			Connection c = jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(requete);
			 System.out.println("Liste des equipes");
			 while (rs.next())
				{
					System.out.println(rs.getInt("id_candidat") + rs.getString("nom_candidat") + "");
				}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	public static void Sauvegarder(Equipe equipe) 
	{	
		try {
			 Connection c = jdbc.Base.connexion();
			String requete ="INSERT INTO java_candidat(id_candidat, nom_candidat) VALUES (id_candidat, nom_candidat)";
			 Statement smt = c.createStatement();	
			 int rs = smt.executeUpdate(requete);
			String query = "INSERT INTO java_appartenir(id_equipe, id_personne) VALUES (id_equipe, id_personne) ";
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
				
	}

}
