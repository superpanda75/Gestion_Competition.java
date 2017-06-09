package ihmSwing;

import java.awt.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;

import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;



public class IhmCompetition// implements ItemListener
	{
//		private JPanel	cards 			= new JPanel();
//		private JPanel	ongletPers		= new JPanel();

		
		private JTextField	modifNomComp;
		private JTextField	modifDateComp;
		private JTextField	modifEnEquipe;
		
		private final Inscriptions inscriptions ; 
		private Map<Integer, Competition> mapCompetition;

		public IhmCompetition(Inscriptions inscriptions)
		{
			this.inscriptions = inscriptions;
		}
		
		//public JPanel get
		private JLabel mkLbListPers(){
			JLabel lblListeDesCompetitions = new JLabel("Liste des competitions enregistr\u00E9es :");
			lblListeDesCompetitions.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblListeDesCompetitions.setBounds(37, 43, 249, 16);
			return lblListeDesCompetitions;
		}
		
		private JLabel mkLbNomComp(){
		JLabel lbl_nom_Comp = new JLabel("Nom de la competition :");
		lbl_nom_Comp.setForeground(new Color(102, 102, 102));
		lbl_nom_Comp.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lbl_nom_Comp.setBounds(409, 80, 181, 14);
		return lbl_nom_Comp;
		}
		private JLabel mkLbDateComp(){
		JLabel lbl_date_comp = new JLabel("Date");
		lbl_date_comp.setForeground(new Color(102, 102, 102));
		lbl_date_comp.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lbl_date_comp.setBounds(409, 174, 181, 14);
		return lbl_date_comp;
		}
		
		private JLabel mkLbEnEquipComp(){
			JLabel lbl_equipe_comp = new JLabel("Equipe");
			lbl_equipe_comp.setForeground(new Color(102, 102, 102));
			lbl_equipe_comp.setBackground(new Color(0, 0, 0));
			lbl_equipe_comp.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lbl_equipe_comp.setBounds(409, 265, 181, 14);
			return lbl_equipe_comp;
		}
		
		private JTextField mkTfNomComp(){
			modifNomComp = new JTextField();
			modifNomComp.setEnabled(false);
			modifNomComp.setEditable(false);
			modifNomComp.setBackground(new Color(102, 102, 102));
			modifNomComp.setBounds(406, 287, 160, 20);
			modifNomComp.setColumns(10);
			return modifNomComp;
			}

			private JTextField mkTfPrenom(){
			modifDateComp = new JTextField();
			modifDateComp.setEnabled(false);
			modifDateComp.setEditable(false);
			modifDateComp.setBackground(new Color(102, 102, 102));
			modifDateComp.setBounds(406, 197, 160, 20);
			modifDateComp.setColumns(10);
			return modifDateComp;
			}
			
			private JTextField mkTfNom(){
			modifEnEquipe = new JTextField();
			modifEnEquipe.setBackground(new Color(102, 102, 102));
			modifEnEquipe.setEditable(false);
			modifEnEquipe.setEnabled(false);
			modifEnEquipe.setBounds(406, 105, 160, 20);
			modifEnEquipe.setColumns(10);
			return modifEnEquipe;
			}
			
			private void attach(JTextField tf, JLabel lb){
				lb.setLabelFor(tf);
			}
			
		public JPanel getCardModif(){
			
			JPanel cardCompetition = new JPanel();
			cardCompetition.setBackground(new Color(51, 153, 204));
			cardCompetition.setLayout(null);
			cardCompetition.add(mkLbListPers());
			
			
			JTextField mail = mkTfNomComp();
			JLabel lbMail = mkLbEnEquipComp();
			attach(mail,lbMail);
			cardCompetition.add(mail);
			cardCompetition.add(lbMail);
			
			JTextField prenom = mkTfPrenom();
			JLabel lbPrenom = mkLbDateComp();
			attach(prenom,lbPrenom);
			cardCompetition.add(prenom);
			cardCompetition.add(lbPrenom);
			
			JTextField nom = mkTfNom();
			JLabel lbNom = mkLbNomComp();
			attach(nom,lbNom);
			cardCompetition.add(nom);
			cardCompetition.add(lbNom);
			

			

			
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
			cardCompetition.add(btnValiderLaModification);

			cardCompetition.add(getListCompetition());

			return cardCompetition;
		}
		
		private ListModel<String> getListCompetitionModel()
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
					return   mapCompetition.size() + 1;
				}
				
				@Override
				public String getElementAt(int index)
				{
					if (index < mapCompetition.size())
						return mapCompetition.get(index).getNom();
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
			mapCompetition = new TreeMap<>();
			int i = 0;
			for (Competition comp : inscriptions.getCompetitions())
				mapCompetition.put(i++, comp);
		}
		
		private ListSelectionListener getListCompetitionSelectionListener()
		{
			return new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e)
				{
					JList<String> source = (JList<String>) e.getSource();
					int index = source.getSelectedIndex();
					if (index == source.getModel().getSize() - 1)
						System.out.println("Ajout d'une personne");
					else						
						System.out.println("Selection de " + mapCompetition.get(index).getNom());
				}
			};
		}
		
		private JList<String> getListCompetition(){
			JList<String> listeCompetition = new JList<>(getListCompetitionModel());
			listeCompetition.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listeCompetition.setBackground(new Color(51, 102, 153));
			listeCompetition.setBounds(37, 80, 190, 256);
			listeCompetition.addListSelectionListener(getListCompetitionSelectionListener());	
			return listeCompetition;
			}
		
		/**
		 * 
		 * @param String name
		 */
		private String getChampNom(){
			return modifEnEquipe.getText();
		}
		
		/**
		 * 
		 * @param LocalDate Date
		 *
		 */
		private String getChampDate(){
			return modifDateComp.getText();
		}
		
		/**
		 * 
		 * @param String  equipe
		 */
		private String getChampEnEquipe(){
			return modifNomComp.getText();
		}
		
		/**
		 * 
		 * @param String name
		 */
		private void setChampNom(String name){
			modifEnEquipe.setText(name);
		}
		
		/**
		 * 
		 * @param String firstname
		 */
		private void setChampDate(String firstname){
			modifDateComp.setText(firstname);
		}
		
		/**
		 * 
		 * @param String  email
		 */
		private void setChampEnEquipe(String enEquipe){
			modifNomComp.setText(enEquipe);
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
