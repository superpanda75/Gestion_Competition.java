package inscriptions;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant 
 * s'inscrire à une compétition.
 * 
 */

public class Equipe extends Candidat 
{
	private static final long serialVersionUID = 4147819927233466035L;
	private SortedSet<Personne> membres = new TreeSet<>();
	private int id = -1;
	
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
		// TODO faire pareil dans les autres classes.
		if (!Inscriptions.bd)
			throw new RuntimeException();
		return id;
	}

	/**
	 * Modifie l'id de la personne.
	 * @param prenom
	 */	
	public void setId(int id)
	{
		if (!Inscriptions.bd)
			throw new RuntimeException();
		if (this.id == -1)
			this.id = id;
		throw new RuntimeException();
	}
	
	
	/**
	 * Retourne l'ensemble des personnes formant l'équipe.
	 */	
	public SortedSet<Personne> getMembres()
	{
		return Collections.unmodifiableSortedSet(membres);
	}
	
	/**
	 * Ajoute une personne dans l'équipe.
	 * @param membre
	 * @return
	 */

	public boolean add(Personne membre)
	{
		
		membre.add(this);
		// TODO if bd
		jdbc.BaseEquipe.addMembreEquipe(this,membre);
		return membres.add(membre);
	}

	/**
	 * Supprime une personne de l'équipe. 
	 * @param membre
	 * @return
	 */
	
	public boolean remove(Personne membre)
	{
		membre.remove(this);
		jdbc.BaseEquipe.suppMembreEquipe(this, membre);
		return membres.remove(membre);
	}

	@Override
	public void delete()
	{
		super.delete();
		for (Personne p : membres)
			this.remove(p);
		jdbc.BaseEquipe.suppEquipe(this);

	}
	
	@Override
	public String toString()
	{
		return  getNom();
	}
}