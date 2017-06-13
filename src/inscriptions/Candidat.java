package inscriptions;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Candidat � un �v�nement sportif, soit une personne physique, soit une �quipe.
 *
 */

public abstract class Candidat implements Comparable<Candidat>, Serializable
{
	private static final long serialVersionUID = -6035399822298694746L;
	private Inscriptions inscriptions;
	private int id;
	private String nom;
	private Set<Competition> competitions;

	Candidat(Inscriptions inscriptions, String nom)
	{
		this.inscriptions = inscriptions;	
		this.nom = nom;
		competitions = new TreeSet<>();
	}

	/**
	 * Retourne l'id de la personne.
	 * @return
	 */	
	public int getId()
	{
		// TODO faire pareil dans les autres classes. CLASS Candidat => fait
		return id;
	}

	/**
	 * Modifie l'id de la personne.
	 * @param prenom
	 */	
	public void setId(int id)
	{
		this.id = id;
	}


	/**
	 * Retourne le nom du candidat.
	 * @return
	 */

	public String getNom()
	{
		return nom;
	}

	/**
	 * Modifie le nom du candidat.
	 * @param nom
	 */

	public void setNom(String nom)
	{
		this.nom = nom;
		jdbc.BaseEquipe.modifEquipe(this);
	}

	/**
	 * Retourne toutes les comp�titions auxquelles ce candidat est inscrit.s
	 * @return
	 */

	public Set<Competition> getCompetitions()
	{
		return Collections.unmodifiableSet(competitions);	
	}

	boolean add(Competition competition)
	{
		return competitions.add(competition);
	}

	boolean remove(Competition competition)
	{
		return competitions.remove(competition);
	}

	/**
	 * Supprime un candidat de l'application.
	 */

	public void delete()
	{
		for (Competition c : competitions)
			c.remove(this);
		inscriptions.remove(this);
	}

	@Override
	public int compareTo(Candidat o)
	{
		return getNom().compareTo(o.getNom());
	}

	@Override
	public String toString()
	{
		if (getCompetitions().size() != 0){
			return "\n" + getNom() + " -> inscrit � " + getCompetitions();
		}else{
			return  "\n" + getNom() + " n'est inscrit � aucune competition et ";
		}
	}
}