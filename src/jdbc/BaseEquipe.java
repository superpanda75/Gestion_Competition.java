package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.SortedSet;

import inscriptions.*;

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
	
		public static void Sauvegarder(SortedSet<Candidat> candidats, Equipe equipe) 
		{	
			try {
				
				Connection c = jdbc.Base.connexion();
				 Statement smt = c.createStatement();
				String requete ="INSERT INTO java_appartenir(id_equipe)VALUES('"+equipe+"') ";
				 int rs = smt.executeUpdate(requete);
				smt.executeUpdate(requete);	
				String requete2 ="Select id_equipe From java_appartenir";
				ResultSet result = smt.executeQuery(requete2);
				int idequipe = 0;
				while (result.next()) {
				    idequipe = result.getInt("id_equipe");
				}
				String requete3 ="INSERT INTO java_candidat(id_candidat,nom_candidat) VALUES ('"+idequipe+"','"+equipe.getNom()+"')";
				smt.executeUpdate(requete3);
				int idCandidat=0;
				String requete4="SELECT id_candidat FROM java_candidat";
				ResultSet result2= smt.executeQuery(requete4);
				while (result2.next()) 
				{
				    idCandidat = result2.getInt( "id_candidat" );
				}
			}  catch (SQLException e) {
				System.out.println(e.getMessage());
			}
					
		}
	

}
