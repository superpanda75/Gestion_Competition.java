package inscriptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

import jdbc.BaseCandidat;
import jdbc.BaseEquipe;
import userDialog.MainMenu;


/**
 * Point d'entrée dans l'application, un seul objet de type Inscription
 * permet de gérer les compétitions, candidats (de type equipe ou personne)
 * ainsi que d'inscrire des candidats à des compétition.
 */

public class Inscriptions implements Serializable
{
	private static final long serialVersionUID = -3095339436048473524L;
	private static final String FILE_NAME = "Inscriptions.srz";
	private static Inscriptions inscriptions;
	private SortedSet<Competition> competitions = new TreeSet<>();
	private SortedSet<Candidat> candidats = new TreeSet<>();
	BaseEquipe baseEquipe = new BaseEquipe();
	public static boolean db = false;



	

	private Inscriptions()
	{
	}
	
	/**
	 * Retourne les compétitions.
	 * @return
	 */
	
	public SortedSet<Competition> getCompetitions()
	{
		jdbc.BaseCompetition.afficheComp();
		return Collections.unmodifiableSortedSet(competitions);
	}
	
	/**
	 * Retourne tous les candidats (personnes et équipes confondues).
	 * @return
	 */
	
	public SortedSet<Candidat> getCandidats()
	{
		jdbc.BaseCandidat.AffichCand(candidats);
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Retourne toutes les personnes.
	 * @return
	 */
	
	public SortedSet<Personne> getPersonnes()
	{
		jdbc.BasePersonne.AfficheP();
		SortedSet<Personne> personnes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Personne)
				personnes.add((Personne)c);
		return Collections.unmodifiableSortedSet(personnes);
	}

	/**
	 * Retourne toutes les équipes.
	 * @return
	 */
	
	public SortedSet<Equipe> getEquipes()
	{
		jdbc.BaseEquipe.AffichEquipe();
		SortedSet<Equipe> equipes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Equipe)
				equipes.add((Equipe)c);
		return Collections.unmodifiableSortedSet(equipes);
	}

	/**
	 * Créée une compétition. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Competition}.
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */
	
	public Competition createCompetition(String nom, 
			LocalDate dateCloture, boolean enEquipe)
	{
		
			Competition competition = new Competition(this, nom, dateCloture, enEquipe);
			jdbc.BaseCompetition.Sauvegarder(competition);
			competitions.add(competition);
			return competition;
	
	}
	

	/**
	 * Créée une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.

	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	//AJOUTER PERSONNE ET CANDIDAT
	public Personne createPersonne(String nom, String prenom, String mail)
	{
		Personne personne = new Personne(this, nom, prenom, mail);
		jdbc.BasePersonne.sauvegarder(personne);
		candidats.add(personne);
		return personne;
	}
	
	public Personne modifPersonne(Personne personne,String nom,String prenom,String mail)
	{
		personne.setPrenom(prenom);
		personne.setNom(nom);
		personne.setMail(mail);
		jdbc.BasePersonne.ModifP(personne);
		return personne;
	}
	
	/**
	 * Créée un Candidat de type équipe. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Equipe}.
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	
	public Equipe createEquipe(String nom)
	{
		Equipe equipe = new Equipe(this, nom);
		jdbc.BaseEquipe.addEquipe(equipe, inscriptions);
		candidats.add(equipe);
		return equipe;
	}
	
	void remove(Competition competition)
	{
		if (db)
			jdbc.BaseCompetition.suppComp(competition);
		competitions.remove(competition);
	}
	
	void remove(Candidat candidat)
	{
		candidats.remove(candidat);
	}
	
	/**
	 * Retourne l'unique instance de cette classe.
	 * Crée cet objet s'il n'existe déjà.
	 * @return l'unique objet de type {@link Inscriptions}.
	 */
	
	public static Inscriptions getInscriptions()
	{
		
		if (inscriptions == null)
		{
			inscriptions = readObject();
			if (inscriptions == null)
				inscriptions = new Inscriptions();
		}
		return inscriptions;
	}

	/**
	 * Retourne un object inscriptions vide. Ne modifie pas les compétitions
	 * et candidats déjà existants.
	 */
	
	public Inscriptions reinitialiser()
	{
		inscriptions = new Inscriptions();
		return getInscriptions();
	}

	/**
	 * Efface toutes les modifications sur Inscriptions depuis la dernière sauvegarde.
	 * Ne modifie pas les compétitions et candidats déjà existants.
	 */
	
	public Inscriptions recharger()
	{
		inscriptions = null;
		return getInscriptions();
	}
	
	private static Inscriptions readObject()
	{
		ObjectInputStream ois = null;
		try
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions)(ois.readObject());
		}
		catch (IOException | ClassNotFoundException e)
		{
			return null;
		}
		finally
		{
				try
				{
					if (ois != null)
						ois.close();
				} 
				catch (IOException e){}
		}	
	}
	
	/**
	 * Sauvegarde le gestionnaire pour qu'il soit ouvert automatiquement 
	 * lors d'une exécution ultérieure du programme.
	 * @throws IOException 
	 */
	
	public void sauvegarder() throws IOException
	{
		ObjectOutputStream oos = null;
		try
		{
			FileOutputStream fis = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(this);
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (oos != null)
					oos.close();
			} 
			catch (IOException e){}
		}
	}
	
	@Override
	public String toString()
	{
		return "Candidats : " + getCandidats().toString()
			+ "\nCompetitions  " + getCompetitions().toString();
	}
	//NE PAS TOUCHER 
	public static void main(String[] args)
	{
		MainMenu menu = new MainMenu();
		menu.start();
	}
	

	//UPDATE COMPETITION
	public Competition modifCompetition(Competition competition, String nom)
	{
		competition.setNom(nom);
		return competition;
}
	//UPDATE SUR LA DATE COMPETITION
	public Competition modifDateCompetition(Competition competition,LocalDate dateCloture)
	{
				
		competition.setDateCloture(dateCloture);
		return competition;
}
}
