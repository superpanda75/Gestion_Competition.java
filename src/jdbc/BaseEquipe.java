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
	// fonctionne 
	public static void affichEquipe(){
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
	//AFFICHER UN CANDIDAT QUI EST UNE EQUIPE -> fonctionne 
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
	//INSCRIT CANDIDAT A UNE EQUIPE-> ne fonctionne pas
	public static void addEquipe(Equipe equipe,Inscriptions inscriptions) 
	{	
		try {
			Connection c = jdbc.Base.connexion();
			Statement smt = c.createStatement();
			String requete;
			if(inscriptions.getEquipes().contains(equipe))
			{
				requete ="UPDATE java_candidat SET nom_candidat='"+equipe.getNom()+"' WHERE id_candidat="+equipe.getNom();
				smt.executeUpdate(requete);
			}
			else
			{
				//COMPTE CANDIDAT QUI SONT EN EQUIPE
				requete = "SELECT COUNT(*) AS equipe FROM java_appartenir WHERE id_equipe IS NOT NULL";
				ResultSet result = smt.executeQuery(requete);
				int idequipe = 0;
				while(result.next())
				{
				    idequipe = result.getInt("equipe");
				}
				idequipe++;
				int idpersonne = 0;
				idpersonne++;
				String requete2 ="INSERT INTO java_appartenir(id_equipe, id_personne) VALUES('"+idequipe+"','"+idpersonne+"')";
				smt.executeUpdate(requete2);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		}
	}