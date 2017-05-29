package inscriptions;

import java.util.Collections;
import java.util.*;

/**
 * Repr�sente une personne physique pouvant s'inscrire � une comp�tition.
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
	 * Retourne le pr�nom de la personne.
	 * @return
	 */
	
	public String getPrenom()
	{
		return prenom;
	}

	/**
	 * Modifie le pr�nom de la personne.
	 * @param prenom
	 */
	
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
		jdbc.BasePersonne.updatePers(this);
	}

	/**
	 * Retourne l'adresse �lectronique de la personne.
	 * @return
	 */
	
	public String getMail()
	{
		return mail;

	}

	/**
	 * Modifie l'adresse �lectronique de la personne.
	 * @param mail
	 */
	
	public void setMail(String mail)
	{
		this.mail = mail;
		jdbc.BasePersonne.updatePers(this);

	}

	/**
	 * Retoure les �quipes dont cette personne fait partie.
	 * @return
	 */
	
	public Set<Equipe> getEquipes()
	{
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
		jdbc.BasePersonne.suppPersonne(this);
	}
	
	@Override
	public String toString()
	{
		if (equipes.size() == 1){
			return super.toString() + " est un membre de l'�quipe :  " + equipes.toString();
		}
		else if(equipes.size() > 1){
			return super.toString() + ""+ getPrenom()+ " est un membre des �quipes :  " + equipes.toString();
		}		
		else{
			return super.toString() + ""+ getPrenom()+ "et ne fait partie d'aucune equipe";
		}
	}

	
}