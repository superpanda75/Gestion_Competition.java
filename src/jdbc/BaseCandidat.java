package jdbc;

import java.sql.*;
import java.util.*;
import inscriptions.*;

public class BaseCandidat {
	public BaseCandidat(){
		
	}
	//1- AFFICHER CANDIDAT - Equipe --> fonctionne 
//	// TODO renommer
//		 public static SortedSet<Candidat> SelectCand(Inscriptions inscription){
//				SortedSet<Candidat> listeCand = new TreeSet<>();
//				for (Candidat candidat : BasePersonne.SelectPers(inscription)) {
//					listeCand.add(candidat);
//					candidat.getId();
//				}
//				for (Candidat candidat : BaseEquipe.SelectEquipe(inscription)) {
//					listeCand.add(candidat);
//				}
//
//			return listeCand;
//		}

	
	
		// TODO faire de même pour mettre en relation les équipes et les personnes
	
		 public static void inscritCandidats(Inscriptions inscription){
			 try{
				String query="SELECT * "
							+"FROM java_inscription";
				Connection c =Base.getConnexion();
				 Statement smt = c.createStatement();
				 ResultSet rs = smt.executeQuery(query);
				 while(rs.next())
					{
					 Competition laCompetition = BaseCompetition.getCompetition(rs.getInt(2));
					 Personne laPersonne = BasePersonne.getPersonne(rs.getInt(1));
					 if (laPersonne != null)
						 laCompetition.add(laPersonne);					 
					 else
					 {
						 Equipe lEquipe = BaseEquipe.getEquipe(rs.getInt(1));
						 if (lEquipe == null)
							 throw new RuntimeException();
						 laCompetition.add(lEquipe);
					 }
					}
				
			}catch(SQLException e){	
				System.out.println(e.getMessage());
		}
		} 

		 
		 //2- modifie le candidat ->fonctionne
		 public void updateCand(Candidat candidat){
			 try{
					Connection c = Base.getConnexion();
					String req = "{call updateCand( ? , ? )}";// TODO en doublon avec BaseEquipe
					java.sql.CallableStatement smt = c.prepareCall(req);
					smt.setInt(1,candidat.getId());
					smt.setString(2, candidat.getNom());
					smt.executeUpdate(); 
			 }catch(SQLException e){
				 System.out.println(e.getMessage());
			 }
		 }
		 //3- supprime le candidat -> fonctionne 
		 public void deleteCandidat(Candidat candidat){
			 try{
				 Connection c = Base.getConnexion();
					String req = "{call deleteCand( ? )}";
					java.sql.CallableStatement smt = c.prepareCall(req);
					smt.setInt(1,candidat.getId());
					smt.executeUpdate(); 
				 
			 }catch(SQLException e){
				 System.out.println(e.getMessage());
			 }
		 }
		 //4- Inscrire un candidat (java_inscription) -> ne fonctionne pas
		 public static void inscCandToComp(Candidat candidat, Competition competition)
			{
				try	
				{
					 Connection c = Base.getConnexion();
					String sql = "{call InscrireCandComp( ? , ? )}";
					java.sql.CallableStatement smt = c.prepareCall(sql);
		        	smt.setObject(1,candidat.getId());
		        	smt.setInt(2,competition.getId());
					smt.executeUpdate();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
	
}