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
					persMenu.ajoute(getOptionSupprPersonneEquipe(element, inscriptions));
					persMenu.ajoute(getOptionEditerUnePersonne(element));
					//TODO:D'autres options pour l'utilisateur
					persMenu.ajouteRevenir("r");
					persMenu.setRetourAuto(true);
					return persMenu;
				}
			};
			
		}
		
		//D�tails personne FAIS
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
		
		//Supprimer une personne  FAIS
		static Option getOptionSupprimerPersonne(Personne personne)
		{
			return new Option("Supprimer "+personne.getPrenom(),"2",getActionSupprimerPersonne(personne));
		}
		
		static Action getActionSupprimerPersonne(final Personne personne)
		{
			return new Action ()
					{
						@Override
						public void optionSelectionnee()
						{
							personne.delete();
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
				Liste<Equipe> liste = new Liste<>("Ajouter "+personne.getPrenom()+" dans une �quipe","4",getActionListeAjouterUnePersonneEquipe(personne, inscriptions));
				liste.ajouteRevenir("r");
				return liste;
			}
			
			
			static ActionListe<Equipe> getActionListeAjouterUnePersonneEquipe (Personne personne, Inscriptions inscriptions)
			{
				return new ActionListe<Equipe>()
						{
									public List<Equipe> getListe()
					                {
					                        return new ArrayList<>(inscriptions.getEquipes());
					                }
									
									public void elementSelectionne(int indice, Equipe element)
									{
										element.add(personne);
									}

									public Option getOption(Equipe element)
									{
										return null;
									}
						};
			}
			// FAIS SUPPRIMER UN MEMBRE D'UNE EQUIPE
			private static Option getOptionSupprPersonneEquipe(Personne personne, Inscriptions inscriptions)

			{
				Liste<Equipe> supprEquipe = new Liste<> ("Supprimer une Personne d'une Equipe","5",getActionListeSupprPersonneEquipe(personne, inscriptions));
				return supprEquipe;
			}
			private static ActionListe<Equipe> getActionListeSupprPersonneEquipe (Personne personne, Inscriptions inscriptions)

			{
				return new ActionListe<Equipe> ()

						{
									public List<Equipe> getListe()
									{
											return new ArrayList<>(inscriptions.getEquipes());
									}
									
									public void elementSelectionne(int indice, Equipe element)
									{
										element.remove(personne);
									}
									
									public Option getOption(Equipe element)
									{
										return null;
									}
						};
			}
			//Editer une personne  fais 
			
			private static Option getOptionEditerUnePersonne(Personne personne)
			{
				return new Option("Editer "+personne.getPrenom(),"6",getActionEditerUnePersonne(personne));
			}
			
			static Action getActionEditerUnePersonne (final Personne personne)

			{
				return new Action ()
						{
							@Override
							public void optionSelectionnee()
							{
								String Nom = commandLine.util.InOut.getString("Nom: "),
										Prenom = commandLine.util.InOut.getString("Pr�nom: "),
										Mail = commandLine.util.InOut.getString("Mail : ");
								personne.setNom(Nom);
								personne.setPrenom(Prenom);
								personne.setMail(Mail);
							}
						};
			}
			//Ajouter personne fais 
			
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