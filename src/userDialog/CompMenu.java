package userDialog;


import java.time.LocalDate;
import java.util.*;
import inscriptions.*;
import inscriptions.Competition.*;
import commandLine.*;
import commandLine.util.InOut;;

public class CompMenu
{
	private static Inscriptions inscriptions;
	public CompMenu()
	{
		inscriptions = Inscriptions.getInscriptions();
	}

	static Menu getMenuCompetition()
	{
		Menu menuCompetition = new Menu("Gestion de compétition","1");
		menuCompetition.ajoute(getListeCompetition());
		menuCompetition.ajoute(getOptionAjouterCompetition());
		menuCompetition.ajouteRevenir("r");
		return menuCompetition;
	}

	//Gestion compétition

	static Liste<Competition> getListeCompetition()
	{
		Liste<Competition> liste = new Liste<>("Gestion de compétition","1",getActionListeGestionCompetition());
		liste.ajouteRevenir("r");
		return liste;
	}

	static ActionListe<Competition> getActionListeGestionCompetition()
	{
		return new ActionListe<Competition>()
		{

			@Override
			public List<Competition> getListe()
			{
				return new ArrayList<>(inscriptions.getCompetitions());
			}

			@Override
			public void elementSelectionne(int indice, Competition element) 
			{

			}

			@Override
			public Menu getOption(Competition element) 
			{
				//TODO: Menu competition
				Menu menuCompetition = new Menu("Options pour "+element.getNom(),null);
				menuCompetition.ajoute(getOptionVoirUneCompetition(element));
				menuCompetition.ajoute(getOptionSupprimerUneCompetition(element));
				menuCompetition.ajoute(getOptionEditerUneCompetition(element));
				menuCompetition.ajoute(getListeSupprimerUnCandidatCompetition(element));
				menuCompetition.ajouteRevenir("r");
				menuCompetition.setRetourAuto(true);
				return menuCompetition;
			}

		};
	}

	static Option getOptionVoirUneCompetition(Competition competition)
	{
		Option option = new Option("Détails de "+competition.getNom(),"1",getActionAfficherUneCompetition(competition));
		return option;
	}

	//Supprimer une personne d'une competition

	static Liste<Candidat> getListeSupprimerUnCandidatCompetition(Competition competition)
	{
		Liste<Candidat> liste = new Liste<>("Supprimer un candidat de "+competition.getNom(),"5",getListeActionSupprimerUnCandidatCompetition(competition));
		liste.ajouteRevenir("r");
		return liste;
	}

	//Liste supprimer un candidat d'une competition
	static ActionListe<Candidat>getListeActionSupprimerUnCandidatCompetition(Competition competition)
	{
		return new ActionListe<Candidat>()
		{

			@Override
			public List<Candidat> getListe() 
			{
				return new ArrayList<>(competition.getCandidats());
			}

			@Override
			public void elementSelectionne(int indice, Candidat element) 
			{
				competition.remove(element);
				System.out.println(element.getNom()+" à bien été supprimé de "+competition.getNom());
			}

			@Override
			public Option getOption(Candidat element) 
			{
				return null;
			}

		};
	}

	//Liste voir une compétition

	private static Action getActionAfficherUneCompetition(Competition element) 
	{
		return new Action()
		{
			@Override
			public void optionSelectionnee() 
			{
				if(!element.getCandidats().isEmpty())
				{
					System.out.println(element.getCandidats().toString());
				}
				else
				{
					System.out.println("Cette compétition n'as pas encore de participants");
				}
				System.out.println("Date de cloture : "+element.getDateCloture());
			}
		};
	}



	//Ajouter une compétition

	static Option getOptionAjouterCompetition()
	{
		return new Option("Ajouter une competition","2",getActionAjouterCompetition());
	}

	private static Action getActionAjouterCompetition()
	{
		return new Action()
		{
			@Override
			public void optionSelectionnee() 
			{
				String nom= InOut.getString("Nom : ");
				System.out.println("Date de cloture : ");
				String jour=InOut.getString("Jour : "),
						mois=InOut.getString("Mois : "),
						annee=InOut.getString("Annee : ");
				String dateClotureString = annee+"-"+mois+"-"+jour;
				LocalDate dateCloture = LocalDate.parse(dateClotureString);
				boolean enEquipe = false;
				String reponse="";
				while(!reponse.equals("o")&&!reponse.equals("n"))
				{
					reponse=InOut.getString("En équipe ? o : Oui n : Non : ");
				}
				enEquipe=reponse.compareTo("o")==0;
				inscriptions.createCompetition(nom, dateCloture, enEquipe);
			}
		};
	}

	//Editer une competition

	private static Option getOptionEditerUneCompetition(Competition competition)
	{
		Option option = new Option("Editer nom de la competition : "+competition.getNom(),"3",getActionModifCompetition(competition));

		return option;
	}


	//Modifier la competition

	static Action getActionModifCompetition (final Competition competition)
	{
		return new Action ()
		{
			@Override
			public void optionSelectionnee()
			{
				String nouveauNom = commandLine.util.InOut.getString(" nom: ");
				int jour = commandLine.util.InOut.getInt("jour :"),
						mois = commandLine.util.InOut.getInt("mois :"),
						annee = commandLine.util.InOut.getInt("Année:");
				LocalDate nouvelledateCloture = LocalDate.of (annee,mois,jour);

				competition.setNom(nouveauNom);
				try
				{
					competition.setDateCloture(nouvelledateCloture);
				} 
				catch (InscriptionEnRetardException e)
				{
					System.out.println(e);
				}
			}
		};
	}

	//Supprimer une compétition
	private static Option getOptionSupprimerUneCompetition(Competition competition)
	{
		Option option = new Option("Supprimer "+competition.getNom(),"4",getActionSupprCompetition(competition));
		return option;
	}
	static Action getActionSupprCompetition (final Competition competition)
	{
		return new Action ()
		{
			@Override
			public void optionSelectionnee()
			{
				competition.delete();
				System.out.println(competition.getNom()+" à été supprimé !");

			}
		};
	}
}