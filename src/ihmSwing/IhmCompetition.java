package ihmSwing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;

public class IhmCompetition extends JFrame
	{
		private JPanel ongletComp = new JPanel();
		
		public IhmCompetition(JLabel titreOnglet)
		{
			this.ongletComp.add(titreOnglet);
	
		}
		
		public JPanel getOnglet(){
			return this.ongletComp;
		}
	}
