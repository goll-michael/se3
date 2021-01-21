package de.se3.gui;


import javax.swing.*;

import de.se3.applikation.Anzeige;
import de.se3.applikation.Anzeigenverwaltung;

import java.util.*;

// Die Liste mit den Anzeigen
public class Anzeigeliste extends ListenFenster implements Observer
{
	//Attribute
	//Referenz auf das einzige Objekt
	private static Anzeigeliste dieAnzeigenliste = null;
	private AnzeigeModell einTabellenModell = new AnzeigeModell();

	//Fenster sichtbar machen und initialisieren
	private Anzeigeliste(JFrame dasAnwendungsfenster, String Fenstertitel)  
	{
		super(dasAnwendungsfenster, Fenstertitel);

		//Zuordnung des Tabellendaten-Modells (model) zur Tabellenansicht (view)
	    eineTabellenAnsicht.setModel(einTabellenModell);
			Anzeigenverwaltung.getObjektreferenz().addObserver(this);
		
	    
	    AnzeigeModell.ausrichten(eineTabellenAnsicht);
	}
	
	public static Anzeigeliste anzeigenAnzeigenliste(JFrame dasAnwendungsfenster, String Fenstertitel)
	{
	    if (dieAnzeigenliste == null)
	        dieAnzeigenliste = new Anzeigeliste(dasAnwendungsfenster, Fenstertitel);
	        //Konstruktor kann nur einmal aufgerufen werden
	    //Das Fenster beim AnzeigeContainer als Beobachter anmelden    
	    //Das Fenster sichtbar machen
	    dieAnzeigenliste.setVisible(true);
	    return dieAnzeigenliste;
	}


    
    //Das Anzeige-Detailfenster oeffnen, damit ein neuer Anzeige
    //eingegeben werden kann.
    protected void neu()
    {
        Anzeigenfenster.anzeigenAnzeigefenster((JFrame)dieAnzeigenliste.getParent(),"Neu Anzeige",null,null);
    }
    
    //Das Anzeige-Detailfenster oeffnen, damit der uebergebene Anzeige
    //veraendert werden kann.
    protected void aendern()
    {
        try
        {
            int Zeile = eineTabellenAnsicht.getSelectedRow();
            if (Zeile >-1)
            {
                Anzeige eineAnzeige = 
                	(Anzeige)Anzeigenverwaltung.getObjektreferenz().gibAnzeige(Zeile);
                Anzeigenfenster.anzeigenAnzeigefenster((JFrame)dieAnzeigenliste.getParent(), "Aendern Anzeige",eineAnzeige,null);
            }
        }
        catch (Exception e)
        {
        }
    }
    
    //Die selektierte Anzeige aus dem Container entfernen. Zusaetzlich
    //werden alle Links auf die Anzeige geloescht.
    protected void loeschen()
    {
        try
        {
            int Zeile = eineTabellenAnsicht.getSelectedRow();
            if (Zeile >-1)
            {
            	Anzeige eineAnzeige = 
                	(Anzeige)Anzeigenverwaltung.getObjektreferenz().gibAnzeige(Zeile);
                eineAnzeige.getKunde().removeLinkAnzeige(eineAnzeige);
                Anzeigenverwaltung.getObjektreferenz().entferneAnzeige(eineAnzeige);
                update(null, null);
            }
        }
        catch (Exception e)
        {
        }
    }

	public void update(Observable arg0, Object arg1) 
	{
		einTabellenModell.update();
	}
}       
        