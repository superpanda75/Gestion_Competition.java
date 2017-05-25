package jdbc;

import java.sql.*;
import java.util.*;
import inscriptions.*;

public class BaseCandidat {
	public static Base bdd = new Base();
	
	//AFFICHER CANDIDAT
	public BaseCandidat(){
		
	}
	//1- AFFICHER CANDIDAT - Equipe --> fonctionne 
		 public static SortedSet<Candidat> SelectCand(Inscriptions inscription){
				SortedSet<Candidat> listeCand = new TreeSet();
				for (Candidat candidat : BasePersonne.SelectPers(inscription)) {
					listeCand.add(candidat);
					candidat.getId();
				}
				for (Candidat candidat : BaseEquipe.SelectEquipe(inscription)) {
					listeCand.add(candidat);
				}

			return listeCand;
		}

	
		 //2- modifie le candidat 
		 public void updateCand(){
			 try{
					Connection c = bdd.connexion();
					String req = "";
					

			 }catch(SQLException e){
				 System.out.println(e.getMessage());
			 }
		 }
		 //3- supprime le candidat
		 public void deleteCand(){
			 try{
				 Connection c = bdd.connexion();
					String req = "";
				 
			 }catch(SQLException e){
				 System.out.println(e.getMessage());
			 }
		 }
		 //4- Inscrire un candidat (java_inscription) 
	
}