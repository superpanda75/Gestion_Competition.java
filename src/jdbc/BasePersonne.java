package jdbc;

import java.sql.*;
import java.util.*;
import inscriptions.*;


public class BasePersonne {
	public static Base bdd = new Base();


	public BasePersonne()
	{

	}
	public static void updatePers(Personne personne)
	{
		try 
		{
			Connection c = bdd.connexion();
			String sql = "{call modifPersonne(?, ?, ?, ?)}";
        	java.sql.CallableStatement smt = c.prepareCall(sql);
        	smt.setInt(1,personne.getId());
        	smt.setString(2,personne.getNom());
        	smt.setString(3,personne.getPrenom());
        	smt.setString(4,personne.getMail());
        	smt.executeUpdate();
		}
        						
        catch (SQLException e)
        {
        	e.printStackTrace();
        }
	}
	public static void suppPersonne(Personne personne)
	{
		try 
		{
			Connection c = bdd.connexion();
			String sql = "{call suppPersonne( ? )}";
			java.sql.CallableStatement smt = c.prepareCall(sql);
			smt.setInt(1,personne.getId());
			smt.executeUpdate(); 	
	    } 
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("La personne n'a pas été supprimé.");
	    }
	}
	 
		 //FONCTIONNE AJOUTER PERSONNE & candidat (on recupere le prenom de la classe mere personne et le nm pour la classe fille candidat )->notion héritage
//		/*public static void sauvegarder(Personne personne){
//			 try{
//				 String sql="INSERT INTO java_personne(id_personne,prenom_personne,mail_personne) VALUES('"+personne.getId()+"','"+personne.getPrenom()+"','"+personne.getMail()+"')";
//				 Connection c = jdbc.Base.connexion();
//				 Statement smt = c.createStatement();
//				 smt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
//					ResultSet rs = smt.getGeneratedKeys();
//					while(rs.next())
//					{
//						sql ="INSERT INTO java_candidat(nom_candidat) VALUES ('"+personne.getNom()+"')";
//					}
//					smt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
//					ResultSet resultat = smt.getGeneratedKeys();
//					while(resultat.next())
//					{
//						personne.setId(resultat.getInt(1));
//					}
//					
//			 }
//			 catch(SQLException e){
//				System.out.print(e.getMessage());
//			 }
//		 }*/
		//AFFICHER CANDIDAT --> fonctionne 
		 public static SortedSet<Candidat> SelectPers(Inscriptions inscription){
				SortedSet<Candidat> listePers = new TreeSet();
			 try{
				String query="SELECT c.id_candidat, c.nom_candidat, p.prenom_personne, p.mail_personne "
							+"FROM java_candidat c, java_personne p "
							+"WHERE c.id_candidat = p.id_personne";
				Connection c =bdd.connexion();
				 Statement smt = c.createStatement();
				 ResultSet rs = smt.executeQuery(query);
				 while(rs.next())
					{
					 Candidat leCandidat = inscription.createPersonne(rs.getString("nom_candidat"),
							 rs.getString("prenom_personne"), rs.getString("mail_personne"));
					 leCandidat.setId(rs.getInt("id_candidat"));
					 listePers.add(leCandidat);
					}
				
			}catch(SQLException e){	
				System.out.println(e.getMessage());
		}
			return listePers;
		} 

}