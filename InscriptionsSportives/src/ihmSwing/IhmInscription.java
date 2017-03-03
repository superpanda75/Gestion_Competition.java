package ihmSwing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;

public class IhmInscription
	{
		private JPanel ongletInsc = new JPanel();
		
		public IhmInscription(JLabel titreOnglet)
		{
			this.ongletInsc.add(titreOnglet);
	
		}
		
		public JPanel getOnglet(){
			return this.ongletInsc;
		}
	}
