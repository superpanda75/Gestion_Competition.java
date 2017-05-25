package userDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;

import commandLine.*;
import commandLine.util.InOut;;

public class TeamMenu 
{
	private static Inscriptions inscriptions;
	public TeamMenu()
	{
		inscriptions = Inscriptions.getInscriptions();
	}
	
	static Menu getMenuEquipe()
	{
		Menu menuEquipe = new Menu("Gestion d'�quipe","2");
		menuEquipe.ajoute(getListeVoirUneEquipe());
		menuEquipe.ajoute(getOptionAjouterEquipe());
		menuEquipe.ajouteRevenir("r");
		return menuEquipe;
	}
	
	static Liste<Equipe> getListeVoirUneEquipe()
	{
		
		Liste<Equipe> liste= new Liste<>("Liste d'�quipe","1",getActionListeVoirUneEquipe());
		liste.ajouteRevenir("r");
		return liste;
	}
	
	//Ajouter equipe
	
		static Option getOptionAjouterEquipe()
		{
			return new Option("Ajouter une equipe","5",getActionAjouterEquipe());
		}
		
		private static Action getActionAjouterEquipe()
		{
			return new Action ()
					{
						@Override
						public void optionSelectionnee()
						{
							String nom= InOut.getString("Nom : ");
									inscriptions.createEquipe(nom);
						}
					};
		}
	
	//Liste voir equipe
		private static ActionListe<Equipe> getActionListeVoirUneEquipe() 
		{
			return new ActionListe<Equipe>() {
				
				@Override
				public List<Equipe> getListe()
				{
					return new ArrayList<>(inscriptions.getEquipes());
				}
				
				@Override
				public void elementSelectionne(int indice, Equipe element) 
				{
					
				}

				@Override
				public Menu getOption(Equipe element) 
				{
					//return new Option("Afficher "+element.getNom(),null, getActionAfficherEquipe(element));
					Menu menuEquipe = new Menu("Option pour "+element.getNom(),null);
					//TODO : Ajouter des options pour les �quipes
					menuEquipe.ajoute(getOptionVoirUneEquipe(element));
					menuEquipe.ajoute(getOptionSupprimerEquipe(element));
					menuEquipe.ajoute(getListeAjouterUneEquipeCompetition(element));
					menuEquipe.ajouteRevenir("r");
					menuEquipe.setRetourAuto(true);
					return menuEquipe;
				}
			};
			
		}
		
		private static Option getOptionVoirUneEquipe(Equipe equipe)
		{
			return new Option("D�tails de l'�quipe "+equipe.getNom(),"1",getActionAfficherEquipe(equipe));
		}
		
		private static Action getActionAfficherEquipe(Equipe element)
		{
			return new Action()
					{
						public void optionSelectionnee()
						{
							System.out.println("Nom : "+element.getNom());
							if(!element.getMembres().isEmpty())
							{
								System.out.println("Membre de l'�quipe: "+element.getMembres().toString());
							}
							else
							{
								System.out.println(element.getNom()+" n'as pas de membres");
							}
							if(!element.getCompetitions().isEmpty())
							{
								System.out.println("Competition : "+element.getCompetitions().toString());
							}
							else
							{
								System.out.println(element.getNom()+" ne participe � aucune comp�tition");
							}
						}
					};
		}
		
		//Supprimer une �quipe
		
		static Option getOptionSupprimerEquipe(Equipe equipe)
		{
			return new Option("Supprimer "+equipe.getNom(),"2",getActionSupprimerEquipe(equipe));
		}
		
		private static Action getActionSupprimerEquipe(Equipe equipe)
		{
			return new Action()
					{
						public void optionSelectionnee()
						{
							equipe.delete();
							System.out.println(equipe.getNom()+" � �t� supprimer !");
						}
					};
		}
		
		static Liste<Competition> getListeAjouterUneEquipeCompetition(Equipe equipe)
		{
			Liste<Competition> liste = new Liste<>("Ajouter "+equipe.getNom()+" � une comp�tition","3",getActionListeAjouterUneEquipeCompetition(equipe));
			liste.ajouteRevenir("r");
			return liste;
		}
		
		//Liste ajouter une equipe dans une competition
		
				private static ActionListe<Competition> getActionListeAjouterUneEquipeCompetition(Equipe equipe)
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
										if(competition.getEnEquipe())
										{
											liste.add(competition);
										}
									}
									return liste;
								}

								@Override
								public void elementSelectionne(int indice, Competition element) 
								{
									
									System.out.println(equipe.getNom()+" � bien �t� ajout�e � "+element.getNom());
								}

								@Override
								public Option getOption(Competition element) 
								{
									return null;
								}
						
							};
						
				}
	
	
}