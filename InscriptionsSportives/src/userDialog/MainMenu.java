package userDialog;

import java.io.IOException;
import inscriptions.Inscriptions;
import utilitaires.ligneDeCommande.*;

public class MainMenu 
{
	private static Menu mainMenu;
	private static PersMenu persMenu;
	private static TeamMenu teamMenu;
	private static CompMenu compMenu;
	private static Inscriptions inscriptions;
	public MainMenu()
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
	        Menu menuPrincipal = new Menu("Menu Principal");
	        menuPrincipal.ajoute(persMenu.getMenuPersonne());
	        menuPrincipal.ajoute(teamMenu.getMenuEquipe());
	        menuPrincipal.ajoute(compMenu.getMenuCompetition());
	        menuPrincipal.ajoute(getOptionSauvegarde());
	        menuPrincipal.ajouteQuitter("q");
	        return menuPrincipal;
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
							System.out.println("Sauvegarde réussi !");
							
						} 
						catch (IOException e) 
						{
							System.out.println("Sauvegarde impossible. " + e);
						}
					}
				};
	}
	

}