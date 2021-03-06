package jdbc;

import java.sql.*;
import inscriptions.*;

public class BaseCandidat {
	private final static Connection c = Base.getConnexion();

	public BaseCandidat(){

	}


	// TODO faire de m�me pour mettre en relation les �quipes et les personnes -> fais 
	public static void inscritCandidats(Inscriptions inscription){
		try{
			String query="SELECT * "
					+"FROM java_inscription";
			Statement smt = c.createStatement();
			ResultSet rs = smt.executeQuery(query);
			while(rs.next())
			{	
				Competition laCompetition = jdbc.BaseCompetition.getCompetition(rs.getInt(2));
				Personne laPersonne = jdbc.BasePersonne.getPersonne(rs.getInt(1));
				if (laPersonne != null)
					laCompetition.add(laPersonne);						 
				else
				{
					Equipe lEquipe = jdbc.BaseEquipe.getEquipe(rs.getInt(1));
					if (lEquipe == null)
						throw new RuntimeException("Impossible de trouver le candidat num�ro " + rs.getInt(1));
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


	//2- modifie le candidat ->fonctionne
	// TODO en doublon avec BaseEquipe -> fait -- supprimer car doublon
	//		 public static void modifEquipe(Candidat candidat){
	//			 try{
	//				 String sql = "{call updateCand( ? , ? )}";
	//				 java.sql.CallableStatement smt = c.prepareCall(sql);
	//		         smt.setInt(1, candidat.getId());
	//		         smt.setString(2, candidat.getNom());
	//		         smt.executeUpdate();
	//			 }catch(SQLException e){
	//				 System.out.println(e.getMessage());
	//			 }
	//		 }
	//		 
	//		 //3- supprime le candidat -> fonctionne 
	//		 public void deleteCandidat(Candidat candidat){
	//			 try{
	//					String req = "{call deleteCand( ? )}";
	//					java.sql.CallableStatement smt = c.prepareCall(req);
	//					smt.setInt(1,candidat.getId());
	//					smt.executeUpdate(); 
	//				 
	//			 }catch(SQLException e){
	//				 System.out.println(e.getMessage());
	//			 }
	//		 }


}