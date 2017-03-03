package Persistance;

import inscriptions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;



public class Requete {

	public static ResultSet resultat;
	public static String requete;
	public static Statement smt;
	public static Connection con;
	static boolean db = true;
	static Inscriptions inscription;
	static int cpt = 0;

	public Requete()
	{

	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////Fonctions EXE///////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	public static void addPers(String nom, String prenom,String mail)
	{

		String createP = "call insertPersonne('" + nom + "','" + prenom + "','" + mail + "');";
		Driver.getConnection(createP);
	}

	public static void addEqui(String nom)
	{
		String createE = "call insertEquipe('" + nom + "');";
		Driver.connexionExe(createE);
	}

	public static void addComp(String nom, LocalDate dateCloture,Boolean enEquipe)
	{
		String createC  = "Call insertCompet ('"+ nom +"','"+dateCloture+"','"+ enEquipe + "');";
		Driver.connexionExe(createC);
	}
	
	public static void addToCompetition(String nom, String nomcand )
	{
		String getcomp,getcand;
		int idcand, idcomp;
		
		getcomp = "call getidcomp("+ nom+")";
		getcand = "call getidcand("+ nomcand+")";
		ResultSet rs = Driver.connexionQuery(getcomp);
		ResultSet rs2 = Driver.connexionQuery(getcand);
		try {
			idcand = rs2.getInt(0);
			idcomp = rs.getInt(0);
			String req = "call addtocompete("+ idcand +","+ idcomp+")";
			Persistance.Driver.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	
	
	public static void suppPers(String nom, String prenom,String mail)
	{
			
		String idpers = "call getidpers("+ nom+")";
		ResultSet rs = Driver.connexionQuery(idpers);
		
		try {
			int idPers = rs.getInt("idPersonne");
			String req = "call suppers("+ idPers +","+nom+")";
			Persistance.Driver.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void suppComp(String nom, LocalDate dateCloture, boolean enEquipe)
	{
	
		String idcomp = "call getidcomp("+ nom+")";
		ResultSet rs = Driver.connexionQuery(idcomp);
		
		try {
			int idComp = rs.getInt("idCompetition");
			String req = "call suppcompete("+ idComp +","+nom+")";
			Persistance.Driver.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void suppCand(String nom)
	{
//		String suppC  = "Delete from Candidat where nomCandidat = '"+ nom + "';";
//		String suppP  = "Delete from Personens where nomCandidat = '"+ nom + "';";
		String nomcand;
		
		nomcand = "call getidcand("+ nom+")";
		ResultSet rs = Driver.connexionQuery(nomcand);
		
		try {
			int idcand = rs.getInt("idCandidat");
			String req = "call suppcand("+ idcand +","+nom+")";
			Persistance.Driver.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void suppEqui(String nomequi)
	{
		String getequi = "call getidequi("+ nomequi+")";
		ResultSet rs = Driver.connexionQuery(getequi);
		try {
			int idequi = rs.getInt(0);
			String req = "call suppequi("+ idequi +","+ nomequi+")";
			Persistance.Driver.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String suppE  = "Delete from Equipe where nom = '"+ nom + "';";
//		Base.connexionExe(suppE);
	}
	
	public static void suppInsc(String nom,String nomcand)
	{
		String getcomp,getcand;
		int idcand, idcomp;
		getcomp = "call getidcomp("+ nom+")";
		getcand = "call getidcand("+ nomcand+")";
		ResultSet rs = Driver.connexionQuery(getcomp);
		ResultSet rs2 = Driver.connexionQuery(getcand);
		try {
			idcand = rs2.getInt(0);
			idcomp = rs.getInt(0);
			String req = "call supptocompete("+ idcand +","+ idcomp+")";
			Persistance.Driver.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
///////////////////////////////////////
	
	
	public static void modifPren(String oldPrenom, String prenom,Personne personne )
	{
		String modifprenom;
		int id;
		modifprenom = "call getidpers('"+ personne.getNom()+"','"+personne.getPrenom()+"','"+personne.getMail()
				+"')";
		resultat = Driver.connexionQuery(modifprenom);
		try {
		id = resultat.getInt(0);
		String modifPers  = "call modifprenom("+id+",'"+ prenom +"','"+oldPrenom+"';";
		Persistance.Driver.connexionExe(modifPers);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void modifnom(String oldnom, String nom,Personne personne )
	{
		String modifnom;
		int id;
		modifnom = "call getidpers('"+ personne.getNom()+"','"+personne.getPrenom()+"','"+personne.getMail()
				+"')";
		resultat = Driver.connexionQuery(modifnom);
		try {
		id = resultat.getInt(0);
		String modifPers  = "call modifprenom("+id+",'"+ nom +"','"+oldnom+"';";
		Persistance.Driver.connexionExe(modifPers);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void modifmail(String oldmail, String mail,Personne personne )
	{
		String modifmail;
		int id;
		modifmail= "call getidpers('"+ personne.getNom()+"',"
				+ "'"+personne.getPrenom()+"','"+personne.getMail()+"')";
		resultat = Driver.connexionQuery(modifmail);
		try {
		id = resultat.getInt(0);
		String modifPers  = "call modifprenom("+id+",'"+ mail +"','"+oldmail+"';";
		Persistance.Driver.connexionExe(modifPers);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	

	public static void modifequipe(String oldnom, String nom,Equipe equipe )
	{
		String modifnom;
		int id;
		modifnom = "call getidpers('"+ equipe.getNom() +"')";
		resultat = Driver.connexionQuery(modifnom);
		try {
		id = resultat.getInt(0);
		String modifequi  = "call modifprenom("+id+",'"+ nom +"','"+oldnom+"';";
		Persistance.Driver.connexionExe(modifequi);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public static void modifcomp(String oldnom, String nom, Competition competition )
	{
		String modifnom;
		int id;
		modifnom = "call getidcomp('"+ competition.getNom() +"')";
		resultat = Driver.connexionQuery(modifnom);
		try {
		id = resultat.getInt(0);
		String modifcomp  = "call modifnomcomp("+id+",'"+ oldnom +"','"+nom+"';";
		Persistance.Driver.connexionExe(modifcomp);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public static void modifdate(LocalDate newdate,Competition competition )
	{
		String modifdate;
		int id;
		modifdate = "call getidcomp('"+ competition.getNom() +"')";
		resultat = Driver.connexionQuery(modifdate);
		try {
		id = resultat.getInt(0);
		String modifDate  = "call modifdate("+id+",'"+ competition.getDateCloture() +"','"+newdate+"';";
		Persistance.Driver.connexionExe(modifDate);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public static void addCand(String nom)
	{
		String createCand  = "Insert into Candidat (nom) "
				+ "values ('"+ nom +"');";
		Driver.connexionExe(createCand);
	}



	///////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Fonctions Query//////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////


	public static void affichEquipe()
	{
		
		try {
			requete = "Select * from equipe;";
			con = Persistance.Driver.connexion();
			smt = con.createStatement();
			resultat =smt.executeQuery(requete);
			
			while (resultat.next())
			{
				cpt ++;
				System.out.println(cpt + " - " + resultat.getString( "nomEquipe"));
				
				
				//inscription.createPersonne(rs.getString("nomPersonne"), rs.getString("prenomPersonne"),rs.getString("mail"));
			}
			cpt = 0;
		}	
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
	}
	public static void chargeEquipes(Inscriptions inscriptions)
	{
		try {
			System.out.println("Charge equipe!!!!!!!!!!!!!!!");
			String req = "Select * from Equipe;";
			con = Persistance.Driver.connexion();
			smt = con.createStatement();
			resultat =smt.executeQuery(req);
			while (resultat.next())
				System.out.println("Charge equipe");
				inscriptions.createEquipe(resultat.getString("nomEquipe"),false);
		}
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
	}
	
	public static void affichePersonne(Inscriptions inscriptions)
	{
		try {
			requete = "Select * from personne;";
			con = Persistance.Driver.connexion();
			smt = con.createStatement();
			resultat =smt.executeQuery(requete);
			while (resultat.next())
			{
				cpt ++;
				System.out.println(cpt + " - " + resultat.getString("nomPersonne"));
				inscription.createPersonne(resultat.getString("nomPersonne"), resultat.getString("prenomPersonne"),resultat.getString("mail"));
			}
			cpt = 0;
		}	
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
	}
		
	public static void chargePersonnes(Inscriptions inscriptions)
	{
		try {
			requete = "Select * from personne;";
			con = Persistance.Driver.connexion();
			smt = con.createStatement();
			resultat =smt.executeQuery(requete);
			while (resultat.next())
			{
				
				System.out.println("coucou :!!ChargePersonnes!!!!!!!!");
				inscriptions.createPersonne(resultat.getString("nomPersonne"), resultat.getString("prenomPersonne"),resultat.getString("mail"),false);
			}
		}	
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
	}
	
	public static void afficheComp()
	{
		try {
			requete = "Select * from competition;";
			con = Persistance.Driver.connexion();
			smt = con.createStatement();
			resultat =smt.executeQuery(requete);
		while (resultat.next())
			{
				cpt ++;
				System.out.println(cpt + " - " + resultat.getString("nomcompetition"));
				//inscriptions.createPersonne(rs.getString("nomPersonne"), rs.getString("prenomPersonne"),rs.getString("mail"));
			}
			cpt = 0;
		}	
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
	}
	public static void chargeCompet(Inscriptions inscriptions)
	{
		try {
			requete = "Select * from Competition;";
			con = Persistance.Driver.connexion();
			smt = con.createStatement();
			resultat = smt.executeQuery(requete);
			while (resultat.next())
				System.out.println("Toutes les competitions!");
				inscriptions.resultat(resultat.getString("nomCompetition"), resultat.getDate("dateCloture").toLocalDate(),resultat.getBoolean("enEquipe"),false);
		}
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
	}
	
	
	
	
	
	
	public static ArrayList<String> affEquipe() throws SQLException
	{
		ArrayList<String> a = new ArrayList<>();
		try {
			requete = "Select * from equipe;";
			String b;
			con = Persistance.Driver.connexion();
			smt = con.createStatement();
			resultat =smt.executeQuery(requete);


			while (resultat.next())
			{
				b = resultat.getString("nomEquipe");
				a.add(b);

			}

			for (String value : a)
		{
			System.out.println(value);
		}	
		}
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
		return a;


	}


}