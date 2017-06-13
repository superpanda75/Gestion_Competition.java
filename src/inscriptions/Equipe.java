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
	private Inscriptions inscriptions;

	Equipe(Inscriptions inscriptions, String nom)
	{
		super(inscriptions, nom);
		this.inscriptions = inscriptions;

	}

	/**
	 * Retourne l'id de la personne.
	 * @return
	 */	
	public int getId()
	{
		// TODO faire pareil dans les autres classes. => fait !
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
		// TODO if bd
		membre.add(this);
		if (Inscriptions.bd && !inscriptions.estEnChargement())
			throw new RuntimeException();
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
		if (Inscriptions.bd)
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