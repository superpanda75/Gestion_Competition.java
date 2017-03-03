package ihmSwing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;


public class IhmPersonne extends JFrame
	{	
		private JPanel ongletPers = new JPanel();
	
		public IhmPersonne(JLabel titreOnglet)
		{
			this.ongletPers.add(titreOnglet);
	
		}
		
		public JPanel getOnglet(){
			return this.ongletPers;
		}
	
		
			
	}

	
