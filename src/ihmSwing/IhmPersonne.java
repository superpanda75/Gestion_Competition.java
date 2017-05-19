package ihmSwing;

import inscriptions.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class IhmPersonne implements ItemListener
	{	
		JPanel cards;
		private JPanel ongletPers = new JPanel();
		private JTabbedPane menuPersonne = new JTabbedPane(SwingConstants.RIGHT);
		private JPanel panelDroite = new JPanel();
		
		public IhmPersonne(JLabel titreOnglet)
		{
			this.ongletPers.setLayout(new BorderLayout());
			this.ongletPers.add(titreOnglet);
			addComponentToPane(ongletPers);
			//itemStateChanged(event);
//			this.ongletPers.add(panelDroite,BorderLayout.EAST);			
		}
		
		public void addComponentToPane(Container pane) {
	        //Put the JComboBox in a JPanel to get a nicer look.
	        JPanel comboBoxPane = new JPanel(); //use FlowLayout
	        String optionAjouter = "Ajouter une Personne";
	        String optionModifier = "Modifier une Personne";
	        

	        String comboBoxItems[] = { optionAjouter, optionModifier};
	        JComboBox cb = new JComboBox(comboBoxItems);
	        cb.setEditable(false);
	        cb.addItemListener(this);
	        comboBoxPane.add(cb);
	         
	        //TODO : ici, il serait intéréssant d'alleger la méthode en 
	        // mettant dans une autre méthode la creations des cards!
	        
	        //Create the "cards".
	        JPanel card1 = new JPanel();
	        card1.add(new JLabel("nom :"));
	        card1.add(new JTextField(20));
	        card1.add(new JLabel("prenom :"));
	        card1.add(new JTextField(20));
	        card1.add(new JLabel("email :"));
	        card1.add(new JTextField("exemple@exemple.com", 20));
	        card1.add(new JButton("Button 1"));
	        card1.add(new JButton("Button 2"));
	        card1.add(new JButton("Button 3"));
	         
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
	        pane.add(cards, BorderLayout.CENTER);
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
			return this.ongletPers;
		}
	
		public class ModeleDynamiqueObjet extends AbstractTableModel {
			    ArrayList<Personne> personnes = new ArrayList();
			    
			    public ArrayList<Personne> chargerDonnees()  {
					
					ArrayList<Personne> personnes = new ArrayList<>(); 
						for (Personne p : Inscriptions.getInscriptions().getPersonnes())
						{
							personnes.add(p);
						}
					 
					//catch (SQLException e) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					//}
					return personnes;
			    }
			 
			    private final String[] entetes = {"Prénom", "Nom", "e-mail"};
			 
			 
			    public int getRowCount() {
			        return personnes.size();
			    }
			 
			    public int getColumnCount() {
			        return entetes.length;
			    }
			 
			    public String getColumnName(int columnIndex) {
			        return entetes[columnIndex];
			    }
			 
			    public Object getValueAt(int rowIndex, int columnIndex) {
			        switch(columnIndex){
			            case 0:
			                return personnes.get(rowIndex).getPrenom();
			            case 1:
			                return personnes.get(rowIndex).getNom();
			            case 2:
			                return personnes.get(rowIndex).getMail();
			            default:
			                return null; //Ne devrait jamais arriver
			        }
			    }
			 
			    public void addAmi(Personne personne) {
			        personnes.add(personne);
			 
			        fireTableRowsInserted(personnes.size() -1, personnes.size() -1);
			    }
			 
			    public void removeAmi(int rowIndex) {
			        personnes.remove(rowIndex);
			 
			        fireTableRowsDeleted(rowIndex, rowIndex);
			    }
			}	
			
	}

	
