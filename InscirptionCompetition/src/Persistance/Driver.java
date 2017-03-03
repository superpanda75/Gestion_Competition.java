package Persistance;

import java.sql.*;

import inscriptions.Inscriptions;
import inscriptions.Personne;
public class Driver 
{
	public static Connection con;
	public static Class c;
	
	public static Connection connexion () throws ClassNotFoundException
	{
		
		try{
				Driver pilote = (Driver)c.newInstance() ;

				String protocole =  "jdbc:mysql:" ;
				String ip =  "localhost" ;    
				String port =  "3306" ; 
				String nomBase =  "ppe" ;  
				String conString = protocole +  "//" + ip +  ":"
						+ "" + port +  "/" + nomBase ;
				String nom_connexion =  "root" ;  
				String mdp =  "" ;

				con = DriverManager.getConnection(
						conString, nom_connexion, mdp) ;
				System.out.println("Connection Réussi!");	
		}
		catch (Exception e) {
			

			System.out.println("Echec de connexion à la base de donnée" + e.getMessage() );
		}
		return con;
	}

	public static void connexionExe (String req)
	{
		try {
			connexion();
			String sql = req  ;
			Statement smt = con.createStatement() ;
			smt.executeUpdate(sql) ;
		}  catch (Exception e) {
			System.out.println( e.getMessage() );
		}
	}
	


	public static ResultSet connexionQuery (String req)
	{
		Connection con = null;
		try {
			connexion();
			String sql = req  ;
			Statement smt = con.createStatement() ;
			ResultSet rs = smt.executeQuery(sql) ;
			return rs;
		}
		catch (Exception e) {
		// gestion des exceptions
		System.out.println( e.getMessage() );
		}

		finally
		{
			try {
				con.close();
			} catch (Exception e) {
				
				
			}
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
	inscriptions.Inscriptions test1 = inscriptions.Inscriptions.getInscriptions();
		
		Driver.connexionQuery("Select * from candidat ;");
		Driver.connexionExe("insert into candidat (nom_c) values ('LALA')");
		test1.createPersonne("MIMI", "LALA","MAil");
		System.out.print(Persistance.Requete.affEqui());
		
	}

	
	
	

}