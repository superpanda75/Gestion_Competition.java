package jdbc;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import inscriptions.*;
import inscriptions.Competition.*;

public class BaseCompetition {
	private boolean sauvegarder = true;

	public BaseCompetition(){
		
	}
	
	public static void updateComp(Competition competition){
		try{
			String req="UPDATE java_competition "
					+ "SET java_competition.nom_competition = "+competition.getNom()
					+"java_competition.date = "+competition.getDateCloture()
					+"java_competition.enEquipe = "+competition.getEnEquipe()
					+"WHERE java_competition.id_competition = "+competition.getId();
			Connection c =jdbc.Base.connexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(req);
		}catch(SQLException e){
			
		}
	}
	public static void deleteComp(Competition comp)
	{
		try 
		{
			String sql = "{call suppComp( ? )}";
			Connection c =jdbc.Base.connexion();
			java.sql.CallableStatement smt = c.prepareCall(sql);
			smt.setLong(1,comp.getId());
			smt.executeUpdate(); 
	    } 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
	    }
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
	 
	 /*public void selectInscription(Inscriptions inscriptions)
	 {
		 try{
			 for(Competition comp: inscriptions.getCompetitions())
				{
					 Connection c =jdbc.Base.connexion();
					 String sql = "SELECT * "
					 		+ "FROM java_candidat c, java_inscription i "
					 		+ "WHERE i.id_candidat = c.id_candidat "
					 		+ "AND i.id_competition = i.id_competition";
					 Statement smt = c.createStatement();
					 ResultSet rs = smt.executeQuery(sql);
					 while (rs.next())
						{
						 for (Personne p : inscriptions.getPersonnes()) 
								if(rs.getInt("id_candidat") == p.getId())
								{
									comp.add(p);
								}
				            for (Equipe e : inscriptions.getEquipes()) 
				            {
								if(rs.getInt("id_candidat") == e.getId())
								{
									comp.add(e);
								}
							}
				        } 
				}
		 }catch(SQLException e){
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 } 
	 }*/

		 //AJOUTER COMPETITION  --> fonctionne
		 public static void Sauvegarder(Competition competition) 
			{	
				try {
						Connection c =  jdbc.Base.connexion();
						 Statement smt = c.createStatement();
						int equipe;
						if (competition.getEnEquipe())
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
}