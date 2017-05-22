package ihmSwing;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class IhmEquipe implements ItemListener
	{	
		JPanel cards;
		private JPanel ongletEqui = new JPanel();
		private JTabbedPane menuEqui = new JTabbedPane(SwingConstants.RIGHT);
		private JPanel panelDroite = new JPanel();
		
		public IhmEquipe(JLabel titreOnglet)
		{
			
			this.ongletEqui.setLayout(new BorderLayout());
			this.ongletEqui.add(titreOnglet);
	
			addComponentToPane(ongletEqui);
			//itemStateChanged(event);
//			this.ongletPers.add(panelDroite,BorderLayout.EAST);
			
 		}
		
		public void addComponentToPane(Container pane) {
	        //Put the JComboBox in a JPanel to get a nicer look.
	        JPanel comboBoxPane = new JPanel(); //use FlowLayout
	        String optionAjouter = "Ajouter une Equipe";
	        String optionModifier = "Modifier une Equipe";
	        

	        String comboBoxItems[] = { optionAjouter, optionModifier};
	        JComboBox cb = new JComboBox(comboBoxItems);
	        cb.setEditable(false);
	        cb.addItemListener(this);
	        comboBoxPane.add(cb);
	         
	        //TODO : ici, il serait intéréssant d'alleger la méthode en 
	        // mettant dans une autre méthode la creations des cards!
	        
	        //Create the "cards".
	        JPanel card1 = new JPanel();
	        card1.add(new JLabel("nom de l'équipe :"));
	        card1.add(new JTextField(20));
	        card1.add(new JLabel("membres"));
	        card1.add(new JTextField(20));
	       
	         
	        JPanel card2 = new JPanel();
	        card2.add(new JTextField("TextField", 20));
	        
	        
	        JPanel card3 = new JPanel();
	        card3.add(new JTextField("TextField", 20));
	        card3.add(new JButton("Supprimer"));
	         
	        //Create the panel that contains the "cards".
	        cards = new JPanel(new CardLayout());
	        cards.add(card1, optionAjouter);
        cards.add(card2, optionModifier);
        
	         
	        pane.add(comboBoxPane, BorderLayout.PAGE_START);
	        pane.add(cards, BorderLayout.SOUTH);
	    }
	     
	    public void itemStateChanged(ItemEvent evt) {
	        CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, (String)evt.getItem());
	    }
		
		
		
 		public JPanel getOnglet(){
 			return this.ongletEqui;
 		}






}
