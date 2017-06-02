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
		private JTabbedPane	menuPersonne	= new JTabbedPane(SwingConstants.RIGHT);
		private JPanel		panelDroite		= new JPanel();
		private final JCheckBox check1 = new JCheckBox("");
		private final JCheckBox check = new JCheckBox("");
		private final JButton btnDate = new JButton("Choisissez la date");

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
				
				JButton btnAjouter = new JButton("Ajouter");
				btnAjouter.setBounds(202, 236, 89, 23);
				card1.add(btnAjouter);
				cards.add(card2, optionModifier);
				card2.setLayout(null);

			
			
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
			
		

			Inscriptions ins = inscriptions.Inscriptions.getInscriptions();
			SortedSet personnes = ins.getPersonnes();
			System.out.println(personnes);
			DefaultListModel model = new DefaultListModel<>();
			JList<Personne> list_1 = new JList<>(model);
			for (Object personne : personnes)
			{
				model.addElement(personne);
			}
			list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list_1.setBackground(new Color(51, 102, 153));
			list_1.setBounds(37, 80, 190, 256);
			list_1.setCellRenderer(getCellRenderer());
			// =====================DECOMMENTER ET REMPLACER LA LISTE D
			// AfFICHAGE FACTICE====
			// ==============================================================================
			pane.add(ActionPersonne, BorderLayout.PAGE_START);
			pane.add(cards, BorderLayout.CENTER);

			
		}

		private ListCellRenderer<? super Personne> getCellRenderer()
		{
			return new DefaultListCellRenderer() {
				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus)
				{
					Personne p = (Personne) value;
					Component listCellRendererComponent = super.getListCellRendererComponent(list,
							p.getId()+"/"+ p.getNom() + "/" + p.getPrenom() + "/" + p.getMail(), index, isSelected, cellHasFocus);
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
