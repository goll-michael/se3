package client.gui;


import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*; 

import client.application.AnzeigenverwaltungDelegate;

/**
 * Hauptfenster der Anwendung
 */
public class AnzeigeKundeGUI extends JFrame
{
	/**
	 * Beim Serialisieren eines Objektes wird auch die serialVersionUID der zugehoerigen
	 * Klasse mit in die Ausgabedatei geschrieben. Soll das Objekt spaeter deserialisiert
	 * werden, so wird die in der Datei gespeicherte serialVersionUID mit der aktuellen
	 * serialVersionUID des geladenen .class-Files verglichen. Stimmen beide nicht
	 * ueberein, so gibt es eine Ausnahme des Typs InvalidClassException, und der
	 * Deserialisierungsvorgang bricht ab.
	 */
	private static final long serialVersionUID = 1L;
	
	private AnzeigenverwaltungDelegate anzeigenverwaltung = AnzeigenverwaltungDelegate.getInstance();
	
	private enum Flaeche {LEER_FLAECHE, NEUKUNDE_FLAECHE, 
		NEUANZEIGE_FLAECHE, LISTEANZEIGE_FLAECHE,LISTEKUNDE_FLAECHE};
		
	//Hintergrundsflaeche
	private JPanel hauptFlaeche;
    private JPanel linkeFlaeche;
    private FensterAbhoerer einFensterAbhoerer = new FensterAbhoerer();

    private JButton neuKundeDruckknopfJ     = new JButton();
    private JButton listeKundeDruckknopfJ   = new JButton();
    private JButton neuAnzeigeDruckknopfJ   = new JButton();
    private JButton listeAnzeigeDruckknopfJ = new JButton();
    private JButton beendenDruckknopfJ      = new JButton();
    
    private UnterfensterKunde kundenFenster;
    private UnterfensterAnzeige anzeigenFenster;
    
	//Konstruktor
	public AnzeigeKundeGUI()
	{
	    super("Anzeigen- und Inserentenverwaltung"); //Aufruf des Konstruktors von JFrame
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
	    kundenFenster = new UnterfensterKunde(null);
	    kundenFenster.setHauptfenster(this);
	    anzeigenFenster = new UnterfensterAnzeige(null);
	    anzeigenFenster.setHauptfenster(this);
	    hauptFlaeche.add(kundenFenster, Flaeche.NEUKUNDE_FLAECHE.toString());
	    hauptFlaeche.add(anzeigenFenster, Flaeche.NEUANZEIGE_FLAECHE.toString());  
	    hauptFlaeche.add(new ListenfensterAnzeige(anzeigenFenster), Flaeche.LISTEANZEIGE_FLAECHE.toString());
	    hauptFlaeche.add(new ListenfensterKunde(kundenFenster), Flaeche.LISTEKUNDE_FLAECHE.toString());
	    	    
	    add(BorderLayout.CENTER, hauptFlaeche);
	    
	    // linke flaeche
	    linkeFlaeche = new JPanel();
	    linkeFlaeche.setLayout(null);
	    linkeFlaeche.setPreferredSize(new Dimension(160, 400));
	    int breite = 120, hoehe = 26, xBegin = 20, yBegin = 5, yDiff = 31;
	    neuKundeDruckknopfJ.    setBounds(xBegin, yBegin + 0 * yDiff, breite, hoehe);
	    neuKundeDruckknopfJ.    setAction(new NeuerKundeAktion());
	    linkeFlaeche.add(neuKundeDruckknopfJ);
	    neuAnzeigeDruckknopfJ.  setBounds(xBegin, yBegin + 1 * yDiff, breite, hoehe);
	    neuAnzeigeDruckknopfJ.  setAction(new NeueAnzeigeAktion());
	    linkeFlaeche.add(neuAnzeigeDruckknopfJ);
	    listeKundeDruckknopfJ.  setBounds(xBegin, yBegin + 2 * yDiff, breite, hoehe);
	    listeKundeDruckknopfJ.  setAction(new KundenlisteAktion());
	    linkeFlaeche.add(listeKundeDruckknopfJ);
	    listeAnzeigeDruckknopfJ.setBounds(xBegin, yBegin + 3 * yDiff, breite, hoehe);
	    listeAnzeigeDruckknopfJ.setAction(new AnzeigenlisteAktion());
	    linkeFlaeche.add(listeAnzeigeDruckknopfJ);    
	    beendenDruckknopfJ.     setBounds(xBegin, yBegin + 4 * yDiff, breite, hoehe);
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
		
		setSize(610, 795);
		
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
	    int ErgebnisEndeDialog = JOptionPane.showConfirmDialog(this, 
	    		"Soll die Anwendung wirklich beendet werden?", "Frage", JOptionPane.YES_NO_OPTION);
	     //Wenn Ja gewaehlt, Anwendung beenden
	     if(ErgebnisEndeDialog == JOptionPane.YES_OPTION)
	     {
	    	 this.setVisible(false); //Unsichtbar machen des aufrufenden Fensters

	    	 try
	    	 {
	    		 anzeigenverwaltung.endeAnwendung();
	    	 }
	    	 catch(Exception e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	    	 
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
    
	@SuppressWarnings("serial")
	private class NeueAnzeigeAktion extends AbstractAction 
	{
		{
			putValue(Action.NAME, "Neue Anzeige...");
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			aendereAnzeige(Flaeche.NEUANZEIGE_FLAECHE);
			anzeigenFenster.zeigen(null);
	    }
	}
	
	@SuppressWarnings("serial")
	private class NeuerKundeAktion extends AbstractAction 
	{
		{
			putValue(Action.NAME, "Neuer Kunde...");
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			aendereAnzeige(Flaeche.NEUKUNDE_FLAECHE);
			kundenFenster.zeigen(null);
		}
	}
		
	@SuppressWarnings("serial")
	private class AnzeigenlisteAktion extends AbstractAction 
	{
		{
			putValue(Action.NAME, "Alle Anzeigen...");
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			aendereAnzeige(Flaeche.LISTEANZEIGE_FLAECHE);
		}
	}
	
	@SuppressWarnings("serial")
	private class KundenlisteAktion extends AbstractAction 
	{
		{
			putValue(Action.NAME, "Alle Kunden...");
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			aendereAnzeige(Flaeche.LISTEKUNDE_FLAECHE);
		}
	}
	
	public void zeigeKundenliste()
	{
		aendereAnzeige(Flaeche.LISTEKUNDE_FLAECHE);
	}
	
	public void zeigeAnzeigeListe()
	{
		aendereAnzeige(Flaeche.LISTEANZEIGE_FLAECHE);		
	}
	
	@SuppressWarnings("serial")
	private class BeendenAktion extends AbstractAction 
    {
		{
			putValue(Action.NAME, "Beenden");
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			anzeigenEndeDialog();
		}
    }  
        
    //FensterAbhoerer **********************************************************************
	 private class FensterAbhoerer extends WindowAdapter
     {
        //Ereignis Schliessen des Fensters
        public void windowClosing(WindowEvent event)
	    {
	        anzeigenEndeDialog();
	    }
     }
}