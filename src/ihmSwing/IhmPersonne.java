package ihmSwing;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.*;
import inscriptions.*;



public class IhmPersonne// implements ItemListener
{

	private JTextField	modifEmailPers	= mkTfMail();
	private JTextField	modifPrenomPers	= mkTfPrenom();
	private JTextField	modifNomPers	= mkTfNom();

	private JLabel lblEmail 	= mkLbEmailPers();
	private JLabel lblPrenom 	= mkLbPrenomPers();
	private JLabel lblNom		= mkLbNomPers();
	private JButton btnValiderLaModification = new JButton("ajouter");
	private JButton supprimer = new JButton("supprimer");
	private JButton btnModifier = new JButton("Modifier");

	private final Inscriptions inscriptions ; 
	private Map<Integer, Personne> mapPersonnes;

	public IhmPersonne(Inscriptions inscriptions)
	{
		this.inscriptions = inscriptions;
		getCardModif();
	}

	private void associateLabels(){
		lblEmail.setLabelFor(modifEmailPers);
		lblPrenom.setLabelFor(modifPrenomPers);
		lblNom.setLabelFor(modifNomPers);
	}

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



	public JPanel getCardModif(){

		JPanel cardPersonnes = new JPanel();
		cardPersonnes.setBackground(new Color(51, 153, 204));
		cardPersonnes.setLayout(null);
		cardPersonnes.add(mkLbListPers());

		cardPersonnes.add(modifEmailPers);
		cardPersonnes.add(lblEmail);

		cardPersonnes.add(modifPrenomPers);
		cardPersonnes.add(lblPrenom);

		cardPersonnes.add(modifNomPers);
		cardPersonnes.add(lblNom);		



		btnValiderLaModification.setBounds(250, 351, 160, 23);
		btnValiderLaModification.addActionListener(getValidationSelectionListener());
		cardPersonnes.add(btnValiderLaModification);
		supprimer.setBounds(250, 400, 160, 23);
        supprimer.addActionListener(boutonSupprimerListener());
        cardPersonnes.add(supprimer);
        btnModifier.setBounds(250, 380, 160, 23);
        btnModifier.addActionListener(boutonModifierListener());
        cardPersonnes.add(btnModifier);


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
				System.out.println(index);
				System.out.println(source.getModel().getSize());
				if (index == source.getModel().getSize()){

					//gérer l'ajout ici
					System.out.println("Ajout d'une personne");
				}
				else{						
					//gérer la modif ici
					activerChampNom(mapPersonnes.get(index).getNom());
					activerChampPrenom(mapPersonnes.get(index).getPrenom());
					activerChampEmail(mapPersonnes.get(index).getMail());
					System.out.println("Selection de " + mapPersonnes.get(index).getNom());
				}
			}
		};

	}
	private ActionListener boutonSupprimerListener()
	{
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{		
				 	for(Personne pers : inscriptions.getPersonnes()){
				 		 if(pers.getNom().compareTo(modifNomPers.getText()) == 0){
				 			pers.delete();
				 		 }
				 		 
				 	}
					
				JOptionPane.showMessageDialog(null,
						getChampNom() + "  à bien été supprimé !", "Information",
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
				 	for(Personne pers : inscriptions.getPersonnes()){
				 		 if(pers.getNom().compareTo(modifNomPers.getText()) == 0){
				 			pers.delete();
				 		 }
				 		 
				 	}
					
				JOptionPane.showMessageDialog(null,
						getChampNom() + "  à bien été supprimé !", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}
		};
	}
	  
	private ActionListener getValidationSelectionListener()
	{

		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				inscriptions.createPersonne(modifNomPers.getText(),
						modifPrenomPers.getText(),
						modifEmailPers.getText()
						);	

				JOptionPane.showMessageDialog(null,
						getChampNom() + " " +getChampPrenom() + " à bien été ajouter !", "Information",
						JOptionPane.INFORMATION_MESSAGE);
				rafraichirMap();

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
		modifEmailPers.setText("");
		modifEmailPers.setEditable(true);
		modifEmailPers.setEnabled(true);
		modifEmailPers.setBackground(new Color(51, 102, 153));
		lblEmail.setForeground(new Color(0,0,0));
		modifEmailPers.setText(data);	
	}

	/**
	 * Permet d'activer un champ et de le remplir
	 * @param data
	 */
	public void activerChampPrenom(String data){
		modifPrenomPers.setText("");
		modifPrenomPers.setEditable(true);
		modifPrenomPers.setEnabled(true);
		modifPrenomPers.setBackground(new Color(51, 102, 153));
		lblPrenom.setForeground(new Color(0,0,0));
		modifPrenomPers.setText(data);	
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
	 * Cette méthode permet de désactiver le champ
	 * @param lbl
	 * @param modifEmailPers
	 */
	public void desactiverChampMail(){
		modifEmailPers.setEditable(false);
		modifEmailPers.setEnabled(false);
		modifEmailPers.setBackground(new Color(102, 102, 102));
		lblEmail.setForeground(new Color(102, 102, 102));
		modifEmailPers.setText("");
	}

	/**
	 * 
	 * Cette méthode permet de désactiver le champ
	 * @param lbl
	 * @param modifPrenomPers
	 */
	public void desactiverChampPrenom(){
		modifPrenomPers.setEditable(false);
		modifPrenomPers.setEnabled(false);
		modifPrenomPers.setBackground(new Color(102, 102, 102));
		lblPrenom.setForeground(new Color(102, 102, 102));
		modifPrenomPers.setText("");
	}

	/**
	 * 
	 * Cette méthode permet de désactiver le champ
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
	 * @return the lblEmail
	 */
	private JLabel getLblEmail()
	{
		return lblEmail;
	}

	/**
	 * @param lblEmail the lblEmail to set
	 */
	private void setLblEmail(JLabel lblEmail)
	{
		this.lblEmail = lblEmail;
	}

	/**
	 * @return the lblPrenom
	 */
	private JLabel getLblPrenom()
	{
		return lblPrenom;
	}

	/**
	 * @param lblPrenom the lblPrenom to set
	 */
	private void setLblPrenom(JLabel lblPrenom)
	{
		this.lblPrenom = lblPrenom;
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
//	private boolean mailValid() {
//		return getChampPrenom().matches("[a-zA-Z0-9._-]{1,20}@[a-zA-Z]{3,10}\\.[a-z]{2,6}");
//	}
//
//	private boolean prenomValid() {
//		return getChampPrenom().matches("[a-zA-Z ]{3,}");
//	}
//
//
//	private boolean nomValid() {
//		return getChampNom().matches("[a-zA-Z ]{3,}");
//	}
//
//	private void regexPersonne() {
//		// boutonEdit.setEnabled(verifyRegexField());
//		modifEmailPers.setBorder(BorderFactory.createLineBorder(mailValid() ? Color.GREEN : Color.RED));
//		modifNomPers.setBorder(BorderFactory.createLineBorder(nomValid() ? Color.GREEN : Color.RED));
//		modifPrenomPers.setBorder(BorderFactory.createLineBorder(prenomValid() ? Color.GREEN : Color.RED));
//		btnValiderLaModification.setEnabled((estValider("nom") && estValider("prenom") && estValider("mail")));
//	}
//
//	private boolean estValider(String s) {
//		switch (s) {
//		case "nom":
//			return nomValid();
//		case "prenom":
//			return prenomValid();
//		case "mail":
//			return mailValid();
//		}
//		return false;
//	}

}
