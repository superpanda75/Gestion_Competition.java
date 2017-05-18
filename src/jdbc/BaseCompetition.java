package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;

public class BaseCompetition {
	
	public BaseCompetition(){
		
	}
	//AFFICHER CANDIDAT - Equipe --> fonctionne 
	 public static SortedSet<Competition> SelectComp(Inscriptions inscription){
			SortedSet<Competition> SelectComp = new TreeSet();
		 try{
			 String query="SELECT * FROM java_competition";
			Connection c =jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(query);
			 while(rs.next())
				{
				 LocalDate dateCloture = rs.getDate("date").toLocalDate();
				 Competition laCompetition = inscription.createCompetition(rs.getString("nom_competition"), dateCloture, rs.getBoolean("enEquipe"));
				 laCompetition.setId(rs.getInt("id_competition"));
				 SelectComp.add(laCompetition);
				}
			
		}catch(SQLException e){	
			System.out.println(e.getMessage());
	}
		return SelectComp;
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
			public static void deleteCand(Candidat candidat, Competition competition)
			{
				try {
					Connection c =  jdbc.Base.connexion();
					 Statement smt = c.createStatement();
					String requete ="DELETE FROM java_candidat c WHERE c.id_candidat="+candidat.getId()+" AND id_competition="+competition.getNom();
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