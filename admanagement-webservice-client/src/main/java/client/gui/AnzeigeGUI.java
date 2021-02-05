package client.gui;


import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*; 

/**
 * Hauptfenster der Anwendung
 */
public class AnzeigeGUI extends JFrame
{
	private static final long serialVersionUID = 1L;

	private enum Flaeche {LEER_FLAECHE, NEUANZEIGE_FLAECHE};
		
	//Hintergrundsflaeche
	private JPanel hauptFlaeche;
    private JPanel linkeFlaeche;
    private FensterAbhoerer einFensterAbhoerer = new FensterAbhoerer();

    private JButton neuAnzeigeDruckknopfJ = new JButton();
    private JButton beendenDruckknopfJ = new JButton();
    private UnterfensterAnzeige anzeigenFenster;
    
	//Konstruktor
	public AnzeigeGUI()
	{
	    super("AnzeigenWS-Client"); //Aufruf des Konstruktors von JFrame
	    initGUI();
	 }
	
	private void initGUI() 
	{
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//Cursor-Form setzen
	    setFont(new Font("Dialog", 28, Font.PLAIN));
	    
	    //Flaeche einfuegen und Hintergrund der Flaeche auf blau setzen
	    setLayout(new BorderLayout());
	    
	    // hauptflaeche
	    hauptFlaeche = new JPanel(new CardLayout());
	    hauptFlaeche.add(new JPanel(), Flaeche.LEER_FLAECHE.toString());
	    anzeigenFenster = new UnterfensterAnzeige();
	    anzeigenFenster.setHauptfenster(this);
	    hauptFlaeche.add(anzeigenFenster, Flaeche.NEUANZEIGE_FLAECHE.toString());  
	    	    
	    add(BorderLayout.CENTER, hauptFlaeche);
	    
	    // linke flaeche
	    linkeFlaeche = new JPanel();
	    linkeFlaeche.setLayout(null);
	    linkeFlaeche.setPreferredSize(new Dimension(160, 400));
	    int breite = 120, hoehe = 26, xBegin = 20, yBegin = 5, yDiff = 31;
	    neuAnzeigeDruckknopfJ.  setBounds(xBegin, yBegin, breite, hoehe);
	    neuAnzeigeDruckknopfJ.  setAction(new NeueAnzeigeAktion());
	    linkeFlaeche.add(neuAnzeigeDruckknopfJ);   
	    beendenDruckknopfJ.     setBounds(xBegin, yBegin + 1 * yDiff, breite, hoehe);
	    beendenDruckknopfJ.     setAction(new BeendenAktion());
	    linkeFlaeche.add(beendenDruckknopfJ);
	    add(BorderLayout.WEST, linkeFlaeche);
	   
	    //Abhoerer hinzufuegen
	    einFensterAbhoerer = new FensterAbhoerer();
	    addWindowListener(einFensterAbhoerer);
	    
		//Linke obere Ecke des Fensters auf dem Bildschirm festlegen
		setLocation(50,50);
		
		//Piktogramm fuer Anwendungsmenueknopf zuordnen
		setzePiktogramm(getClass().getResource("resources/banana.gif"), this);
		
		setSize(610, 495);
		setResizable(false);

		//Anzeigen des Fensters
		setVisible(true);
	}

	 // Setzt das Piktogramm im Anwendungsmenueknopf (links oben im Fenster)
	 private void setzePiktogramm(URL Dateiname, Frame einFenster) 
	 {
			Image einPiktogramm = getToolkit().getImage(Dateiname);
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(einPiktogramm, 0);
			try 
			{
				// Warten bis das Piktogramm geladen ist
				mt.waitForAll();
			} catch (InterruptedException e) {
			// nichts tun
		}
		einFenster.setIconImage(einPiktogramm);
	 }
	 
	 private void anzeigenEndeDialog()
	 {
	    //Anzeigen eines Bestaetigungsdialogs 
	    int ErgebnisEndeDialog =
	    	JOptionPane.showConfirmDialog(
    			this, 
	    		"Soll die Anwendung wirklich beendet werden?",
	    		"Frage",
	    		JOptionPane.YES_NO_OPTION
    		);
	    
	     //Wenn Ja gewaehlt, Anwendung beenden
	     if(ErgebnisEndeDialog == JOptionPane.YES_OPTION)
	     {
	    	 this.setVisible(false); //Unsichtbar machen des aufrufenden Fensters
	         this.dispose(); //Freigabe der Systemressourcen
		     System.exit(0);//Schliessen der Anwendung
		 }
		 else if ((ErgebnisEndeDialog == JOptionPane.CLOSED_OPTION)||
		                (ErgebnisEndeDialog == JOptionPane.NO_OPTION))
		 {
		     //Die Operation sorgt dafuer, dass das Schliessen des Mitteilungsfensters
		     //keine Auswirkungen auf das Anwendungsfenster hat
			 this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 }
	 } 

    public void aendereAnzeige(Flaeche flaeche)
    {
    	CardLayout cl = (CardLayout)(hauptFlaeche.getLayout());
        cl.show(hauptFlaeche, flaeche.toString());
    }
    
	private class NeueAnzeigeAktion extends AbstractAction 
	{
		private static final long serialVersionUID = 1L;
		{
			putValue(Action.NAME, "Neue Anzeige...");
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			aendereAnzeige(Flaeche.NEUANZEIGE_FLAECHE);
			anzeigenFenster.zeigen();
	    }
	}
	
	private class BeendenAktion extends AbstractAction 
    {
		private static final long serialVersionUID = 1L;
		{
			putValue(Action.NAME, "Beenden");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			anzeigenEndeDialog();
		}
    }  
        
    //FensterAbhoerer
	 private class FensterAbhoerer extends WindowAdapter
     {
        //Ereignis Schliessen des Fensters
        public void windowClosing(WindowEvent event)
	    {
	        anzeigenEndeDialog();
	    }
     }
}