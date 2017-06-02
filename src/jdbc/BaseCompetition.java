package jdbc;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import inscriptions.*;
import inscriptions.Competition.*;

public class BaseCompetition {

	private static Map<Integer, Competition> competitions = new TreeMap<>();	
		
		

	//PROCEDURE STOCKEE MODIFIER UNE COMPETITION -> fonctionne
	public static void update(Competition competition)
	{
		try 
		{
			Connection c = Base.getConnexion();
			String sql = "{call modifComp( ? , ? , ? , ? )}";
        	java.sql.CallableStatement smt = c.prepareCall(sql);
        	smt.setString(1,competition.getNom());
        	java.sql.Date date = java.sql.Date.valueOf(competition.getDateCloture());
        	smt.setDate(2,date);
        	smt.setBoolean(3,competition.getEnEquipe());
        	smt.setInt(4,competition.getId());
        	smt.executeUpdate();
		}
        						
        catch (SQLException e)
        {
        	System.out.println(e.getMessage());
        }
	}
	//PROCEDURE STOCKEE SUPPRIMER UNE COMPETITION-> fonctionne
	public static void deleteComp(Competition comp)
	{
		try 
		{
			String sql = "{call suppComp( ? )}";
			Connection c = Base.getConnexion();
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
	 public static void SelectComp(Inscriptions inscription){
			
		 try{
			 String query="SELECT * FROM java_competition";
			 Connection c = Base.getConnexion();
			 Statement smt = c.createStatement();
			 ResultSet rs = smt.executeQuery(query);
			 while(rs.next())
				{
				 LocalDate dateCloture = rs.getDate("date").toLocalDate();
				 Competition laCompetition = inscription.createCompetition(rs.getString("nom_competition"), dateCloture, rs.getBoolean("enEquipe"));
				 int id = rs.getInt("id_competition");
				 laCompetition.setId(id);
				 competitions.put(id, laCompetition);
				}
			
		}catch(SQLException e){	
			System.out.println(e.getMessage());
	}
	}
	 
	 //PROCEDURE STOCKEE -> fonctionne
	 public void selectInscription(Inscriptions inscriptions)
	 {
		 try{
		  Connection c = Base.getConnexion();
			 for(Competition comp: inscriptions.getCompetitions())
				{
					 String sql = "{call inscriptionCandToComp( ? )}";
					 java.sql.CallableStatement smt = c.prepareCall(sql);
					 smt.setInt(1, comp.getId());
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
	 }
	 //PROCEDURE STOCKEE AJOUTER COMPETITION  --> fonctionne
	 public static void Sauvegarder(Competition competition)
		{
			try	
			{
				Connection c = Base.getConnexion();
				String sql = "{call addCompetition( ? , ? , ? )}";
				java.sql.CallableStatement smt = c.prepareCall(sql);
	        	smt.setString(1,competition.getNom());
	        	java.sql.Date date = java.sql.Date.valueOf(competition.getDateCloture());
	        	smt.setDate(2,date);
	        	smt.setBoolean(3,competition.getEnEquipe());
				smt.executeUpdate();	
				competition.setId(smt.RETURN_GENERATED_KEYS);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	 //PROCEDURE STOCKEE -> fonctionne
	 public static void removeCandidatComp(Competition competition, Candidat candidat)
		{
			try 
			{
				Connection c = Base.getConnexion();
				String sql = "{call removeCandToComp( ? , ? )}";
				java.sql.CallableStatement smt = c.prepareCall(sql);
				smt.setInt(1,competition.getId());
				smt.setInt(2,candidat.getId());
				smt.executeUpdate(); 	
		    } 
			catch (SQLException e)
			{
				e.printStackTrace();
		    }
		}
	 
	 public static Competition getCompetition(int id)
	 {
		 return competitions.get(id);
	 }

}