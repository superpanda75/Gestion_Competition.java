package inscriptions;

import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

/**
 * Représente une compétition, c'est-� -dire un ensemble de candidats 
 * inscrits �  un événement, les inscriptions sont closes �  la date dateCloture.
 *
 */

public class Competition implements Comparable<Competition>, Serializable
{
	private static final long serialVersionUID = -2882150118573759729L;
	private Inscriptions inscriptions;
	private String nom;
	private int id;
	private Set<Candidat> candidats;
	private LocalDate dateCloture;
	private boolean enEquipe = false;

	Competition(Inscriptions inscriptions, String nom, LocalDate dateCloture, boolean enEquipe)
	{
		this.enEquipe = enEquipe;
		this.inscriptions = inscriptions;
		this.nom = nom;
		this.dateCloture = dateCloture;
		candidats = new TreeSet<>();
	}
	
	/**
	 * Retourne l'id de la competition.
	 * @return
	 */	
	public int getId()
	{
		return id;
	}

	/**
	 * Modifie l'id de la competition.
	 * @param prenom
	 */	
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * Retourne le nom de la compétition.
	 * @return
	 */
	
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Modifie le nom de la compétition.
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom ;
		jdbc.BaseCompetition.update(this);
	}
	
	/**
	 * Retourne vrai si les inscriptions sont encore ouvertes, 
	 * faux si les inscriptions sont closes.
	 * @return
	 */
	
	public boolean inscriptionsOuvertes(LocalDate dateCloture) 
	{
		// TODO retourner vrai si et seulement si la date système est antérieure �  la date de clôture.
		return ( dateCloture.isBefore(LocalDate.now()));
	}
		
	
	
	/**
	 * Retourne la date de cloture des inscriptions.
	 * @return
	 */
	
	public LocalDate getDateCloture()
	{
		return dateCloture;
	}
	
	/**
	 * Est vrai si et seulement si les inscriptions sont réservées aux équipes.
	 * @return
	 */
	
	public boolean getEnEquipe() {
		return enEquipe;
	}
	
	public void setEnEquipe(boolean enEquipe) {
		this.enEquipe = enEquipe;
		jdbc.BaseCompetition.update(this);
	}
	
	/**
	 * Modifie la date de cloture des inscriptions. Il est possible de la reculer 
	 * mais pas de l'avancer.
	 * @param dateCloture
	 */
	
	public void setDateCloture(LocalDate dateCloture) //throws a completer
	{
		// TODO v�rifier que l'on avance pas la date.

		if (dateCloture.isAfter(this.dateCloture))
			this.dateCloture = dateCloture;		  	
		else
			throw new DateClotureException();

	}
	
	/**
	 * Retourne l'ensemble des candidats inscrits.
	 * @return
	 */
	
	public Set<Candidat> getCandidats()
	{
		return Collections.unmodifiableSet(candidats);
	}
	
	/**
	 * Inscrit un candidat de type Personne �  la compétition. Provoque une
	 * exception si la compétition est réservée aux équipes ou que les 
	 * inscriptions sont closes.
	 * @param personne
	 * @return
	 */
	
	public boolean add(Personne personne)
	{
		// TODO vérifier que la date de clôture n'est pas passée
		if( dateCloture.isAfter(LocalDate.now()))
			throw new InscriptionEnRetardException(personne);
		if (enEquipe)
			throw new RuntimeException();
		personne.add(this);
		return candidats.add(personne);
	}

	/**
	 * Inscrit un candidat de type Equipe �  la compétition. Provoque une
	 * exception si la compétition est réservée aux personnes ou que 
	 * les inscriptions sont closes.
	 * @param personne
	 * @return
	 */

	public boolean add(Equipe equipe)
	{
		// TODO vérifier que la date de clôture n'est pas passée
		if ( dateCloture.isAfter(LocalDate.now())) 
			throw new InscriptionEnRetardException(equipe);
		if (enEquipe)
			throw new RuntimeException();
		equipe.add(this);
		return candidats.add(equipe);
	}
	
	/**
	 * Désinscrit un candidat.
	 * @param candidat
	 * @return
	 */
	
	public boolean remove(Candidat candidat)
	{
		candidat.remove(this);
		return candidats.remove(candidat);
	}
	
	/**
	 * Supprime la compétition de l'application.
	 */
	
	public void delete()
	{
		for (Candidat candidat : candidats)
			remove(candidat);
		inscriptions.remove(this);
		jdbc.BaseCompetition.deleteComp(this);
	}
	
	@Override
	public int compareTo(Competition o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return getNom();
	}
	
	public class DateClotureException extends RuntimeException 
	{
		private LocalDate mauvaiseDate;
		
		 @Override
		 public String toString()
		 {
		  return "Impossible de remplacer la date de cl�ture (" + getDateCloture() + ") de la competition "
				  + getNom() + " par " + mauvaiseDate + ".";
		 }

		 public void DateClotureException(LocalDate mauvaiseDate) 
		{
			this.mauvaiseDate = mauvaiseDate ;	
		}
	}

	public class InscriptionEnRetardException extends RuntimeException
	{
		private Candidat candidat;
		
		public InscriptionEnRetardException(Candidat candidat) 
		{
			this.candidat = candidat;
		}
		
		public String toString()
		 {
		  return "Impossible de d'ajouter le candidat (" + candidat + ") � la competition "
				  + getNom() + " par " + getDateCloture() + ".";
		 }
	}


}