package userDialog;

import java.io.IOException;

import inscriptions.Competition.InscriptionEnRetardException;
import inscriptions.Inscriptions;
import commandLine.*;
import commandLine.util.InOut;;

public class MainMenu 
{
	private static Menu mainMenu;
	private static PersMenu persMenu;
	private static TeamMenu teamMenu;
	private static CompMenu compMenu;
	private static Inscriptions inscriptions;
	
	public MainMenu() throws InscriptionEnRetardException, RuntimeException, IOException
	{
		inscriptions = Inscriptions.getInscriptions();
		mainMenu=getMainMenu();
		persMenu = new PersMenu();
		teamMenu = new TeamMenu();
		compMenu = new CompMenu();
	}
	
	public void start()
	{
		mainMenu.start();
	}
	
	public Inscriptions getInscriptions()
	{
		return inscriptions;
	}
	
	//Menu principal
	static Menu getMainMenu()
	{
	        Menu mainMenu = new Menu("Menu Principal");
	        mainMenu.ajoute(compMenu.getMenuCompetition());
	        mainMenu.ajoute(teamMenu.getMenuEquipe());
	        mainMenu.ajoute(persMenu.getMenuPersonne());
	        mainMenu.ajoute(getOptionSauvegarde());
	        mainMenu.ajouteQuitter("q");
	        return mainMenu;
	}
	
	//Sauvergarder
	
	static Option getOptionSauvegarde()
	{
		return new Option("Sauvegarder","s",getActionSauvegarde());
	}
	
	static Action getActionSauvegarde()
	{
		return new Action()
				{
					@Override
					public void optionSelectionnee() 
					{
						try 
						{
							inscriptions.sauvegarder();
							System.out.println("Sauvegardé !");							
						} 
						catch (IOException e) 
						{
							System.out.println("Echec de la sauvegarde. " + e);
						}
					}
				};
	}
	

}