package inscriptions;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Repr�sente une Equipe. C'est-�-dire un ensemble de personnes pouvant 
 * s'inscrire � une comp�tition.
 * 
 */

public class Equipe extends Candidat 
{
	private static final long serialVersionUID = 4147819927233466035L;
	private SortedSet<Personne> membres = new TreeSet<>();
	private int id;
	
	Equipe(Inscriptions inscriptions, String nom)
	{
		super(inscriptions, nom);
	}

	/**
	 * Retourne l'id de la personne.
	 * @return
	 */	
	public int getId()
	{
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
	 * Retourne l'ensemble des personnes formant l'�quipe.
	 */	
	public SortedSet<Personne> getMembres()
	{
		jdbc.BaseEquipe.affichEquipe();
		return Collections.unmodifiableSortedSet(membres);
	}
	
	/**
	 * Ajoute une personne dans l'�quipe.
	 * @param membre
	 * @return
	 */

	public boolean add(Personne membre)
	{
		membre.add(this);
		return membres.add(membre);
	}

	/**
	 * Supprime une personne de l'�quipe. 
	 * @param membre
	 * @return
	 */
	
	public boolean remove(Personne membre)
	{
		membre.remove(this);
		return membres.remove(membre);
	}

	@Override
	public void delete()
	{
		super.delete();
	}
	
	@Override
	public String toString()
	{
		return "Equipe " + super.toString();
	}
}