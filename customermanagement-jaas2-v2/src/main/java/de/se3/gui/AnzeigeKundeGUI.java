/* Programmname: Artikel- und Lieferantenverwaltung
* GUI-Klasse: ArtikelLieferantenGUI
* Aufgabe: Verwaltung des Anwendungsfensters
*/
package de.se3.gui;


import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

import de.se3.applikation.Anzeigenverwaltung;
import de.se3.security.ac.swing.AcJMenu;
import de.se3.security.ac.swing.AcJMenuBar;
import de.se3.security.ac.swing.AcJPanel;
import de.se3.security.administration.PermissionAdministrator;



public class AnzeigeKundeGUI extends JFrame
{
    //Hintergrundsflaeche
	private AcJPanel eineFlaeche = new AcJPanel("AnzeigeKundeGUI-eineFlaeche");
	
	private MeinPopupMenu einPopup;
	private MenueBalken einMenueBalken;
    private FensterAbhoerer einFensterAbhoerer = new FensterAbhoerer();
    private MausAbhoerer einMausabhoerer = new MausAbhoerer();


	//Konstruktor
	public AnzeigeKundeGUI(String FensterTitel)
	{
	    super(FensterTitel); //Aufruf des Konstruktors von JFrame
        
	    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//Cursor-Form setzen
	    setFont(new Font("Dialog", 28, Font.PLAIN));
	    //Flaeche einfuegen und Hintergrund der Flaeche auf blau setzen
	    getContentPane().add(eineFlaeche);
	    eineFlaeche.setBackground(Color.blue);
	    //Abhoerer hinzufuegen
	    einFensterAbhoerer = new FensterAbhoerer();
	    addWindowListener(einFensterAbhoerer);
	    //Hinzufuegen des Menuebalkens, eigene Klasse
		einMenueBalken = new MenueBalken("AnzeigeKundeGUI-einMenueBalken");
		setJMenuBar(einMenueBalken);
	    //Pop-up-Menue erzeugen und aktivieren
	    einPopup = new MeinPopupMenu();
	    getContentPane().addMouseListener(einMausabhoerer);
	    //Aktivieren von Maus-Ereignissen des Anwendungsfensters
	    //Wird fuer das pop-up-Menue benoetigt
	    enableEvents(AWTEvent.MOUSE_EVENT_MASK);//Ereignis-Maske zur Auswahl von Maus-ereignissen
	 }

        /* Gibt es nicht mehr, weil durch SingleAccessPoint ersetzt
	//Klassenoperation
	public static void main(String[] args)
	{

		 SwingUtilities.invokeLater(new Runnable()
	     {
	            public void run()
	            {
					//Benutzungsoberflaeche aufbauen
					//Erzeugen des Anwendungsfensters
	            	AnzeigeKundeGUI dasAnwendungsfenster = new AnzeigeKundeGUI("Anzeigen- und Inserentenverwaltung");
	            	dasAnwendungsfenster.initGUI();
	            }
	        });
	}
	*/
	public void initGUI() 
	{
		//Groesse einstellen
		setSize(900,650);
		//Linke obere Ecke des Fensters auf dem Bildschirm festlegen
		setLocation(50,50);
		//Anzeigen des Fensters
		setVisible(true);
	}
	

	 public void processMouseEvent(MouseEvent event)
	 {
	    //Verarbeitet Maus-Ereignisse des Anwendungsfensters
	    if (event.isPopupTrigger())
	    {
	        einPopup.show(event.getComponent(), event.getX(), event.getY());
	    }
	    super.processMouseEvent(event);
	 }

