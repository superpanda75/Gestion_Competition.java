package inscriptions;

import java.io.*;
import java.util.*;
import java.time.*;

/**
 * Point d'entr�e dans l'application, un seul objet de type Inscription permet
 * de g�rer les comp�titions, candidats (de type equipe ou personne) ainsi que
 * d'inscrire des candidats � des comp�tition.
 */

public class Inscriptions implements Serializable
{
	private static final long		serialVersionUID	= -3095339436048473524L;
	private static final String		FILE_NAME			= "Inscriptions.srz";
	private static Inscriptions		inscriptions;
	private SortedSet<Competition>	competitions		= new TreeSet<>();
	private SortedSet<Candidat>		candidats			= new TreeSet<>();
	transient final static boolean bd = true; 
	private transient static boolean enChargement = false;

	private Inscriptions()
	{
		if (bd){
			enChargement = true;
			jdbc.BaseCompetition.SelectComp(this);
			jdbc.BaseEquipe.SelectEquipe(this);
			jdbc.BasePersonne.SelectPers(this);
			jdbc.BaseEquipe.selectMembreEquipe(this);
			jdbc.BaseCandidat.inscritCandidats(this);
			enChargement = false;
		}

	}

	public boolean estEnChargement()
	{
		return enChargement;
	}

	/**
	 * Retourne les comp�titions.
	 * 
	 * @return
	 * 
	 */

	public SortedSet<Competition> getCompetitions()
	{
		return Collections.unmodifiableSortedSet(competitions);
	}

	/**
	 * Retourne tous les candidats (personnes et �quipes confondues).
	 * 
	 * @return
	 */

	public SortedSet<Candidat> getCandidats()
	{
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Retourne toutes les personnes.
	 * 
	 * @return
	 */

	public SortedSet<Personne> getPersonnes()
	{
		SortedSet<Personne> personnes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Personne)
				personnes.add((Personne) c);
		return Collections.unmodifiableSortedSet(personnes);
	}

	/**
	 * Retourne toutes les �quipes.
	 * 
	 * @return
	 */

	public SortedSet<Equipe> getEquipes()
	{
		SortedSet<Equipe> equipes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Equipe)
				equipes.add((Equipe) c);
		return Collections.unmodifiableSortedSet(equipes);
	}

	/**
	 * Cr��e une comp�tition. Ceci est le seul moyen, il n'y a pas de
	 * constructeur public dans {@link Competition}.
	 * 
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */

	public Competition createCompetition(String nom, LocalDate dateCloture, boolean enEquipe)
	{

		Competition competition = new Competition(this, nom, dateCloture, enEquipe);
		competitions.add(competition);
		if (bd && !enChargement)
			jdbc.BaseCompetition.Sauvegarder(competition);
		// TODO faire pareil pour equipeet personne
		return competition;

	}

	/**
	 * Cr��e une Candidat de type Personne. Ceci est le seul moyen, il n'y a
	 * pas de constructeur public dans {@link Personne}.
	 * 
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	// AJOUTER PERSONNE ET CANDIDAT
	public Personne createPersonne(String nom, String prenom, String mail)
	{
		Personne personne = new Personne(this, nom, prenom, mail);
		if (bd && !enChargement) // TODO v�rifier cgargement => fait
			jdbc.BasePersonne.sauvegarder(personne);
		candidats.add(personne);
		return personne;
	}

	/**
	 * Cr��e un Candidat de type �quipe. Ceci est le seul moyen, il n'y a
	 * pas de constructeur public dans {@link Equipe}.
	 * 
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */

	public Equipe createEquipe(String nom)
	{
		Equipe equipe = new Equipe(this, nom);
		candidats.add(equipe);
		if (bd && !enChargement)
			jdbc.BaseEquipe.sauvegarder(equipe);
		return equipe;
	}

	void remove(Competition competition)
	{
		competitions.remove(competition);
	}

	void remove(Candidat candidat)
	{
		candidats.remove(candidat);
	}

	/**
	 * Retourne l'unique instance de cette classe. Cr�e cet objet s'il
	 * n'existe d�j�.
	 * 
	 * @return l'unique objet de type {@link Inscriptions}.
	 */

	@SuppressWarnings("unused")
	public static Inscriptions getInscriptions()
	{
		//SERIALISER
		if (!bd && inscriptions == null)
		{
			inscriptions = readObject();
			if (inscriptions == null)
				inscriptions = new Inscriptions();
		}
		//BASE
		if(bd && inscriptions == null){
			inscriptions = new Inscriptions();
		}
		return inscriptions;
	}

	/**
	 * Retourne un object inscriptions vide. Ne modifie pas les comp�titions
	 * et candidats d�j� existants.
	 */

	public Inscriptions reinitialiser()
	{
		inscriptions = new Inscriptions();
		return getInscriptions();
	}

	/**
	 * Efface toutes les modifications sur Inscriptions depuis la derni�re
	 * sauvegarde. Ne modifie pas les comp�titions et candidats d�j�
	 * existants.
	 */

	public Inscriptions recharger()
	{
		inscriptions = null;
		return getInscriptions();
	}

	private static Inscriptions readObject()
	{
		if (bd) throw new RuntimeException();
		ObjectInputStream ois = null;
		try
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions) (ois.readObject());
		} catch (IOException | ClassNotFoundException e)
		{
			return null;
		} finally
		{
			try
			{
				if (ois != null)
					ois.close();
			} catch (IOException e)
			{
			}
		}
	}

	/**
	 * Sauvegarde le gestionnaire pour qu'il soit ouvert automatiquement
	 * lors d'une ex�cution ult�rieure du programme.
	 * 
	 * @throws IOException
	 */

	public void sauvegarder() throws IOException
	{
		if(!bd){
			ObjectOutputStream oos = null;
			try
			{
				FileOutputStream fis = new FileOutputStream(FILE_NAME);
				oos = new ObjectOutputStream(fis);
				oos.writeObject(this);
			} catch (IOException e)
			{
				throw e;
			} finally
			{
				try
				{
					if (oos != null)
						oos.close();
				} catch (IOException e)
				{
				}
			}
		}
	}

	@Override
	public String toString()
	{
		return "Candidats : " + getCandidats().toString() + "\nCompetitions  " + getCompetitions().toString();
	}

}