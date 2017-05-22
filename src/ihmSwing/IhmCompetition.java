package ihmSwing;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;


public class IhmCompetition implements ItemListener
	{	
		JPanel cards;
		private JPanel ongletComp = new JPanel();
		private JTabbedPane menuComp = new JTabbedPane(SwingConstants.RIGHT);
		private JPanel panelDroite = new JPanel();
		
		public IhmCompetition(JLabel titreOnglet)
		{
			
			this.ongletComp.setLayout(new BorderLayout());
			this.ongletComp.add(titreOnglet);
			addComponentToPane(ongletComp);
			//itemStateChanged(event);
//			this.ongletPers.add(panelDroite,BorderLayout.EAST);
			
		}
		
		public void addComponentToPane(Container pane) {
	        //Put the JComboBox in a JPanel to get a nicer look.
	        JPanel comboBoxPane = new JPanel(); //use FlowLayout
	        String optionAjouter = "Ajouter une Compettion";
	        String optionModifier = "Modifier une Competition";
	        

	        String comboBoxItems[] = { optionAjouter, optionModifier};
	        JComboBox cb = new JComboBox(comboBoxItems);
	        cb.setEditable(false);
	        cb.addItemListener(this);
	        comboBoxPane.add(cb);
	         
	        //TODO : ici, il serait int�r�ssant d'alleger la m�thode en 
	        // mettant dans une autre m�thode la creations des cards!
	        
	        //Create the "cards".
	        JPanel card1 = new JPanel();
	        card1.add(new JLabel("nom de la comp�tition:"));
	        card1.add(new JTextField(20));
	        card1.add(new JLabel("Type :"));
	        card1.add(new JButton("Solo"));
	        card1.add(new JButton("Duo"));
	         
	        JPanel card2 = new JPanel();
	        card2.add(new JTextField("TextField", 20));
	        
	        
	        JPanel card3 = new JPanel();
	        card3.add(new JTextField("TextField", 20));
	        card3.add(new JButton("Supprimer"));
	         
	        //Create the panel that contains the "cards".
	        cards = new JPanel(new CardLayout());
	        cards.add(card1, optionAjouter);
	        cards.add(card2, optionModifier);
	        
	         
	        pane.add(comboBoxPane, BorderLayout.CENTER);
	        pane.add(cards, BorderLayout.SOUTH);
	    }
	     
	    public void itemStateChanged(ItemEvent evt) {
	        CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, (String)evt.getItem());
	    }
		
		//Menu de l'onglet Personne
//		public JTabbedPane makeSousMenuPersonne(){
//			JTabbedPane onglets = new JTabbedPane();
//			
//			JPanel ajouter = new JPanel();
//			JLabel ajouterL = new JLabel("Ajouter une Personne");
//			ajouter.add(ajouterL);
//			
//			JPanel consulter = new JPanel();
//			JLabel consulterL = new JLabel("Consulter les Personnes");
//			consulter.add(consulterL);
//			
//			JPanel modifier = new JPanel();
//			JLabel modifierL = new JLabel();
//			modifier.add(modifierL);
//			
//			onglets.addTab("ajouter une personne",ajouter);
//			onglets.addTab("consulter des personnes",consulter);
//			onglets.addTab("Modifier une personne",modifier);
//			this.menuPersonne = onglets;
//			return onglets;
//		}
		
		public JPanel getOnglet(){
			return this.ongletComp;
		}
	
		
			
	}
