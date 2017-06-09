package ihmSwing;

import inscriptions.Inscriptions;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import com.toedter.calendar.JCalendar;

import java.util.ArrayList;
import java.util.SortedSet;

import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Personne;

import javax.swing.border.LineBorder;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class IhmCompetition extends JFrame implements ItemListener, ActionListener {
		JPanel				cards;
		private JPanel		ongletPers		= new JPanel();
		private JTabbedPane	menuComp	= new JTabbedPane(SwingConstants.RIGHT);
		private JPanel		panelDroite		= new JPanel();
		private final JCheckBox check1 = new JCheckBox("");
		private final JCheckBox check = new JCheckBox("");
		private final JButton btnDate = new JButton("Choisissez la date");
		private JTextField 	modifNomComp;
		private JTextField	modifDateComp;
		private JTextField	modifEnEquipe;
		
		private final Inscriptions inscription = inscriptions.Inscriptions.getInscriptions(); 
		
		private Integer id_competition_modif;
		private String nom_competition_modif;
		private LocalDate date_competition_modif;
		private Boolean equipe_competition_modif;

		
		public IhmCompetition(JLabel titreOnglet)
		{
			this.ongletPers.setLayout(new BorderLayout());
			this.ongletPers.add(titreOnglet);
			addComponentToPane(ongletPers);
			// itemStateChanged(event);
			// this.ongletPers.add(panelDroite,BorderLayout.EAST);
		}

		public void addComponentToPane(Container pane)
		{
			// Put the JComboBox in a JPanel to get a nicer look.
			JPanel ActionPersonne = new JPanel(); // use FlowLayout
			ActionPersonne.setBackground(new Color(51, 102, 153));
			String optionAjouter = "Ajouter une Compétition";
			String optionModifier = "Modifier une Compétitio";

			String comboBoxItems[] =
			{ optionAjouter, optionModifier };
			JComboBox cb = new JComboBox(comboBoxItems);
			cb.setEditable(false);
			cb.addItemListener(this);
			ActionPersonne.add(cb);

			// TODO : ici, il serait intéréssant d'alleger la méthode en
			// mettant dans une autre méthode la creations des cards!

			// Create the "cards".
			JPanel card1 = new JPanel();

			card1.setBackground(new Color(51, 153, 204));
			card1.setLayout(null);
			JLabel lblSolo = new JLabel("Solo  :");
			lblSolo.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblSolo.setBounds(100, 75, 100, 14);
			card1.add(lblSolo);
			JLabel lblPrnom = new JLabel("Nom :");
			lblPrnom.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblPrnom.setBounds(100, 16, 100, 14);
			card1.add(lblPrnom);
			JTextField champ_prenom = new JTextField(20);
			champ_prenom.setBackground(new Color(51, 102, 153));
			lblSolo.setLabelFor(champ_prenom);
			champ_prenom.setBounds(242, 13, 166, 20);
			card1.add(champ_prenom);

			JPanel card2 = new JPanel();
			card2.setBackground(new Color(51, 153, 204));

			JPanel card3 = new JPanel();
			card3.add(new JTextField("TextField", 20));
			card3.add(new JButton("Supprimer"));

			// Create the panel that contains the "cards".
			
			JCheckBox checkBox = new JCheckBox("");
			checkBox.setBackground(new Color(51, 153, 204));
			checkBox.setBounds(226, 75, 113, 25);
			card1.add(checkBox);
			
			JLabel lblEquipe = new JLabel("Equipe :");
			lblEquipe.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblEquipe.setBounds(100, 132, 65, 16);
			card1.add(lblEquipe);
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("");
			chckbxNewCheckBox.setBackground(new Color(51, 153, 204));
			chckbxNewCheckBox.setBounds(226, 132, 113, 25);
			card1.add(chckbxNewCheckBox);
		

			JLabel lblDate_1 = new JLabel("Date : ");
			lblDate_1.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblDate_1.setBounds(370, 223, 56, 16);
			card2.add(lblDate_1);
			
			JButton btnDate = new JButton("");
			btnDate.setBounds(370, 258, 181, 21);
			card2.add(btnDate);
			
			 btnDate.addActionListener(new ActionListener() 
		      { 
		               public void actionPerformed(ActionEvent e) 
		               {
		            	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		       				btnDate.addActionListener(this);
		       				card2.add(btnDate, BorderLayout.WEST);
		       				setLocationRelativeTo(null);
		       			SwingUtilities.invokeLater(new Runnable() {
		    				@Override
		    				public void run() {
		    					/* afficher le calendrier */
		    					JCalendar c = new JCalendar(); // calendrier (JPanel)
		    	 
		    					JDialog d = new JDialog(); // fenêtre
		    					d.setTitle("Choisir une date");
		    					d.setModalityType(ModalityType.APPLICATION_MODAL);
		    					d.getContentPane().add(c);
		    					d.pack();
		    					d.setLocationRelativeTo(IhmCompetition.this);
		    					d.setVisible(true);
		    	 
		    					Date date = c.getCalendar().getTime(); // on récupère la date
		    					
		    					/* on affiche la date dans le label */
		    					Locale locale = Locale.getDefault();
		    					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    					btnDate.setText(dateFormat.format(date));
		    					}
		    			});
		               } 
		       });
			 
			    cards = new JPanel(new CardLayout());
				cards.add(card1, optionAjouter);
				JButton btn = new JButton("Ajouter");
				btn.setBounds(242, 353, 120, 23);
				card1.add(btn);			
			
			JLabel lblListeDesPersonnes = new JLabel("Liste des competitions enregistr\u00E9es :");
			lblListeDesPersonnes.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblListeDesPersonnes.setBounds(12, 43, 288, 16);
			card2.add(lblListeDesPersonnes);

			JButton btnModifier = new JButton("Modifier");
			btnModifier.setEnabled(false);
			btnModifier.setBounds(230, 169, 89, 37);
			card2.add(btnModifier);

			JButton btnValiderLaModification = new JButton("Valider la modification");
			btnValiderLaModification.setBounds(250, 351, 160, 23);
			card2.add(btnValiderLaModification);
			
			JList list = new JList();
			list.setBounds(12, 72, 192, 256);
			card2.add(list);
			
			JLabel lblSolo_1 = new JLabel("Solo :");
			lblSolo_1.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblSolo_1.setBounds(370, 126, 40, 23);
			card2.add(lblSolo_1);
			
			JLabel lblEquipe_1 = new JLabel("Equipe :");
			lblEquipe_1.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblEquipe_1.setBounds(368, 170, 70, 34);
			card2.add(lblEquipe_1);
			check1.setBounds(433, 128, 30, 16);
			check1.setBackground(new Color(51, 153, 204));
			card2.add(check1);
			check.setBounds(433, 177, 30, 16);
			check.setBackground(new Color(51, 153, 204));
			card2.add(check);
			
		

			SortedSet competitions = inscription.getCompetitions();
			System.out.println(competitions);
			// TODO crer votre liste model vous-même
			DefaultListModel model = new DefaultListModel<>();
			JList<Competition> list_1 = new JList<>(model);
			for (Object comp : competitions)
			{
				model.addElement(comp);
			}
			list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list_1.setBackground(new Color(51, 102, 153));
			list_1.setBounds(37, 80, 190, 256);
			list_1.setCellRenderer(getCellRenderer());
			// =====================DECOMMENTER ET REMPLACER LA LISTE D
			// AfFICHAGE FACTICE====
			card2.add(list_1);
			// ==============================================================================
			pane.add(ActionPersonne, BorderLayout.PAGE_START);
			pane.add(cards, BorderLayout.CENTER);

			// LISTE DE TEST
			DefaultListModel model_test = new DefaultListModel<>();
			JList<Competition> list_test = new JList<>(model_test);
			list_test.setBackground(new Color(51, 102, 153));
			list_test.setBounds(37, 80, 190, 256);

			// card2.add(list_test);

			list_1.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e)
				{
					JList source = (JList) e.getSource();
					Object selection = source.getSelectedValue();
					
					Class unknown = selection.getClass();
					System.out.println(unknown);
					
					int id =((Competition) selection).getId();
					
					String nom = ((Competition) selection).getNom();
					LocalDate Date =((Competition) selection).getDateCloture();
					Boolean Equipe =((Competition) selection).getEnEquipe();
					
					setChampNom(nom);
					setChampDate(Date);
					setChampEquipe(Equipe);
					System.out.println(selection);
					System.out.println(id);
					System.out.println(Equipe);
					System.out.println(Date);
					System.out.println(nom);
					
					desactiverChamp(lblPrnom, modifNomComp);
					desactiverChamp(lblDate_1, modifDateComp);
					desactiverChamp(lblEquipe, modifEnEquipe);
					
					btnModifier.setEnabled(true);
					btnModifier.addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e)
						{
							activerChamp(lblPrnom, modifNomComp, getChampNom());
						}
					});
				}
			});		
			
			btnValiderLaModification.addActionListener(new ActionListener() {						
				@Override
				public void actionPerformed(ActionEvent e)
				{
					inscription.createCompetition(getChampNom(), getChampDate(), getChampEquipe());
				}
			});
			
		}
		private int getIdCompetition(){
			return id_competition_modif;
		}
		
		/**
		 * 
		 * @return String nom_competition_modif
		 */
		private String getChampNom(){
			return nom_competition_modif;
		}
		
		/**
		 * 
		 * @return LocalDate date_competition_modif
		 */
		private LocalDate getChampDate(){
			return date_competition_modif;
		}
		
		/**
		 * 
		 * @return String nom_competition_modif
		 */
		private Boolean getChampEquipe(){
			return equipe_competition_modif;
		}
		
		/**
		 * 
		 * @param Integer id_comp
		 */
		private void getIdCompetition(Integer id){
			id_competition_modif = id;
		}
		
		/**
		 * 
		 * @param String name_comp
		 */
		private void setChampNom(String name){
			nom_competition_modif = name;
		}
		
		/**
		 * 
		 * @param LocalDate date
		 */
		private void setChampDate(LocalDate date){
			date_competition_modif = date;
		}
		
		/**
		 * 
		 * @param Boolean equipe
		 */
		private void setChampEquipe(Boolean equipe){
			equipe_competition_modif = equipe ;
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

		private ListCellRenderer<? super Competition> getCellRenderer()
		{
			return new DefaultListCellRenderer() {
				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus)
				{
					Competition comp = (Competition) value;
					Component listCellRendererComponent = super.getListCellRendererComponent(list,
							comp.getId()+"/"+ comp.getNom() + "/" + comp.getDateCloture() + "/" + comp.getEnEquipe(), index, isSelected, cellHasFocus);
					return listCellRendererComponent;
				}
			};
		}

		public void itemStateChanged(ItemEvent evt)
		{
			CardLayout cl = (CardLayout) (cards.getLayout());
			cl.show(cards, (String) evt.getItem());
		}
		
		public void actionPerformed(ActionEvent e) {
			
		}
	

		public JPanel getOnglet()
		{
			return this.ongletPers;
		}
	}
