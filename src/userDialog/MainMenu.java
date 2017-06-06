package userDialog;

import java.io.IOException;
import inscriptions.Inscriptions;
import inscriptions.Competition.InscriptionEnRetardException;
import commandLine.*;
import commandLine.util.InOut;;

public class MainMenu 
{
	private static Menu mainMenu;
	private static PersMenu persMenu;
	private static TeamMenu teamMenu;
	private static CompMenu compMenu;
	private static Inscriptions inscriptions;
	
	public MainMenu()
	{

		mainMenu=getMainMenu();
		persMenu = new PersMenu();
		teamMenu = new TeamMenu();
		compMenu = new CompMenu();
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) 
	{
		MainMenu menu = new MainMenu();
		menu.start();
		// RENVOIE UNE COLLECTION DE CANDIDAT
		// Inscriptions inscriptions = Inscriptions.getInscriptions();
		// for (Candidat c : inscription.getCandidats()) {
		// System.out.println(c);
		// }
		// RENVOIE UNE COLLECTION DE COMPETITION & Equipe
		// Inscriptions inscriptions = Inscriptions.getInscriptions();
		// for (Competition c : inscription.getCompetitions()) {
		// System.out.println(c);
		// }
		// for(Equipe e: inscription.getEquipes()){

		// System.out.println(e.getMembres());

		// }

		// for (Competition c : inscriptions.getCompetitions())
		// {
		// System.out.println(c);
		// }
	}
	public static void start()
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
							e.printStackTrace();
						}
					}
				};
	}
	

}