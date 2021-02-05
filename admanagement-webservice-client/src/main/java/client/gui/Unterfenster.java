package client.gui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Oberklasse für die beiden Unterfenster Anzeigenfenster und Kundenfenster
 */
public abstract class Unterfenster extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	//Attribute
	private ArrayList<JTextField> textfelder = new ArrayList<JTextField>();
	private ArrayList<JComponent> pflichtFelder = new ArrayList<JComponent>();
	private Color pflichtFaerbung = new java.awt.Color(255, 255, 159); //gelb
	private AnzeigeGUI hauptfenster;
	
	protected Unterfenster()
	{
		setLayout(null);
	    setFont(new Font("Dialog", Font.PLAIN, 10));
	}
	
	protected JTextField neuesTextfeld() 
	{
		JTextField textfeld = new JTextField();
		textfelder.add(textfeld);
		return textfeld;
	}
	
	protected void resetTextFelder() 
	{
		for(JTextField textfeld: textfelder) 
			textfeld.setText("");
    }
	
	protected void macheZuPflichtfeld(JComponent comp)
	{
		comp.setBackground(pflichtFaerbung);
		pflichtFelder.add(comp);
	}
	
	protected void zeigePflichtfeldFehlerMitteilung()
	{
		JOptionPane.showMessageDialog(
			this,
			"Es sind Pflichtfelder nicht ausgefuellt! Pflichtfelder sind durch gelbe Farbe hinterlegt.",
			"Mitteilung",
			JOptionPane.OK_OPTION
		);
	}
	
	protected boolean pflichtTextfelderAusgefuellt()
	{
		for(JComponent comp : pflichtFelder) 
		{
			if(comp instanceof JTextField) 
			{
				JTextField textFeld = (JTextField) comp;
				if(textFeld.getText().isEmpty())
					return false;
			}
		}
		return true;
	}

	protected AnzeigeGUI getHauptfenster()
	{
		return hauptfenster;
	}

	protected void setHauptfenster(AnzeigeGUI hauptfenster)
	{
		this.hauptfenster = hauptfenster;
	}	
}
	 