package ihmSwing;

import java.awt.*;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;

import inscriptions.Inscriptions;
import inscriptions.Personne;



public class IhmPersonne// implements ItemListener
	{
//		private JPanel	cards 			= new JPanel();
//		private JPanel	ongletPers		= new JPanel();

		
		private JTextField	modifEmailPers;
		private JTextField	modifPrenomPers;
		private JTextField	modifNomPers;
		
		private final Inscriptions inscriptions ; 
		private Map<Integer, Personne> mapPersonnes;

		public IhmPersonne(Inscriptions inscriptions)
		{
			this.inscriptions = inscriptions;
		}
		
		//public JPanel get
		private JLabel mkLbListPers(){
			JLabel lblListeDesPersonnes = new JLabel("Liste des personnes enregistr\u00E9es :");
			lblListeDesPersonnes.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblListeDesPersonnes.setBounds(37, 43, 249, 16);
			return lblListeDesPersonnes;
		}
		
		private JLabel mkLbNomPers(){
		JLabel lbl_nom_pers = new JLabel("Nom de candidat :");
		lbl_nom_pers.setForeground(new Color(102, 102, 102));
		lbl_nom_pers.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lbl_nom_pers.setBounds(409, 80, 181, 14);
		return lbl_nom_pers;
		}
		private JLabel mkLbPrenomPers(){
		JLabel lbl_prenom_pers = new JLabel("Pr\u00E9nom :");
		lbl_prenom_pers.setForeground(new Color(102, 102, 102));
		lbl_prenom_pers.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lbl_prenom_pers.setBounds(409, 174, 181, 14);
		return lbl_prenom_pers;
		}
		
		private JLabel mkLbEmailPers(){
			JLabel lbl_email_pers = new JLabel("email :");
			lbl_email_pers.setForeground(new Color(102, 102, 102));
			lbl_email_pers.setBackground(new Color(0, 0, 0));
			lbl_email_pers.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lbl_email_pers.setBounds(409, 265, 181, 14);
			return lbl_email_pers;
		}
		
		private JTextField mkTfMail(){
			modifEmailPers = new JTextField();
			modifEmailPers.setEnabled(false);
			modifEmailPers.setEditable(false);
			modifEmailPers.setBackground(new Color(102, 102, 102));
			modifEmailPers.setBounds(406, 287, 160, 20);
			modifEmailPers.setColumns(10);
			return modifEmailPers;
			}

			private JTextField mkTfPrenom(){
			modifPrenomPers = new JTextField();
			modifPrenomPers.setEnabled(false);
			modifPrenomPers.setEditable(false);
			modifPrenomPers.setBackground(new Color(102, 102, 102));
			modifPrenomPers.setBounds(406, 197, 160, 20);
			modifPrenomPers.setColumns(10);
			return modifPrenomPers;
			}
			
			private JTextField mkTfNom(){
			modifNomPers = new JTextField();
			modifNomPers.setBackground(new Color(102, 102, 102));
			modifNomPers.setEditable(false);
			modifNomPers.setEnabled(false);
			modifNomPers.setBounds(406, 105, 160, 20);
			modifNomPers.setColumns(10);
			return modifNomPers;
			}
			
			private void attach(JTextField tf, JLabel lb){
				lb.setLabelFor(tf);
			}
			
		public JPanel getCardModif(){
			
			JPanel cardPersonnes = new JPanel();
			cardPersonnes.setBackground(new Color(51, 153, 204));
			cardPersonnes.setLayout(null);
			cardPersonnes.add(mkLbListPers());
			
			
			JTextField mail = mkTfMail();
			JLabel lbMail = mkLbEmailPers();
			attach(mail,lbMail);
			cardPersonnes.add(mail);
			cardPersonnes.add(lbMail);
			
			JTextField prenom = mkTfPrenom();
			JLabel lbPrenom = mkLbPrenomPers();
			attach(prenom,lbPrenom);
			cardPersonnes.add(prenom);
			cardPersonnes.add(lbPrenom);
			
			JTextField nom = mkTfNom();
			JLabel lbNom = mkLbNomPers();
			attach(nom,lbNom);
			cardPersonnes.add(nom);
			cardPersonnes.add(lbNom);
			

			

			
//			JLabel lblListeDesPersonnes = new JLabel("Liste des personnes enregistr\u00E9es :");
//			lblListeDesPersonnes.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
//			lblListeDesPersonnes.setBounds(37, 43, 249, 16);
//			cardPersonnes.add(lblListeDesPersonnes);
//			
//			
//			//A supprimer
//			
////			JButton btnModifier = new JButton("Modifier");
////			btnModifier.setEnabled(false);
////			btnModifier.setBounds(270, 196, 89, 23);
////			cardPersonnes.add(btnModifier);
//			
//			//FIN A SUPPRIMER
//
////			JLabel lbl_nom_pers = new JLabel("Nom de candidat :");
////			lbl_nom_pers.setForeground(new Color(102, 102, 102));
////			lbl_nom_pers.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
////			lbl_nom_pers.setBounds(409, 80, 181, 14);
//			//lbl_nom_pers.setLabelFor();
//			cardPersonnes.add(lbl_nom_pers);
			
			

//			JLabel lbl_prenom_pers = new JLabel("Pr\u00E9nom :");
//			lbl_prenom_pers.setForeground(new Color(102, 102, 102));
//			lbl_prenom_pers.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
//			lbl_prenom_pers.setBounds(409, 174, 181, 14);
//			cardPersonnes.add(lbl_prenom_pers);

//			JLabel lbl_email_pers = new JLabel("email :");
//			lbl_email_pers.setForeground(new Color(102, 102, 102));
//			lbl_email_pers.setBackground(new Color(0, 0, 0));
//			lbl_email_pers.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
//			lbl_email_pers.setBounds(409, 265, 181, 14);
//			cardPersonnes.add(lbl_email_pers);

			

			JButton btnValiderLaModification = new JButton("Valider la modification");
			btnValiderLaModification.setBounds(250, 351, 160, 23);
			cardPersonnes.add(btnValiderLaModification);

			cardPersonnes.add(getListPersonne());

			return cardPersonnes;
		}
		
		private ListModel<String> getListPersonneModel()
		{
			rafraichirMap();
			return new ListModel<String>() {
				
				@Override
				public void removeListDataListener(ListDataListener l)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public int getSize()
				{
					return mapPersonnes.size() + 1;
				}
				
				@Override
				public String getElementAt(int index)
				{
					if (index < mapPersonnes.size())
						return mapPersonnes.get(index).getNom();
					else
						return "<Ajouter>";
				}
				
				@Override
				public void addListDataListener(ListDataListener l)
				{
					// TODO Auto-generated method stub
					
				}
			};
		}
		
		private void rafraichirMap()
		{
			mapPersonnes = new TreeMap<>();
			int i = 0;
			for (Personne p : inscriptions.getPersonnes())
				mapPersonnes.put(i++, p);
		}
		
		private ListSelectionListener getListPersonneSelectionListener()
		{
			return new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e)
				{
					JList<String> source = (JList<String>) e.getSource();
					int index = source.getSelectedIndex();
					if (index == source.getModel().getSize() - 1)
						System.out.println("Ajout d'une personne");
					else						
						System.out.println("Selection de " + mapPersonnes.get(index).getNom());
				}
			};
		}
		
		private JList<String> getListPersonne(){
			JList<String> listePersonnes = new JList<>(getListPersonneModel());
			listePersonnes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listePersonnes.setBackground(new Color(51, 102, 153));
			listePersonnes.setBounds(37, 80, 190, 256);
			listePersonnes.addListSelectionListener(getListPersonneSelectionListener());	
			return listePersonnes;
			}
		
		/**
		 * 
		 * @param String name
		 */
		private String getChampNom(){
			return modifNomPers.getText();
		}
		
		/**
		 * 
		 * @param String firstname
		 */
		private String getChampPrenom(){
			return modifPrenomPers.getText();
		}
		
		/**
		 * 
		 * @param String  email
		 */
		private String getChampMail(){
			return modifEmailPers.getText();
		}
		
		/**
		 * 
		 * @param String name
		 */
		private void setChampNom(String name){
			modifNomPers.setText(name);
		}
		
		/**
		 * 
		 * @param String firstname
		 */
		private void setChampPrenom(String firstname){
			modifPrenomPers.setText(firstname);
		}
		
		/**
		 * 
		 * @param String  email
		 */
		private void setChampMail(String email){
			modifEmailPers.setText(email);
		}
		
		/**
		 * Cette fonction permet d'activer un champ : activerChamp(libelle, JTextField)
		 * @param lbl
		 * @param tf
		 */
		public void activerChamp(JLabel lbl, JTextField tf, String data){
			tf.setText("");
			 tf.setEditable(true);
		     tf.setEnabled(true);
		     tf.setBackground(new Color(51, 102, 153));
		     lbl.setForeground(new Color(0,0,0));
		     tf.setText(data);	
		}
		
		/**
		 * Cette fonction permet de désactiver un champ en le grisant et en le désactivant : desactiverChamp(libelle, JTextField)
		 * @param lbl
		 * @param tf
		 */
		public void desactiverChamp(JLabel lbl, JTextField tf){
			 tf.setEditable(false);
		     tf.setEnabled(false);
		     tf.setBackground(new Color(102, 102, 102));
		     lbl.setForeground(new Color(102, 102, 102));
		     tf.setText("");
		}

		public JPanel getOnglet()
		{
			JPanel ongletPers = new JPanel();
			ongletPers.setLayout(new BorderLayout());
			ongletPers.setName("Personnes");
			ongletPers.add(getCardModif());
			return ongletPers;
		}
	}
