package ihmSwing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;

import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;

import inscriptions.*;



public class IhmEquipe// implements ItemListener
{

	private JTextField	modifNomEqui	= mkTfNomEqui();
	private JTextField	modifMembres	= mkTfMembres();
	private JComboBox liste1;

	private JLabel lblNomEqui 	= mkLbNomEqui();
	private JLabel lblMembres 	= mkLbMembres();

	private final Inscriptions inscriptions ; 
	private Map<Integer, Equipe> mapEquipe;
	private Map<Integer, Personne> mapPersonnes;

	private JPanel modifEquipe = new JPanel();
	private JDialog fenetre = new JDialog();
	private JButton btnValiderLaModification = new JButton("Valider");
	private JButton btnModifier = new JButton("Modifier");
	private JButton btnAddMembre = new JButton("Ajouter"); 
	private JButton supprimer = new JButton("Supprimer");



	public IhmEquipe(Inscriptions inscriptions)
	{
		this.inscriptions = inscriptions;
	}

	private void associateLabels(){
		lblNomEqui.setLabelFor(modifNomEqui);
		lblMembres.setLabelFor(modifMembres);
		lblNomEqui.setLabelFor(modifNomEqui);
	}

	private JLabel mkLbListEqui(){
		JLabel lblListeDesEquipes = new JLabel("Liste des equipes enregistr\u00E9es :");
		lblListeDesEquipes.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lblListeDesEquipes.setBounds(37, 43, 249, 16);
		return lblListeDesEquipes;
	}
	// LABEL POUR NOM EQUIPE
	private JLabel mkLbNomEqui(){
		JLabel lbl_nom_Equi = new JLabel("Nom de l'equipe :");
		lbl_nom_Equi.setForeground(new Color(102, 102, 102));
		lbl_nom_Equi.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lbl_nom_Equi.setBounds(409, 80, 181, 14);
		return lbl_nom_Equi;
	}

	private JLabel mkLbMembres(){
		JLabel lbl_Membres = new JLabel("Nom des membres :");
		lbl_Membres.setForeground(new Color(102, 102, 102));
		lbl_Membres.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lbl_Membres.setBounds(409, 174, 181, 14);
		return lbl_Membres;
	}

