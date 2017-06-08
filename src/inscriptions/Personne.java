package inscriptions;

import java.util.Collections;
import java.util.*;

/**
 * Représente une personne physique pouvant s'inscrire à une compétition.
 */

public class Personne extends Candidat
{
	private static final long serialVersionUID = 4434646724271327254L;
	private String prenom, mail;
	private int id;
	private Set<Equipe> equipes;
	
	Personne(Inscriptions inscriptions, String nom, String prenom, String mail)
	{
		super(inscriptions, nom);
		this.prenom = prenom;
		this.mail = mail;
		equipes = new TreeSet<>();
	}

	
	/**
	 * Retourne l'id de la personne.
	 * @return
	 */	
	public int getId()
	{
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
	 * Retourne le prénom de la personne.
	 * @return
	 */
	
	public String getPrenom()
	{
		if (!Inscriptions.bd)
			throw new RuntimeException();
		return prenom;
	}

	/**
	 * Modifie le prénom de la personne.
	 * @param prenom
	 */
	
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
		if (Inscriptions.bd)
			jdbc.BasePersonne.updatePers(this);
		// TODO faire pareil sur les autres setters
	}

	/**
	 * Retourne l'adresse électronique de la personne.
	 * @return
	 */
	
	public String getMail()
	{
		if (!Inscriptions.bd)
			throw new RuntimeException();
		return mail;

	}

	/**
	 * Modifie l'adresse électronique de la personne.
	 * @param mail
	 */
	
	public void setMail(String mail)
	{
		this.mail = mail;
		if (Inscriptions.bd)
		jdbc.BasePersonne.updatePers(this);

	}

	/**
	 * Retoure les équipes dont cette personne fait partie.
	 * @return
	 */
	
	public Set<Equipe> getEquipes()
	{
		if (!Inscriptions.bd)
			throw new RuntimeException();
		return Collections.unmodifiableSet(equipes);
	}
	
	boolean add(Equipe equipe)
	{
		return equipes.add(equipe);
	}

	boolean remove(Equipe equipe)
	{
		return equipes.remove(equipe);
	}
	
	@Override
	public void delete()
	{
		super.delete();
		for (Equipe e : equipes)
			e.remove(this);
		if (Inscriptions.bd)
			jdbc.BasePersonne.suppPersonne(this);
	}
	
	@Override
	public String toString()
	{
		if (equipes.size() == 1){
			return super.toString() + " est un membre de l'équipe :  " + equipes.toString();
		}
		else if(equipes.size() > 1){
			return super.toString() + ""+ getPrenom()+ " est un membre des équipes :  " + equipes.toString();
		}		
		else{
			return super.toString() + ""+ getPrenom()+ "et ne fait partie d'aucune equipe";
		}
	}

	
}