package ihmSwing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JTextField	modifNomComp	= mkTfNomComp();
	private JTextField	modifDateComp	= mkTfDateComp();
	private JRadioButton	modifEnEquipe	= mkTfEnEquipe();

	private JLabel lblNomComp		= mkLbNomComp();
	private JLabel lblDateComp 	= mkLbDateComp();
	private JLabel lblEnEquipeComp 	= mkLbEnEquipeComp();


	private final Inscriptions inscriptions ; 
	private Map<Integer, Competition> mapCompetition;

	public IhmCompetition(Inscriptions inscriptions)
	{
		this.inscriptions = inscriptions;
	}

	private void associateLabels(){
		lblNomComp.setLabelFor(modifNomComp);
		lblDateComp.setLabelFor(modifDateComp);
		lblEnEquipeComp.setLabelFor(modifEnEquipe);

	}

	private JLabel mkLbListComp(){
		JLabel lblListeDesCompetitions = new JLabel("Liste des competitions enregistr\u00E9es :");
		lblListeDesCompetitions.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lblListeDesCompetitions.setBounds(37, 43, 249, 16);
		return lblListeDesCompetitions;
	}

	private JTextField mkTfNomComp(){
		modifNomComp = new JTextField();
		modifNomComp.setBackground(new Color(102, 102, 102));
		modifNomComp.setEditable(false);
		modifNomComp.setEnabled(false);
		modifNomComp.setBounds(406, 105, 160, 20);
		modifNomComp.setColumns(10);
		return modifNomComp;
	}
	private JLabel mkLbNomComp(){
		JLabel lbl_nom_comp = new JLabel("Nom de competition :");
		lbl_nom_comp.setForeground(new Color(102, 102, 102));
		lbl_nom_comp.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lbl_nom_comp.setBounds(409, 80, 181, 14);
		return lbl_nom_comp;
	}

	private JLabel mkLbEnEquipeComp(){
		JLabel lblEnEquipeComp = new JLabel(" En Equipe:");
		lblEnEquipeComp.setForeground(new Color(102, 102, 102));
		lblEnEquipeComp.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lblEnEquipeComp.setBounds(409, 174, 181, 14);
		return lblEnEquipeComp;
	}

	private JLabel mkLbDateComp(){
		JLabel lblDateComp = new JLabel("Date :");
		lblDateComp.setForeground(new Color(102, 102, 102));
		lblDateComp.setBackground(new Color(0, 0, 0));
		lblDateComp.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		lblDateComp.setBounds(409, 265, 181, 14);
		return lblDateComp;
	}

	private JTextField mkTfDateComp(){
		modifDateComp = new JTextField();
		modifDateComp.setEnabled(false);
		modifDateComp.setEditable(false);
		modifDateComp.setBackground(new Color(102, 102, 102));
		modifDateComp.setBounds(406, 287, 160, 20);
		modifDateComp.setColumns(10);
		return modifDateComp;
	}

	private JRadioButton mkTfEnEquipe(){
		modifEnEquipe = new JRadioButton();
		modifEnEquipe.setEnabled(false);
		modifEnEquipe.setBackground(new Color(102, 102, 102));
		modifEnEquipe.setBounds(406, 197, 160, 20);
		return modifEnEquipe;
	}

	public JPanel getCardModif(){

		JPanel cardPersonnes = new JPanel();
		cardPersonnes.setBackground(new Color(51, 153, 204));
		cardPersonnes.setLayout(null);
		cardPersonnes.add(mkLbListComp());

		cardPersonnes.add(modifDateComp);
		cardPersonnes.add(lblDateComp);

		cardPersonnes.add(modifEnEquipe);
		cardPersonnes.add(lblEnEquipeComp);

		cardPersonnes.add(modifNomComp);
		cardPersonnes.add(lblNomComp);		

		JButton btnValiderLaModification = new JButton("Valider");
		btnValiderLaModification.setBounds(250, 351, 160, 23);
		btnValiderLaModification.addActionListener(getValidationSelectionListener());
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
				return mapCompetition.size() + 1;
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
		for (Competition comp: inscriptions.getCompetitions())
			mapCompetition.put(i++, comp);
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
					//gérer l'ajout ici
					System.out.println("Ajout d'une competition");
				}
				else{						
					//gérer la modif ici
					activerChampNom(mapCompetition.get(index).getNom());
					activerChampDate(mapCompetition.get(index).getDateCloture());
					activerChampEnEquipe(mapCompetition.get(index).getEnEquipe());

					System.out.println("Selection de " + mapCompetition.get(index).getNom());
				}
			}
		};
	}

	private ActionListener getValidationSelectionListener()
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				LocalDate dateComp = LocalDate.parse(getChampDate());
				boolean enEquipe = enEquipe();
				inscriptions.createCompetition(getChampNom(), dateComp, enEquipe);
				JOptionPane.showMessageDialog(null,
						getChampNom() + "  à bien été ajouter !", "Information",
						JOptionPane.INFORMATION_MESSAGE);
				rafraichirMap();
			}
			private boolean enEquipe() {
				boolean e = modifEnEquipe.isSelected()? true : false;
				return e;
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
	public void activerChampEnEquipe(Boolean data){
		modifDateComp.setText("");
		modifDateComp.setEditable(true);
		modifDateComp.setEnabled(true);
		modifDateComp.setBackground(new Color(51, 102, 153));
		lblDateComp.setForeground(new Color(0,0,0));
		setChampEnEquipe(true);
	}

	/**
	 * Permet d'activer un champ et de le remplir
	 * @param data
	 */
	public void activerChampDate(LocalDate data){
		modifEnEquipe.setText("");
		modifEnEquipe.setEnabled(true);
		modifEnEquipe.setBackground(new Color(51, 102, 153));
		lblEnEquipeComp.setForeground(new Color(0,0,0));
		setChampDate(data);	
	}

	/**
	 * Permet d'activer un champ et de le remplir
	 * @param data
	 */
	public void activerChampNom(String data){
		modifNomComp.setEditable(true);
		modifNomComp.setText("");
		modifNomComp.setEnabled(true);
		modifNomComp.setBackground(new Color(51, 102, 153));
		lblNomComp.setForeground(new Color(0,0,0));
		modifNomComp.setText(data);	
	}

	/**
	 * 
	 * Cette méthode permet de désactiver le champ
	 * @param lbl
	 * @param modifDateComp
	 */
	public void desactiverChampMail(){
		modifDateComp.setEditable(false);
		modifDateComp.setEnabled(false);
		modifDateComp.setBackground(new Color(102, 102, 102));
		lblDateComp.setForeground(new Color(102, 102, 102));
		modifDateComp.setText("");
	}

	/**
	 * 
	 * Cette méthode permet de désactiver le champ
	 * @param lbl
	 * @param modifEnEquipe
	 */
	public void desactiverChampPrenom(){
		modifEnEquipe.setEnabled(false);
		modifEnEquipe.setBackground(new Color(102, 102, 102));
		lblEnEquipeComp.setForeground(new Color(102, 102, 102));
		modifEnEquipe.setText("");
	}

	/**
	 * 
	 * Cette méthode permet de désactiver le champ
	 * @param lbl
	 * @param modifNomComp
	 */
	public void desactiverChampNom(){
		modifNomComp.setEditable(false);
		modifNomComp.setEnabled(false);
		modifNomComp.setBackground(new Color(102, 102, 102));
		lblNomComp.setForeground(new Color(102, 102, 102));
		modifNomComp.setText("");
	}



	public JPanel getOnglet()
	{
		JPanel ongletPers = new JPanel();
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
		return modifNomComp.getText();
	}

	/**
	 * 
	 * @param String firstname
	 */
	private String getChampEnEquipe(){
		return modifEnEquipe.getText();
	}

	/**
	 * 
	 * @param String  date
	 */
	private String getChampDate(){
		return modifDateComp.getText();
	}

	/**
	 * 
	 * @param String name
	 */
	private void setChampNom(String name){
		modifNomComp.setText(name);
	}

	/**
	 * 
	 * @param String firstname
	 */
	private void setChampEnEquipe(boolean enEquipe){
		modifEnEquipe.setFocusable(enEquipe);
	}

	/**
	 * 
	 * @param String  email
	 */
	private void setChampDate(LocalDate Date){
		modifDateComp.setText(""+LocalDate.now());
	}

	/**
	 * @return the lblEmail
	 */
	private JLabel getLblDateComp()
	{
		return lblDateComp;
	}

	/**
	 * @param lblDate the lblEmail to set
	 */
	private void setLblDate(JLabel lblDate)
	{
		this.lblDateComp = lblDate;
	}

	/**
	 * @return the lblPrenom
	 */
	private JLabel getLblEnEquipe()
	{
		return lblEnEquipeComp;
	}

	/**
	 * @param lblEnEquipe the lblPrenom to set
	 */
	private void setLblEnEquipe(JLabel lblEnEquipe)
	{
		this.lblEnEquipeComp = lblEnEquipe;
	}

	/**
	 * @return the lblNom
	 */
	private JLabel getLblNomComp()
	{
		return lblNomComp;
	}

	/**
	 * @param lblNomComp the lblNom to set
	 */
	private void setLblNomComp(JLabel lblNomComp)
	{
		this.lblNomComp = lblNomComp;
	}

}
