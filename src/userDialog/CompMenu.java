package userDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Competition.InscriptionEnRetardException;
import inscriptions.Inscriptions;


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
				menuCompetition.ajoute(getOptionModifierDateCompetition(element));
				menuCompetition.ajoute(getListeSupprimerUneCandidatCompetition(element));
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
	
	static Liste<Candidat> getListeSupprimerUneCandidatCompetition(Competition competition)
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
		Option option = new Option("Editer nom de la competition : "+competition.getNom(),"3",getActionEditerUneCompetition(competition));
		
		return option;
	}
	
	private static Action getActionEditerUneCompetition(Competition competition)
	{
		return new Action()
				{

					@Override
					public void optionSelectionnee() 
					{
						String nom = commandLine.util.InOut.getString("Nom: ");
						int jour = commandLine.util.InOut.getInt("Jour :"),
								mois = commandLine.util.InOut.getInt("Mois :"),
								annee = commandLine.util.InOut.getInt(" Année:");
						LocalDate newDate = LocalDate.of (annee,mois,jour);
						
						competition.setNom(nom);
						try
						{
							competition.setDateCloture(newDate);
						} 
						catch (InscriptionEnRetardException e)
						{
							System.out.println(e);
						}
					}
			
				};
	}
	
	//Modifier date de competition
	
	private static Option getOptionModifierDateCompetition(Competition competition)
	{
		Option option = new Option("Modifier la Date de la competition : "+competition.getNom(),"4",getActionModifierDateCompetition(competition));
		
		return option;
	}
	
	private static Action getActionModifierDateCompetition(Competition competition)
	{
		return new Action()
				{
					@Override
					public void optionSelectionnee() 
					{
						LocalDate today = LocalDate.now();						
						boolean changeDate = false;
						
						
						while (!changeDate){							
							String dateEntree = InOut.getString("entrez la nouvelle date au format aaaa/mm/jj : ");
							
							if (dateEntree.matches("((19|20)\\d{2})-([1-9]|0[1-9]|1[0-2])-(0[1-9]|[1-9]|[12][0-9]|3[01])")) {
								System.out.println(today);
								LocalDate date = LocalDate.parse(dateEntree);
						    
								if (date.isAfter(today)){
									competition.setDateCloture(date);
									changeDate = true;
								} else System.out.println("erreur positionnement");
							}else System.out.println("erreur regex");
						
						}
						
						System.out.println("Date de cloture modifiée pour la competition : "+competition.getNom());
					}
			
				};
	}
	//Supprimer une compétition
	private static Option getOptionSupprimerUneCompetition(Competition competition)
	{
		Option option = new Option("Supprimer "+competition.getNom(),"2",getActionSupprimerUneCompetition(competition));
		return option;
	}
	private static Action getActionSupprimerUneCompetition(Competition element)
	{
		return new Action()
				{

					@Override
					public void optionSelectionnee() 
					{
						element.delete();
						System.out.println(element.getNom()+" à été supprimée !");
					}
			
				};
	}
}