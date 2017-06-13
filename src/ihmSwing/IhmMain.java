package ihmSwing;

import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import inscriptions.Inscriptions;


public class IhmMain 
{
	private IhmPersonne ongletPersonne;	
	private IhmEquipe ongletEquipe;	
	private IhmCompetition ongletCompetition;	
	private IhmInscription ongletInscription;	

	public IhmMain(Inscriptions inscriptions)
	{
		JFrame f = new JFrame("Gestion des Inscriptions");
		f.setSize(600, 500);

		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);

		JPanel ongletPers = (new IhmPersonne(inscriptions)).getOnglet();	

		JPanel ongletEqui = (new IhmEquipe(inscriptions)).getOnglet();				

		JPanel ongletComp = (new IhmCompetition(inscriptions)).getOnglet();	

		JPanel ongletInsc = (new IhmInscription(inscriptions)).getOnglet();


		onglets.addTab("Competition", ongletComp);
		onglets.addTab("Personne", ongletPers);
		onglets.addTab("Equipe", ongletEqui);
		onglets.addTab("Inscription", ongletInsc);

		onglets.setOpaque(true);		
		f.add(onglets);		
		f.getContentPane().add(onglets);		
		f.setVisible(true);
		f.setResizable(false);
	}

	public static void main(String[] args) {
		new IhmMain(Inscriptions.getInscriptions());

	}


	/**
	 * Cette fonction permet d'activer un champ : activerChamp(libelle, JTextField)
	 * @param lbl
	 * @param tf
	 */
	public static void activerChamp(JLabel lbl, JTextField tf){
		tf.setEditable(true);
		tf.setEnabled(true);
		tf.setBackground(new Color(51, 102, 153));
		lbl.setForeground(new Color(0,0,0));	     
	}

	/**
	 * Cette fonction permet de désactiver un champ en le grisant et en le désactivant : desactiverChamp(libelle, JTextField)
	 * @param lbl
	 * @param tf
	 */
	public static void desactiverChamp(JLabel lbl, JTextField tf){
		tf.setEditable(false);
		tf.setEnabled(false);
		tf.setBackground(new Color(102, 102, 102));
		lbl.setForeground(new Color(102, 102, 102));	     
	}

}
