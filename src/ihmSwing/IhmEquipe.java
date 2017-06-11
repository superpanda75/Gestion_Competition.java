package ihmSwing;

import java.awt.*;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;

import inscriptions.Inscriptions;

import inscriptions.Equipe;



public class IhmEquipe// implements ItemListener
	{
		
		private JTextField	modifNomEqui	= mkTfNomEqui();
		private JTextField	modifMembres	= mkTfMembres();
		private JTextField	modifNomPers	= mkTfNom();
		
		private JLabel lblNomEqui 	= mkLbNomEqui();
		private JLabel lblMembres 	= mkLbMembres();
		private JLabel lblNom		= mkLbNomPers();
		
		private final Inscriptions inscriptions ; 
		private Map<Integer, Equipe> mapEquipe;

		public IhmEquipe(Inscriptions inscriptions)
		{
			this.inscriptions = inscriptions;
		}
		
		private void associateLabels(){
			lblNomEqui.setLabelFor(modifNomEqui);
			lblMembres.setLabelFor(modifMembres);
			lblNom.setLabelFor(modifNomPers);
		}
		
		private JLabel mkLbListEqui(){
			JLabel lblListeDesEquipes = new JLabel("Liste des personnes enregistr\u00E9es :");
			lblListeDesEquipes.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblListeDesEquipes.setBounds(37, 43, 249, 16);
		return lblListeDesEquipes;
		}
		
		private JLabel mkLbNomPers(){
			JLabel lbl_nom_Equi = new JLabel("Nom de candidat :");
			lbl_nom_Equi.setForeground(new Color(102, 102, 102));
			lbl_nom_Equi.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lbl_nom_Equi.setBounds(409, 80, 181, 14);
		return lbl_nom_Equi;
		}
		
		private JLabel mkLbMembres(){
			JLabel lbl_Membres = new JLabel("Pr\u00E9nom :");
			lbl_Membres.setForeground(new Color(102, 102, 102));
			lbl_Membres.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lbl_Membres.setBounds(409, 174, 181, 14);
		return lbl_Membres;
		}
		
		private JLabel mkLbNomEqui(){
			JLabel lbl_Nom_Equi = new JLabel("email :");
			lbl_Nom_Equi.setForeground(new Color(102, 102, 102));
			lbl_Nom_Equi.setBackground(new Color(0, 0, 0));
			lbl_Nom_Equi.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lbl_Nom_Equi.setBounds(409, 265, 181, 14);
		return lbl_Nom_Equi;
		}
		
		private JTextField mkTfNomEqui(){
			modifNomEqui = new JTextField();
			modifNomEqui.setEnabled(false);
			modifNomEqui.setEditable(false);
			modifNomEqui.setBackground(new Color(102, 102, 102));
			modifNomEqui.setBounds(406, 287, 160, 20);
			modifNomEqui.setColumns(10);
		return modifNomEqui;
		}

		private JTextField mkTfMembres(){
			modifMembres = new JTextField();
			modifMembres.setEnabled(false);
			modifMembres.setEditable(false);
			modifMembres.setBackground(new Color(102, 102, 102));
			modifMembres.setBounds(406, 197, 160, 20);
			modifMembres.setColumns(10);
		return modifMembres;
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
			

			
		public JPanel getCardModif(){
			
			JPanel cardPersonnes = new JPanel();
			cardPersonnes.setBackground(new Color(51, 153, 204));
			cardPersonnes.setLayout(null);
			cardPersonnes.add(mkLbListEqui());
			
			cardPersonnes.add(modifNomEqui);
			cardPersonnes.add(lblNomEqui);
			
			cardPersonnes.add(modifMembres);
			cardPersonnes.add(lblMembres);
			
			cardPersonnes.add(modifNomPers);
			cardPersonnes.add(lblNom);		
			
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
					return mapEquipe.size() + 1;
				}
				
				@Override
				public String getElementAt(int index)
				{
					if (index < mapEquipe.size())
						return mapEquipe.get(index).getNom();
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
			mapEquipe = new TreeMap<>();
			int i = 0;
			for (Equipe e : inscriptions.getEquipes())
				mapEquipe.put(i++, e);
		}
		

		private ListSelectionListener getListPersonneSelectionListener()
		{
			return new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e)
				{
					JList<String> source = (JList<String>) e.getSource();
					int index = source.getSelectedIndex();
					System.out.println(index);
					System.out.println(source.getModel().getSize() -1);
					if (index == source.getModel().getSize() - 1){
						//g�rer l'ajout ici
						System.out.println("Ajout d'une personne");
					}
					else{						
						//g�rer la modif ici
						activerChampNom(mapEquipe.get(index).getNom());
						
						System.out.println("Selection de " + mapEquipe.get(index).getNom());
					}
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
		 * Permet d'activer un champ et de le remplir
		 * @param data
		 */
		public void activerChampEmail(String data){
			modifNomEqui.setText("");
			modifNomEqui.setEditable(true);
		    modifNomEqui.setEnabled(true);
		    modifNomEqui.setBackground(new Color(51, 102, 153));
		    lblNomEqui.setForeground(new Color(0,0,0));
		    modifNomEqui.setText(data);	
		}
		
		/**
		 * Permet d'activer un champ et de le remplir
		 * @param data
		 */
		public void activerChampPrenom(String data){
			modifMembres.setText("");
			modifMembres.setEditable(true);
			modifMembres.setEnabled(true);
			modifMembres.setBackground(new Color(51, 102, 153));
		    lblMembres.setForeground(new Color(0,0,0));
		    modifMembres.setText(data);	
		}
		
		/**
		 * Permet d'activer un champ et de le remplir
		 * @param data
		 */
		public void activerChampNom(String data){
			modifNomPers.setEditable(true);
			modifNomPers.setText("");
		    modifNomPers.setEnabled(true);
		    modifNomPers.setBackground(new Color(51, 102, 153));
		    lblNom.setForeground(new Color(0,0,0));
		    modifNomPers.setText(data);	
		}
		
		/**
		 * 
		 * Cette m�thode permet de d�sactiver le champ
		 * @param lbl
		 * @param modifNomEqui
		 */
		public void desactiverChampMail(){
			modifNomEqui.setEditable(false);
		    modifNomEqui.setEnabled(false);
		    modifNomEqui.setBackground(new Color(102, 102, 102));
		    lblNomEqui.setForeground(new Color(102, 102, 102));
		    modifNomEqui.setText("");
		}
		
		/**
		 * 
		 * Cette m�thode permet de d�sactiver le champ
		 * @param lbl
		 * @param modifMembres
		 */
		public void desactiverChampPrenom(){
			modifMembres.setEditable(false);
		    modifMembres.setEnabled(false);
		    modifMembres.setBackground(new Color(102, 102, 102));
		    lblMembres.setForeground(new Color(102, 102, 102));
		    modifMembres.setText("");
		}
		
		/**
		 * 
		 * Cette m�thode permet de d�sactiver le champ
		 * @param lbl
		 * @param modifNomPers
		 */
		public void desactiverChampNom(){
			modifNomPers.setEditable(false);
		    modifNomPers.setEnabled(false);
		    modifNomPers.setBackground(new Color(102, 102, 102));
		    lblNom.setForeground(new Color(102, 102, 102));
		    modifNomPers.setText("");
		}

		
		
		public JPanel getOnglet()
		{
			JPanel ongletPers = new JPanel();
			associateLabels();
			ongletPers.setLayout(new BorderLayout());
			ongletPers.setName("Personnes");
			ongletPers.add(getCardModif());
			return ongletPers;
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
			return modifMembres.getText();
		}
		
		/**
		 * 
		 * @param String  email
		 */
		private String getChampMail(){
			return modifNomEqui.getText();
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
			modifMembres.setText(firstname);
		}
		
		/**
		 * 
		 * @param String  email
		 */
		private void setChampMail(String email){
			modifNomEqui.setText(email);
		}
		
		/**
		 * @return the lblEmail
		 */
		private JLabel getLblEmail()
		{
			return lblNomEqui;
		}

		/**
		 * @param lblEmail the lblEmail to set
		 */
		private void setLblEmail(JLabel lblEmail)
		{
			this.lblNomEqui = lblEmail;
		}

		/**
		 * @return the lblPrenom
		 */
		private JLabel getLblPrenom()
		{
			return lblMembres;
		}

		/**
		 * @param lblPrenom the lblPrenom to set
		 */
		private void setLblPrenom(JLabel lblPrenom)
		{
			this.lblMembres = lblPrenom;
		}

		/**
		 * @return the lblNom
		 */
		private JLabel getLblNom()
		{
			return lblNom;
		}

		/**
		 * @param lblNom the lblNom to set
		 */
		private void setLblNom(JLabel lblNom)
		{
			this.lblNom = lblNom;
		}
		
	}
