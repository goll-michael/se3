package de.se3.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

import de.se3.security.ac.swing.AcJTextField;

public class Unterfenster extends JDialog implements Serializable
{
	//Attribute
	static private int xLinksOben = 60;//Position xLinksOben des ersten Unterfensters
	static private int yLinksOben = 95;//Position yLinksOben des ersten Unterfensters
	private FensterAbhoerer einFensterAbhoerer;
	private ArrayList<AcJTextField> textfelder = new ArrayList<AcJTextField>();
	
	public Unterfenster(JFrame dasAnwendungsfenster, String Fenstertitel)
	{
	    //Unterfenster neu erstellen
	    super(dasAnwendungsfenster, Fenstertitel);
	    //Die folgenden Angaben beziehen sich auf das Unterfenster
	    //setSize(400,300);//Groesse einstellen
		setLocation(xLinksOben, yLinksOben);//Linke obere Ecke des Fensters festlegen
		xLinksOben = xLinksOben + 25;
		yLinksOben = yLinksOben + 25;
		//setVisible(true);
		//Die folgenden Angaben beziehen sich auf die ContentPane!
		getContentPane().setLayout(null);
		getContentPane().setForeground(Color.black);//Vordergrundfarbe 
	    getContentPane().setBackground(Color.white);//Hintergrundfarbe 
	    getContentPane().setFont(new Font("Dialog", Font.PLAIN, 10));
	    
	    einFensterAbhoerer = new FensterAbhoerer();
	    addWindowListener(einFensterAbhoerer);
	    

	    setResizable(false);
	}
	
	//Innere Klasse
	public class FensterAbhoerer extends WindowAdapter implements Serializable
    {
        //Ereignis Schliessen des Fensters
        public void windowClosing(WindowEvent event)
	    {
	        setVisible(false); //Unsichtbar machen des aufrufenden Fensters
	        dispose(); //Freigabe der Systemressourcen
        }
    }
	
	protected AcJTextField neuesTextfeld() 
	{
		AcJTextField textfeld = new AcJTextField("Unterfenster-textfeld");
		
		textfelder.add(textfeld);
		
		return textfeld;
	}
	
	protected void resetTextFelder() 
	{
		for(AcJTextField textfeld: textfelder) 
			textfeld.setText("");
     }
}
	 