package ihmSwing;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;


public class IhmPersonne
	{	
		private JPanel ongletPers = new JPanel();
		private JTabbedPane menuPersonne = new JTabbedPane(SwingConstants.RIGHT);
		private JPanel panelDroite = new JPanel();
		
		public IhmPersonne(JLabel titreOnglet)
		{
			this.ongletPers.setLayout(new BorderLayout());
			this.panelDroite.add(makeSousMenuPersonne());
			this.ongletPers.add(titreOnglet,BorderLayout.WEST);
			this.ongletPers.add(panelDroite,BorderLayout.EAST);
			
		}
		
		//Menu de l'onglet Personne
		public JTabbedPane makeSousMenuPersonne(){
			JTabbedPane onglets = new JTabbedPane();
			
			JPanel ajouter = new JPanel();
			JLabel ajouterL = new JLabel("Ajouter une Personne");
			ajouter.add(ajouterL);
			
			JPanel consulter = new JPanel();
			JLabel consulterL = new JLabel("Consulter les Personnes");
			consulter.add(consulterL);
			
			JPanel modifier = new JPanel();
			JLabel modifierL = new JLabel();
			modifier.add(modifierL);
			
			onglets.addTab("ajouter une personne",ajouter);
			onglets.addTab("consulter des personnes",consulter);
			onglets.addTab("Modifier une personne",modifier);
			this.menuPersonne = onglets;
			return onglets;
		}
		
		public JPanel getOnglet(){
			return this.ongletPers;
		}
	
		
			
	}

	
