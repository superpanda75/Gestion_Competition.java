package userDialog;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;
import commandLine.*;
import commandLine.util.InOut;;

public class PersMenu
{
	private static Inscriptions inscriptions;
	public PersMenu()
	{
		inscriptions = Inscriptions.getInscriptions();
	}
	
	public Inscriptions getInscriptions()
	{
		return inscriptions;
	}
	
	//Menu personne
		static Menu getMenuPersonne()
		{
			Menu persMenu = new Menu("Gestion de personne","3");
			persMenu.ajoute(getListeVoirUnePersonne());
			persMenu.ajoute(getOptionAjouterPersonne());
			persMenu.ajouteRevenir("r");
			return persMenu;
		}
		
		//G�rer une personne ou �quipe
		
		
		static Liste<Personne> getListeVoirUnePersonne()
		{
			Liste<Personne> liste = new Liste<>("Liste de personne","1",ListePersonne());
			liste.ajouteRevenir("r");
			return liste;

		}
		
		//Liste voir personne
		static ActionListe<Personne> ListePersonne() 
		{
			return new ActionListe<Personne>() {
				
				@Override
				public List<Personne> getListe()
				{
					return new ArrayList<>(inscriptions.getPersonnes());
				}
				
				@Override
				public void elementSelectionne(int indice, Personne element) 
				{
					
				}

				@Override
				public Menu getOption(Personne element) 
				{
					Menu persMenu = new Menu("Option pour  "+element.getPrenom()+" "+element.getNom(),null);
					persMenu.ajoute(getOptionVoirUnePersonne(element));
					persMenu.ajoute(getOptionSupprimerPersonne(element));
					persMenu.ajoute(getListeAjouterUnePersonneCompetition(element));
					persMenu.ajoute(getListeAjouterUnePersonneEquipe(element));
					persMenu.ajoute(getOptionEditerUnePersonne(element));
					//TODO:D'autres options pour l'utilisateur
					persMenu.ajouteRevenir("r");
					persMenu.setRetourAuto(true);
					return persMenu;
				}
			};
			
		}
		
		//D�tails personne
		private static Option getOptionVoirUnePersonne(Personne element)
		{
			return new Option("D�tails sur "+element.getPrenom(),"1",getActionVoirPersonne(element));
		}
		
		private static Action getActionVoirPersonne(Personne element) 
		{
			return new Action()
					{
						public void optionSelectionnee()
						{
							System.out.println("Pr�nom : "+element.getPrenom()+" | Nom : "+element.getNom()+" | Mail : "+element.getMail());
							if(!element.getEquipes().isEmpty())
							{
								System.out.println(element.getEquipes().toString());
							}
							else
							{
								System.out.println(element.getPrenom()+ " n'a pas d'�quipe");
							}
							
							if(!element.getCompetitions().isEmpty())
							{
								System.out.println("Participe � "+element.getCompetitions().toString());
							}
							else
							{
								System.out.println(element.getPrenom()+" ne participe � aucune comp�tition");
							}
						}
					};
		}
		
		//Supprimer une personne 
		
		
		static Option getOptionSupprimerPersonne(Personne personne)
		{
			return new Option("Supprimer "+personne.getPrenom(),"2",getActionSupprimerPersonne(personne));
		}
		
		private static Action getActionSupprimerPersonne(Personne personne)
		{
			return new Action()
					{
						public void optionSelectionnee()
						{
							personne.delete();
							System.out.println(personne.getPrenom()+" � �t� supprimer !");
						}
					};
		}
		
		static Liste<Competition> getListeAjouterUnePersonneCompetition(Personne personne)
		{
			Liste<Competition> liste = new Liste<>("Ajouter "+personne.getPrenom()+" � une comp�tition","3",getActionListeCompetitionAjoutPersonne(personne));
			liste.ajouteRevenir("r");
			return liste;
		}
		
		//Liste ajouter une personne dans une comp�tition
		
			private static ActionListe<Competition> getActionListeCompetitionAjoutPersonne(Personne personne)
			{
				return new ActionListe<Competition>()
				{

					@Override
					public List<Competition> getListe()
					{
						ArrayList<Competition> liste = new ArrayList<>();
						Set<Competition> getCompetition = inscriptions.getCompetitions();
						for(Competition competition : getCompetition)
						{
							if(!competition.getEnEquipe())
							{
								liste.add(competition);
							}
						}
						return liste;
					}

					@Override
					public void elementSelectionne(int indice, Competition element) 
					{
						
						System.out.println(personne.getPrenom()+" � bien �t� ajouter � "+element.getNom());
					}

					@Override
					public Option getOption(Competition element) 
					{
						//return new Option("Supprimer "+element.getNom(),null,getActionSupprimerUneCompetition(element));
//						Option option = new Option("Ajouter une personne � "+element.getNom(),null,getActionAjouterUnePersonneCompetition(personne,element));
//						return option;
						return null;
					}
					
				};
			}
			
			//Liste ajouter une personne dans une �quipe
			
			private static Liste<Equipe> getListeAjouterUnePersonneEquipe(Personne personne)
			{
				Liste<Equipe> liste = new Liste<>("Ajouter "+personne.getPrenom()+" dans une �quipe","4",getActionListeAjouterUnePersonneEquipe(personne));
				liste.ajouteRevenir("r");
				return liste;
			}
			
			private static ActionListe<Equipe> getActionListeAjouterUnePersonneEquipe(Personne personne)
			{
				return new ActionListe<Equipe>()
						{
							@Override
							public List<Equipe> getListe() {
								return new ArrayList<>(inscriptions.getEquipes());
							}

							@Override
							public void elementSelectionne(int indice, Equipe element) 
							{
								element.add(personne);
							}

							@Override
							public Option getOption(Equipe element) 
							{
								return null;
							}
						};
			}
			
			//Editer une personne 
			
			private static Option getOptionEditerUnePersonne(Personne personne)
			{
				return new Option("Editer "+personne.getPrenom(),"5",getActionEditerUnePersonne(personne));
			}
			
			private static Action getActionEditerUnePersonne(Personne personne)
			{
				return new Action()
						{
							@Override
							public void optionSelectionnee()
							{
								String nom= InOut.getString("Nom : "),
						                prenom = InOut.getString("Pr�nom : "),
						                mail = InOut.getString("Mail : ");
										personne.setNom(nom);
										personne.setPrenom(prenom);
										personne.setMail(mail);
							}
					
						};
			}
			//Ajouter personne
			
			static Option getOptionAjouterPersonne()
			{
				return new Option("Ajouter une personne","2",getActionAjouterPersonne());
			}

			private static Action getActionAjouterPersonne() 
			{
				return new Action ()
				{
					@Override
					public void optionSelectionnee()
					{
						String nom= InOut.getString("Nom : "),
		                prenom = InOut.getString("Pr�nom : "),
		                mail = InOut.getString("Mail : ");
						inscriptions.createPersonne(nom, prenom, mail);
					}
				};
			}
			
			
}