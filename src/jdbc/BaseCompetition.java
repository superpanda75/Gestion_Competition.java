package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;

public class BaseCompetition {
	
	public BaseCompetition(){
		
	}
	//AFFICHE LES COMPETITION EN EQUIPE  --> fonctionne 
	public static void afficheCompEnEquipe()
	{
		try {
			String req = "SELECT * FROM java_competition co WHERE enEquipe = 1";
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
	
	// AFFICHER UNE COMPETITION --> fonctionne 
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
	//MODIFIER UNE COMPETITION --> ne fonctionne pas
		 public static void ModifNomComp(Competition competition){
			 try{
				 Connection c =  jdbc.Base.connexion();
				 String sql = "UPDATE java_competition SET nom_competition='"+competition.getNom()+"'";
				 Statement smt = c.createStatement();
				 int rs = smt.executeUpdate(sql);
				 System.out.println("Modifications réussi");
			 }catch(SQLException e){
				 System.out.println(e.getMessage());
			 } 	
		 }

		 //AJOUTER COMPETITION  --> fonctionne
		 public static void Sauvegarder(Competition competition) 
			{	
				try {
						Connection c =  jdbc.Base.connexion();
						 Statement smt = c.createStatement();
						int equipe;
						if (competition.estEnEquipe())
							equipe=1;
						else
							equipe = 0;
						String requete ="INSERT INTO java_competition(nom_competition,date,enEquipe) VALUES ('"+competition.getNom()+"','"+competition.getDateCloture()+"','"+equipe+"')";
						smt.executeUpdate(requete);
						int idcomp=0;
						String req="SELECT id_competition FROM java_competition";
						ResultSet resultat= smt.executeQuery(req);
						while (resultat.next()) 
						{
							idcomp = resultat.getInt("id_competition");
						}				
						System.out.println("Competition ajoutée");
					}  catch (SQLException e) {
						System.out.print(e.getMessage());
					}
			}
	 // SUPPRIMER UNE COMPETITION --> ne fonctionne pas
			public static void delete(Candidat candidat, Competition competition)
			{
				try {
					Connection c =  jdbc.Base.connexion();
					 Statement smt = c.createStatement();
					String requete ="DELETE FROM java_inscription WHERE id_candidat="+candidat.getNom()+" AND id_competition="+competition.getNom();
					smt.executeUpdate(requete);	
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				
				}
			}


//RECHERCHER UNE PERSONNE --> ne fonctionne pas 
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
public static void suppComp(Competition competition) {	
	try {		
		String req = "DELETE FROM java_competition WHERE id_competition = '"+competition+"'";
		 Connection c = jdbc.Base.connexion();
		 Statement smt = c.createStatement();
		 smt.executeUpdate(req);
	} catch (SQLException e) {
		System.out.print(e.getMessage());
	}
	
}
}