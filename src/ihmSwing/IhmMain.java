package ihmSwing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;

public class IhmMain extends JFrame 
	{
		private IhmPersonne ongletPersonne;	
		private IhmEquipe ongletEquipe;	
		private IhmCompetition ongletCompetition;	
		private IhmInscription ongletInscription;	

		
		
	public static void main(String[] args) {
		
		JFrame f = new JFrame("Gestion des Inscriptions");
		f.setSize(900, 700);
				
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		
		
		IhmPersonne Pers = new IhmPersonne(new JLabel ("Personne"));	
		JPanel ongletPers = Pers.getOnglet();
		
		
		IhmEquipe Equi = new IhmEquipe(new JLabel ("Equipe"));	
		JPanel ongletEqui = Equi.getOnglet();
		
		
		IhmCompetition Comp = new IhmCompetition(new JLabel ("Competition"));	
		JPanel ongletComp = Comp.getOnglet();
		
		
		IhmInscription Insc = new IhmInscription(new JLabel ("Inscription"));	
		JPanel ongletInsc = Insc.getOnglet();
		
		
		onglets.addTab("Competition", ongletComp);
		onglets.addTab("Personne", ongletPers);
		onglets.addTab("Equipe", ongletEqui);
		onglets.addTab("Inscription", ongletInsc);
	
		
		onglets.setOpaque(true);		
		f.add(onglets);		
		f.getContentPane().add(onglets);		
		f.setVisible(true);
		f.setResizable(false);
		}
		
		
        
	}
