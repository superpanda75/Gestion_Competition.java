package ihmSwing;

import inscriptions.Inscriptions;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.*;

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
import java.awt.event.ActionEvent;

public class IhmEquipe implements ItemListener
	{
		JPanel				cards;
		private JPanel		ongletEquipe		= new JPanel();
		private JTabbedPane	menuEquipe	= new JTabbedPane(SwingConstants.RIGHT);
		private JPanel		panelDroite		= new JPanel();
		private JTextField	modifNomCand;

		public IhmEquipe(JLabel titreOnglet)
		{
			this.ongletEquipe.setLayout(new BorderLayout());
			this.ongletEquipe.add(titreOnglet);
			addComponentToPane(ongletEquipe);
			// itemStateChanged(event);
			// this.ongletPers.add(panelDroite,BorderLayout.EAST);

		}

		public void addComponentToPane(Container pane)
		{
			// Put the JComboBox in a JPanel to get a nicer look.
			JPanel ActionEquipe = new JPanel(); // use FlowLayout
			ActionEquipe.setBackground(new Color(51, 102, 153));
			String optionAjouter = "Nouvelle Equipe";
			String optionModifier = "Modifier une Equipe";

			String comboBoxItems[] =
			{ optionAjouter, optionModifier };
			JComboBox cb = new JComboBox(comboBoxItems);
			cb.setEditable(false);
			cb.addItemListener(this);
			ActionEquipe.add(cb);

			// TODO : ici, il serait intéréssant d'alleger la méthode en
			// mettant dans une autre méthode la creations des cards!

			// Create the "cards".
			JPanel listMembre = new JPanel();

			listMembre.setBackground(new Color(51, 153, 204));
			listMembre.setLayout(null);
			JLabel lblNom = new JLabel("Nom :");
			lblNom.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblNom.setBounds(161, 32, 53, 18);
			listMembre.add(lblNom);
			JTextField champ_nom = new JTextField(20);
			champ_nom.setBackground(new Color(51, 102, 153));
			champ_nom.setBounds(264, 32, 120, 20);
			listMembre.add(champ_nom);

			JPanel card2 = new JPanel();
			card2.setBackground(new Color(51, 153, 204));

			JPanel card3 = new JPanel();
			card3.add(new JTextField("TextField", 20));
			card3.add(new JButton("Supprimer"));

			// Create the panel that contains the "cards".
			cards = new JPanel(new CardLayout());
			cards.add(listMembre, optionAjouter);
			JButton button = new JButton("Ajouter");
			button.setBounds(264, 353, 120, 23);
			listMembre.add(button);
			cards.add(card2, optionModifier);
			card2.setLayout(null);

			JLabel lblListeDesPersonnes = new JLabel("Liste des candidats enregistr\u00E9es :");
			lblListeDesPersonnes.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblListeDesPersonnes.setBounds(37, 100, 249, 16);
			listMembre.add(lblListeDesPersonnes);
			
			JList list = new JList();
			list.setBounds(47, 127, 196, 185);
			listMembre.add(list);
			
			JButton btnListRight = new JButton("New Button");
			btnListRight.setEnabled(false);
			btnListRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnListRight.setBounds(264, 173, 120, 41);
			listMembre.add(btnListRight);
			
			JButton btnListLeft = new JButton("New Button");
			btnListLeft.setEnabled(false);
			btnListLeft.setBounds(264, 236, 120, 41);
			listMembre.add(btnListLeft);
			
			JList list_2 = new JList();
			list_2.setBounds(404, 127, 133, 185);
			listMembre.add(list_2);

			JButton btnRight = new JButton("New Button");
			btnRight.setEnabled(false);
			btnRight.setBounds(250, 195, 109, 39);
			card2.add(btnRight);

			JLabel lbl_nom_cand = new JLabel("Nom de candidat :");
			lbl_nom_cand.setForeground(new Color(102, 102, 102));
			lbl_nom_cand.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lbl_nom_cand.setBounds(77, 34, 141, 14);
			card2.add(lbl_nom_cand);

			modifNomCand = new JTextField();
			modifNomCand.setBackground(new Color(102, 102, 102));
			modifNomCand.setEditable(false);
			modifNomCand.setEnabled(false);
			lbl_nom_cand.setLabelFor(modifNomCand);
			modifNomCand.setBounds(253, 32, 106, 20);
			card2.add(modifNomCand);
			modifNomCand.setColumns(10);

			JButton btnValiderLaModification = new JButton("Valider ");
			btnValiderLaModification.setBounds(248, 300, 109, 23);
			card2.add(btnValiderLaModification);

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
			list_1.setBounds(37, 83, 181, 253);
			list_1.setCellRenderer(getCellRenderer());
			// =====================DECOMMENTER ET REMPLACER LA LISTE D
			// AfFICHAGE FACTICE====
			card2.add(list_1);
			
			JButton btnLeft = new JButton("New button");
			btnLeft.setEnabled(false);
			btnLeft.setBounds(250, 122, 109, 39);
			card2.add(btnLeft);
			
			JList list_3 = new JList();
			list_3.setBackground(new Color(0, 102, 153));
			list_3.setEnabled(false);
			list_3.setBounds(392, 83, 141, 253);
			card2.add(list_3);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Choisir une equipe"}));
			comboBox.setMaximumRowCount(999);
			comboBox.setBounds(224, 62, 135, 20);
			card2.add(comboBox);
			// ==============================================================================
			pane.add(ActionEquipe, BorderLayout.PAGE_START);
			pane.add(cards, BorderLayout.CENTER);

			// LISTE DE TEST
			DefaultListModel model_test = new DefaultListModel<>();
			JList<Personne> list_test = new JList<>(model_test);
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
					
					int id =((Personne) selection).getId();
					String mail =((Personne) selection).getMail();
					String prenom =((Personne) selection).getPrenom();
					String Nom = ((Personne) selection).getNom();
					System.out.println(selection);
					System.out.println(id);
					System.out.println(mail);
					System.out.println(prenom);
					System.out.println(Nom);
					
				}
			});
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

		public JPanel getOnglet()
		{
			return this.ongletEquipe;
		}
	}
