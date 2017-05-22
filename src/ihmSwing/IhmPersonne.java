package ihmSwing;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.*;

import java.util.ArrayList;
import java.util.SortedSet;


import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.LineBorder;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class IhmPersonne implements ItemListener
	{	
		JPanel cards;
		private JPanel ongletPers = new JPanel();
		private JTabbedPane menuPersonne = new JTabbedPane(SwingConstants.RIGHT);
		private JPanel panelDroite = new JPanel();
		private JTextField modifEmailPers;
		private JTextField modifPrenomPers;
		private JTextField modifNomPers;
		
		public IhmPersonne(JLabel titreOnglet)
		{
			
			BorderLayout bl_ongletPers = new BorderLayout();
			bl_ongletPers.setHgap(50);
			this.ongletPers.setLayout(bl_ongletPers);
			this.ongletPers.add(titreOnglet);
			addComponentToPane(ongletPers);
			//itemStateChanged(event);
//			this.ongletPers.add(panelDroite,BorderLayout.EAST);
			
		}
		
		public void addComponentToPane(Container pane) {
	        //Put the JComboBox in a JPanel to get a nicer look.
	        JPanel ActionPersonne = new JPanel(); //use FlowLayout
	        ActionPersonne.setBackground(new Color(51, 102, 153));
	        String optionAjouter = "Ajouter une Personne";
	        String optionModifier = "Modifier une Personne";
	        

	        String comboBoxItems[] = { optionAjouter, optionModifier};
	        JComboBox cb = new JComboBox(comboBoxItems);
	        cb.setEditable(false);
	        cb.addItemListener(this);
	        ActionPersonne.add(cb);
	         
	        //TODO : ici, il serait intéréssant d'alleger la méthode en 
	        // mettant dans une autre méthode la creations des cards!
	        
	        //Create the "cards".
	        JPanel card1 = new JPanel();
	        
	        card1.setBackground(new Color(51, 153, 204));
	        card1.setLayout(null);
	        JLabel label_nom = new JLabel("nom :");
	        label_nom.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
	        label_nom.setBounds(115, 165, 100, 14);
	        card1.add(label_nom);
	        JTextField champ_nom = new JTextField(20);
	        champ_nom.setBackground(new Color(51, 102, 153));
	        champ_nom.setBounds(264, 162, 166, 20);
	        card1.add(champ_nom);
	        JLabel lblPrnom = new JLabel("pr\u00E9nom :");
	        lblPrnom.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
	        lblPrnom.setLabelFor(champ_nom);
	        lblPrnom.setBounds(115, 73, 100, 14);
	        card1.add(lblPrnom);
	        JTextField champ_prenom = new JTextField(20);
	        champ_prenom.setBackground(new Color(51, 102, 153));
	        label_nom.setLabelFor(champ_prenom);
	        champ_prenom.setBounds(264, 70, 166, 20);
	        card1.add(champ_prenom);
	        JLabel label_email = new JLabel("email :");
	        label_email.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
	        label_email.setBounds(115, 251, 100, 14);
	        card1.add(label_email);
	        JTextField champ_mail = new JTextField("exemple@exemple.com", 20);
	        champ_mail.setBackground(new Color(51, 102, 153));
	        label_email.setLabelFor(champ_mail);
	        champ_mail.setBounds(264, 248, 166, 20);
	        card1.add(champ_mail);

	         
	        JPanel card2 = new JPanel();
	        card2.setBackground(new Color(51, 153, 204));
	        
	        
	        JPanel card3 = new JPanel();
	        card3.add(new JTextField("TextField", 20));
	        card3.add(new JButton("Supprimer"));
	         
	        //Create the panel that contains the "cards".
	        cards = new JPanel(new CardLayout());
	        cards.add(card1, optionAjouter);
	        JButton button = new JButton("Ajouter");
	        button.setBounds(242, 353, 120, 23);
	        card1.add(button);
	        cards.add(card2, optionModifier);
	        card2.setLayout(null);
	        
	        List liste_personne = new List();
	        liste_personne.setMultipleSelections(false);
	        liste_personne.setMultipleMode(false);
	        liste_personne.setBounds(37, 80, 190, 256);
	        liste_personne.setBackground(new Color(51, 102, 153));
	        liste_personne.setMultipleMode(false);
	        liste_personne.add("premier membre", 0);
	        liste_personne.add("second membre", 1);
	        liste_personne.add("troisième membre", 2);
	        card2.add(liste_personne);
	       
	        
	        JLabel lblListeDesPersonnes = new JLabel("Liste des personnes enregistr\u00E9es :");
	        lblListeDesPersonnes.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
	        lblListeDesPersonnes.setLabelFor(liste_personne);
	        lblListeDesPersonnes.setBounds(37, 43, 249, 16);
	        card2.add(lblListeDesPersonnes);
	        
	        JButton btnModifier = new JButton("Modifier");
	        btnModifier.setBounds(270, 196, 89, 23);
	        card2.add(btnModifier);
	        
	        JLabel lbl_nom_pers = new JLabel("Nom de candidat :");
	        lbl_nom_pers.setForeground(new Color(102, 102, 102));
	        lbl_nom_pers.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
	        lbl_nom_pers.setBounds(409, 80, 181, 14);
	        card2.add(lbl_nom_pers);
	        
	        JLabel lbl_prenom_pers = new JLabel("Pr\u00E9nom :");
	        lbl_prenom_pers.setForeground(new Color(102, 102, 102));
	        lbl_prenom_pers.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
	        lbl_prenom_pers.setBounds(409, 174, 181, 14);
	        card2.add(lbl_prenom_pers);
	        
	        JLabel lbl_email_pers = new JLabel("email :");
	        lbl_email_pers.setForeground(new Color(102, 102, 102));
	        lbl_email_pers.setBackground(new Color(0, 0, 0));
	        lbl_email_pers.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
	        lbl_email_pers.setBounds(409, 265, 181, 14);
	        card2.add(lbl_email_pers);
	        
	        modifEmailPers = new JTextField();
	        modifEmailPers.setEnabled(false);
	        modifEmailPers.setEditable(false);
	        modifEmailPers.setBackground(new Color(102, 102, 102));
	        lbl_email_pers.setLabelFor(modifEmailPers);
	        modifEmailPers.setBounds(406, 287, 160, 20);
	        card2.add(modifEmailPers);
	        modifEmailPers.setColumns(10);
	        
	        modifPrenomPers = new JTextField();
	        modifPrenomPers.setEnabled(false);
	        modifPrenomPers.setEditable(false);
	        modifPrenomPers.setBackground(new Color(102, 102, 102));
	        lbl_prenom_pers.setLabelFor(modifPrenomPers);
	        modifPrenomPers.setBounds(406, 197, 160, 20);
	        card2.add(modifPrenomPers);
	        modifPrenomPers.setColumns(10);
	        
	        modifNomPers = new JTextField();
	        modifNomPers.setBackground(new Color(102, 102, 102));
	        modifNomPers.setEditable(false);
	        modifNomPers.setEnabled(false);
	        lbl_nom_pers.setLabelFor(modifNomPers);
	        modifNomPers.setBounds(406, 105, 160, 20);
	        card2.add(modifNomPers);
	        modifNomPers.setColumns(10);
	        
	        JButton btnValiderLaModification = new JButton("Valider la modification");
	        btnValiderLaModification.setBounds(250, 351, 160, 23);
	        card2.add(btnValiderLaModification);
	        
	         
	        pane.add(ActionPersonne, BorderLayout.PAGE_START);
	        pane.add(cards, BorderLayout.CENTER);
	    }
	     
	    public void itemStateChanged(ItemEvent evt) {
	        CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, (String)evt.getItem());
	    }	
		
		public JPanel getOnglet(){
			return this.ongletPers;
		}
	}

	
