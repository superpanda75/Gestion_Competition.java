package jdbc;

import java.sql.*;
import java.util.*;
import inscriptions.*;

public class BaseCandidat {
	public static Base bdd = new Base();
	
	public BaseCandidat(){
		
	}
	//1- AFFICHER CANDIDAT - Equipe --> fonctionne 
		 public static SortedSet<Candidat> SelectCand(Inscriptions inscription){
				SortedSet<Candidat> listeCand = new TreeSet();
				for (Candidat candidat : BasePersonne.SelectPers(inscription)) {
					listeCand.add(candidat);
					candidat.getId();
				}
				for (Candidat candidat : BaseEquipe.SelectEquipe(inscription)) {
					listeCand.add(candidat);
				}

			return listeCand;
		}

	
		 //2- modifie le candidat ->fonctionne
		 public void updateCand(Candidat candidat){
			 try{
					Connection c = bdd.connexion();
					String req = "{call updateCand( ? , ? )}";
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
				 Connection c = bdd.connexion();
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
					 Connection c = bdd.connexion();
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