	 // Setzt das Piktogramm im Anwendungsmenueknopf (links oben im Fenster)
	 private void setzePiktogramm(URL Dateiname, Frame einFenster) 
	 {
//			Image einPiktogramm = getToolkit().getImage(Dateiname);
			MediaTracker mt = new MediaTracker(this);
//			mt.addImage(einPiktogramm, 0);
			try 
			{
				// Warten bis das Piktogramm geladen ist
				mt.waitForAll();
			} catch (InterruptedException e) {
			// nichts tun
		}
//		einFenster.setIconImage(einPiktogramm);
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
            
             //Die Daten werden in eine Datei geschrieben
	    	 Anzeigenverwaltung.getObjektreferenz().endeAnwendung();
	        
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
	 //Innere Klassen
	 //Menuebalken ************************************************************************
	 class MenueBalken extends  AcJMenuBar
     {
        //Konstruktor
        public MenueBalken(String identity)
        {
            super(identity);
            AcJMenu einMenue;
            JMenuItem eineOption;
                        
            //Dateimenue
            einMenue = new  AcJMenu("AnzeigeKundeGUI-Dateimenue", "Anwendung");
            einMenue.add(new BeendenAktion());
            add(einMenue); 
            
            //Stammdatenmenue
            einMenue = new  AcJMenu("AnzeigeKundeGUI-Stammdatenmenue","Stammdaten");
            einMenue.add(new AnzeigeAktion());
            einMenue.add(new KundeAktion());
            add(einMenue); 
            
            //Listenmenue
            einMenue = new  AcJMenu("AnzeigeKundeGUI-Listenmenue","Listen");
            einMenue.add(new AnzeigenlisteAktion());
            einMenue.add(new KundenlisteAktion()); 
            add(einMenue);

            //Benutzerwaltung
            einMenue = new AcJMenu("AnzeigeKundeGUI-Administration", "Administration");
            //An der rechten Seite angeordnet um sich abzuheben
            add(Box.createHorizontalGlue());
            einMenue.add(new BenutzerVerwaltungAktion());
            add(einMenue);

        }
    }

    //pop-up-Menue *********************************************************************** 
	class MeinPopupMenu extends JPopupMenu
    {
        //Konstruktor
        public MeinPopupMenu()
        {
        	add(new AnzeigeAktion());
        	add(new KundeAktion());
        	add(new AnzeigenlisteAktion());
        	add(new KundenlisteAktion()); 
        }
    }

        private class BenutzerVerwaltungAktion extends AbstractAction
        {
                {
			putValue(Action.NAME, "Benutzerverwaltung");
		}
		public void actionPerformed(ActionEvent e) {
			PermissionAdministrator.showPermissionAdministrator(false);
		}
        }

	private class AnzeigeAktion extends AbstractAction 
	{
		{
			putValue(Action.NAME, "Anzeige...");
		}
		public void actionPerformed(ActionEvent e) {
			Anzeigenfenster.anzeigenAnzeigefenster(AnzeigeKundeGUI.this, "Neu Anzeige",null,null);
		}
	}
	
	private class KundeAktion extends AbstractAction 
	{
		{
			putValue(Action.NAME, "Kunde...");
		}
		public void actionPerformed(ActionEvent e) {
			Kundenfenster.anzeigenKundenfenster(AnzeigeKundeGUI.this, "Neu Kunde",null,null);
		}
	}
		
	private class AnzeigenlisteAktion extends AbstractAction 
	{
		{
			putValue(Action.NAME, "Alle Anzeigen...");
		}
		public void actionPerformed(ActionEvent e) {
			Anzeigeliste.anzeigenAnzeigenliste(AnzeigeKundeGUI.this, "Liste der Anzeigen");
		}
	}
	
	private class KundenlisteAktion extends AbstractAction 
	{
		{
			putValue(Action.NAME, "Alle Kunden...");
		}
		public void actionPerformed(ActionEvent e) {
			Kundenliste.anzeigenKundenliste(AnzeigeKundeGUI.this, "Liste der Kunden");
		}
	}
	
	
	private class BeendenAktion extends AbstractAction 
    {
		{
			putValue(Action.NAME, "Beenden");
		}
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

    //Mausabhoerer *****************************************************************
    private class MausAbhoerer extends MouseAdapter
    {
        public void mouseReleased(MouseEvent evt)
        {
            if (evt.isPopupTrigger())
                einPopup.show(evt.getComponent(),evt.getX(),evt.getY());
        }
    }
}