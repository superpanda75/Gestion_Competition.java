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
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

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
	public static void selectInscritoComp(Inscriptions inscriptions)
	{
		try{
			Connection c =  jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 for (Competition comp : inscriptions.getCompetitions()) 
				{
				 // CANDIDAT = PERSONNE || EQUIPE
				 
				 String query="";
				 ResultSet rs = smt.executeQuery(query);
				 while(rs.next())
			        {
			            for (Personne personne : inscriptions.getPersonnes()) 
							if(rs.getInt("id_candidat") == personne.getId())
							{
								comp.add(personne);
							}
			            for (Equipe equipe : inscriptions.getEquipes()) 
			            {
							if(rs.getInt("id_candidat") == equipe.getId())
							{
								comp.add(equipe);
							}
						}
			        } 
				}   
				 
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