	// TEXTFIELD POUR NOM EQUIPE
	private JTextField mkTfNomEqui(){
		modifNomEqui = new JTextField();
		modifNomEqui.setEnabled(false);
		modifNomEqui.setEditable(false);
		modifNomEqui.setBackground(new Color(102, 102, 102));
		modifNomEqui.setBounds(406, 100, 160, 20);
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

	public JPanel getCardModif(){

		JPanel cardEquipes = new JPanel();
		cardEquipes.setBackground(new Color(51, 153, 204));
		cardEquipes.setLayout(null);
		cardEquipes.add(mkLbListEqui());

		cardEquipes.add(modifNomEqui);
		cardEquipes.add(lblNomEqui);

		cardEquipes.add(modifMembres);
		cardEquipes.add(lblMembres);

		cardEquipes.add(modifNomEqui);
		cardEquipes.add(lblNomEqui);		

		btnValiderLaModification.setBounds(250, 351, 160, 23);
		btnValiderLaModification.addActionListener(getValidationSelectionListener());
		cardEquipes.add(btnValiderLaModification);
		//		btnAddMembre.setBounds(100,300,160,23);
		//		btnAddMembre.addActionListener(getAddMembreSelectionListener());
		//		fenetre.add(btnAddMembre);
		supprimer.setBounds(250, 400, 160, 23);
		supprimer.addActionListener(boutonSupprimerListener());
		cardEquipes.add(supprimer);
		btnModifier.setBounds(250, 380, 160, 23);
		btnModifier.addActionListener(boutonModifierListener());
		cardEquipes.add(btnModifier);


		cardEquipes.add(getListEquipe());
		cardEquipes.add(getListMembreEquipe());

		return cardEquipes;
	}

	private ListModel<String> getListEquipeModel()
	{
		rafraichirMap();
		return new ListModel<String>(){

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
	private ListModel<String> getListMembreEquipeModel()
	{
		rafraichirMap2();
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
	private void rafraichirMap2()
	{
		mapPersonnes= new TreeMap<>();
		int i = 0;
		for (Personne p: inscriptions.getPersonnes())
			mapPersonnes.put(i++, p);
	}
	private void rafraichirMap()
	{

		mapEquipe = new TreeMap<>();
		int i = 0;
		for (Equipe e : inscriptions.getEquipes())
			mapEquipe.put(i++, e);

	}

	public JPanel getModifierEquipe(){

		modifEquipe.add(lblNomEqui);
		modifEquipe.add(modifNomEqui);
		modifEquipe.add(btnModifier);


		return modifEquipe;

	}
	private ListSelectionListener getListEquipeSelectionListener()
	{
		return new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				JList<String> source = (JList<String>) e.getSource();
				int index = source.getSelectedIndex();
				System.out.println(index);
				System.out.println(source.getModel().getSize() -1);
				if (index == source.getModel().getSize() -1 ){
					//g�rer l'ajout ici
					System.out.println("Ajout d'une personne");
				}
				else{						
					//g�rer la modif ici
					activerChampNom(mapEquipe.get(index).getNom());

					//					fenetre.setSize(400, 400);
					//					fenetre.add(getListMembreEquipe());
					//					fenetre.setVisible(true);
					//					regexNomEquipe();
					System.out.println("Selection de " + mapEquipe.get(index).getNom());

				}
			}
		};
	}
	private ListSelectionListener getListMembreEquipeSelectionListener()
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
					activerChampNom(mapPersonnes.get(index).getNom());

					fenetre.setSize(400, 400);
					fenetre.add(getListMembreEquipe());
					fenetre.setVisible(true);
					//					regexNomEquipe();
					System.out.println("Selection de " + mapPersonnes.get(index).getNom());
				}
			}
		};
	}
	//Ajouter une Equipe
	private ActionListener getValidationSelectionListener()
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{		
				inscriptions.createEquipe(getChampNom());
				JOptionPane.showMessageDialog(null,
						getChampNom() + "  � bien �t� ajouter !", "Information",
						JOptionPane.INFORMATION_MESSAGE);
				regexNomEquipe();
				rafraichirMap();

			}
		};
	}
	//Supprimer une equipe
	private ActionListener boutonSupprimerListener()
	{

		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{		
				for(Equipe equipe : inscriptions.getEquipes()){
					if(equipe.getNom().compareTo(modifNomEqui.getText()) == 0){
						equipe.delete();
					}

				}

				JOptionPane.showMessageDialog(null,
						getChampNom() + "  � bien �t� supprim� !", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}
		};
	}
	private ActionListener boutonModifierListener()
	{		
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{		
				//				 	for(Equipe e : inscriptions.getEquipes()){
				//
				//				 		 if(e.getNom().compareTo(mapEquipe.get(e).getNom()) == 0){
				//				 			e.setNom(getChampNom());	
				//				 			System.out.println();
				//				 		 }
				//				 	}
				fenetre.setSize(400, 150);
				fenetre.add(getModifierEquipe());	
				fenetre.setVisible(true);
				for(Equipe e : inscriptions.getEquipes()){
					if(e.getNom().compareTo(getChampNom())== 0){
						e.setNom(getChampNom());
					}
				}

			}


		};
	}

	private ActionListener getAddMembreSelectionListener()
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{		
				//ajouter des membres d'equipe Equipe.add(getChampNom());
				JOptionPane.showMessageDialog(null,
						getChampNom() + "  � bien �t� ajouter !", "Information",
						JOptionPane.INFORMATION_MESSAGE);
				rafraichirMap();

			}
		};
	} 

	private JList<String> getListEquipe(){
		JList<String> listeEquipe = new JList<>(getListEquipeModel());
		listeEquipe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeEquipe.setBackground(new Color(51, 102, 153));
		listeEquipe.setBounds(37, 80, 190, 256);
		listeEquipe.addListSelectionListener(getListEquipeSelectionListener());	
		return listeEquipe;
	}

	private JList<String> getListMembreEquipe(){
		JList<String> listeMembresEquipe = new JList<>(getListMembreEquipeModel());
		listeMembresEquipe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeMembresEquipe.setBackground(new Color(51, 102, 153));
		listeMembresEquipe.setBounds(37, 80, 190, 256);
		listeMembresEquipe.addListSelectionListener(getListMembreEquipeSelectionListener());	
		return listeMembresEquipe;
	}
	//getListMembreEquipeSelectionListener


	/**
	 * Permet d'activer un champ et de le remplir
	 * @param data
	 */
	public void activerChampNomEquipe(String data){
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
		modifNomEqui.setEditable(true);
		modifNomEqui.setText("");
		modifNomEqui.setEnabled(true);
		modifNomEqui.setBackground(new Color(51, 102, 153));
		lblNomEqui.setForeground(new Color(0,0,0));
		modifNomEqui.setText(data);	
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
		modifNomEqui.setEditable(false);
		modifNomEqui.setEnabled(false);
		modifNomEqui.setBackground(new Color(102, 102, 102));
		lblNomEqui.setForeground(new Color(102, 102, 102));
		modifNomEqui.setText("");
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
		return modifNomEqui.getText();
	}


	/**
	 * 
	 * @param String name
	 */
	private void setChampNom(String name){
		modifNomEqui.setText(name);
	}


	/**
	 * @return the lblMembres
	 */
	private JLabel getLblMembre()
	{
		return lblMembres;
	}

	/**
	 * @param lblPrenom the lblPrenom to set
	 */
	private void setLblMembres(JLabel lblMembres)
	{
		this.lblMembres = lblMembres;
	}

	/**
	 * @return the lblNom
	 */
	private JLabel getLblNom()
	{
		return lblNomEqui;
	}

	/**
	 * @param lblNom the lblNom to set
	 * @wbp.parser.entryPoint
	 */
	private void setLblNom(JLabel lblNom)
	{
		this.lblNomEqui = lblNom;
	}

	/** REGEX -- controle le nom de l'equipe **/
	private boolean ValidationNom() {
		return getChampNom().matches("[a-zA-Z0-9 ]{1,}");
	}
	private void regexNomEquipe()
	{
		modifNomEqui.setBorder(BorderFactory.createLineBorder(ValidationNom() ? Color.GREEN : Color.RED));
		btnValiderLaModification.setEnabled(estValider());
	}
	private boolean estValider() 
	{
		if(ValidationNom() ? true : false);
		return false;

	}

}





