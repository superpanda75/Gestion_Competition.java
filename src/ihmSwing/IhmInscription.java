package ihmSwing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;



public class IhmInscription implements ItemListener
	{
		
		
		
	
		
		private final Inscriptions inscriptions ; 
		private Map<Integer, Candidat> mapCandidat;
		private Map<Integer, Competition> mapCompetition;

		public IhmInscription(Inscriptions inscriptions)
		{
			this.inscriptions = inscriptions;
		}
		
		private void associateLabels(){
			
		}
		
		private JLabel mkLbListPers(){
			JLabel lblListeDesPersonnes = new JLabel("Liste des personnes enregistr\u00E9es :");
			lblListeDesPersonnes.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			lblListeDesPersonnes.setBounds(37, 43, 249, 16);
		return lblListeDesPersonnes;
		}
		
		

			
		public JPanel getCardModif(){
			
			JPanel cardPersonnes = new JPanel();
			cardPersonnes.setBackground(new Color(51, 153, 204));
			cardPersonnes.setLayout(null);
			cardPersonnes.add(mkLbListPers());
			
				
			
			JButton btnValiderLaModification = new JButton("Inscrire");
			btnValiderLaModification.setBounds(260, 200, 80, 23);
		
			cardPersonnes.add(btnValiderLaModification);
			
			cardPersonnes.add(getListPersonne());
			
			cardPersonnes.add(getListInscri());

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
					return mapCandidat.size() + 1;
				}
				
				@Override
				public String getElementAt(int index)
				{
					if (index < mapCandidat.size())
						return mapCandidat.get(index).getNom();
					else
						return null;
				}
				
				@Override
				public void addListDataListener(ListDataListener l)
				{
					// TODO Auto-generated method stub
					
				}
			};
		}
		private ListModel<String> getListInscriModel()
		{
			rafraichirMapCompetition();
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
						return null;
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
			mapCandidat = new TreeMap<>();
			int i = 0;
			for (Candidat c : inscriptions.getCandidats())
				mapCandidat.put(i++, c);
		}
		
		private void rafraichirMapCompetition()
		{
			mapCompetition = new TreeMap<>();
			int i = 0;
			for (Competition co : inscriptions.getCompetitions())
				mapCompetition.put(i++, co);
		}
		
		private JList<String> getListPersonne(){
			JList<String> listePersonnes = new JList<>(getListPersonneModel());
			listePersonnes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listePersonnes.setBackground(new Color(51, 102, 153));
			listePersonnes.setBounds(37, 80, 190, 256);
				
			return listePersonnes;
			}
		private JList<String> getListInscri(){
			JList<String> listeInscri = new JList<String>(getListInscriModel());
			listeInscri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listeInscri.setBackground(new Color(51, 102, 153));
			listeInscri.setBounds(370, 80, 190, 256);
			
			return listeInscri;
			}
		
		
		
		

		
		
		public JPanel getOnglet()
		{
			JPanel ongletInscri = new JPanel();
			associateLabels();
			ongletInscri.setLayout(new BorderLayout());
			ongletInscri.setName("Inscription");
			ongletInscri.add(getCardModif());
			return ongletInscri;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
	
		
		
		
	}
