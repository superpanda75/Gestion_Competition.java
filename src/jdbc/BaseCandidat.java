package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.SortedSet;
import java.util.TreeSet;

import inscriptions.*;

public class BaseCandidat {
	
	//AFFICHER CANDIDAT
	public BaseCandidat(){
		
	}
	//AFFICHER CANDIDAT - Equipe --> fonctionne 
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

	
	 //MODIFIER UN CANDIDAT --> ne fonctionne pas
	 public static void ModifierNomCand(Candidat candidat){
		 try{
			 Connection c = jdbc.Base.connexion();
			 String sql = "UPDATE java_candidat c SET c.id_candidat= ? ";
			 PreparedStatement smt = c.prepareStatement(sql);
			 smt.setInt(candidat.getId(), 1);
			 int rs = smt.executeUpdate(sql);
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
	 }
	
}