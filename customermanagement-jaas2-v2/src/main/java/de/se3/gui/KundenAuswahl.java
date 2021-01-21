package de.se3.gui;


import javax.swing.*;

import de.se3.applikation.Anzeige;
import de.se3.applikation.Anzeigenverwaltung;
import de.se3.applikation.Kunde;

// Auswahl an Kunden in einer Liste
public class KundenAuswahl extends AuswahlFenster
{
    //Attribute
	//Referenz auf das einzige Objekt
	private static KundenAuswahl dieKundenliste = null;
	//Die Anzeige, welche Link zu Kunde aufbauen moechte
    private static Anzeige dieAnzeige;
	private KundenModell einTabellenModell = new KundenModell();
	
	//privater Konstruktor
	private KundenAuswahl(JFrame dasAnwendungsfenster, String fenstertitel)  
	{
		super(dasAnwendungsfenster, fenstertitel);
		eineTabellenAnsicht.setModel(einTabellenModell);
		//Die Spalten der Tabelle ausrichten
		KundenModell.ausrichten(eineTabellenAnsicht);
	}
	
	public static KundenAuswahl getObjektReferenz()
	{
	    return dieKundenliste;
	}

	//Operationen
	public static KundenAuswahl anzeigenKundenAuswahl(JFrame dasAnwendungsfenster, String fenstertitel,Anzeige einAnzeige)
	{
	    if (dieKundenliste == null)
	        dieKundenliste = new KundenAuswahl(dasAnwendungsfenster, fenstertitel);
	        //Konstruktor kann nur einmal aufgerufen werden
	    
	    dieAnzeige = einAnzeige;
	    
	    //Initialisieren und sichtbar machen
        dieKundenliste.setVisible(true);
	    return dieKundenliste;
	}

    
    protected void auswaehlen()
    {
        try
        {
            int Zeile = eineTabellenAnsicht.getSelectedRow();
            if (Zeile >-1)
            {
                Kunde einKunde = 
                	Anzeigenverwaltung.getObjektreferenz().gibKunde(Zeile);
                dieAnzeige.setKunde(einKunde);
                setVisible(false);
            }
        }
        catch (Exception e)
        {
        }
    }
    
    //aktualisiert die liste
	public void update()
	{
	    //Aktualisieren des Tabellenmodells
	    einTabellenModell.update();
	}
}