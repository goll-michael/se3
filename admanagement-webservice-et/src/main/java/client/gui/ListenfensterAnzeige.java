package client.gui;

import javax.swing.JOptionPane;

import server.schnittstelle.fachkonzept.Anzeige;
import client.application.AnzeigenverwaltungDelegate;

/**
 * Unterfenster für die Darstellung der Anzeigenliste
 */
public class ListenfensterAnzeige extends Listenfenster
{
	private static final long serialVersionUID = 1L;

	// Attribute
	
	private AnzeigenverwaltungDelegate anzeigenverwaltung = AnzeigenverwaltungDelegate.getInstance();
	
	//Referenz auf das einzige Objekt
	private ModellAnzeige einTabellenModell = new ModellAnzeige();
	private UnterfensterAnzeige anzeigeFenster;
	
	//Fenster sichtbar machen und initialisieren
	public ListenfensterAnzeige(UnterfensterAnzeige anzFenster)  
	{
		super();
		this.anzeigeFenster = anzFenster;
		
		//Zuordnung des Tabellendaten-Modells (model) zur Tabellenansicht (view)
	    eineTabellenAnsicht.setModel(einTabellenModell);
		
	    ModellAnzeige.ausrichten(eineTabellenAnsicht);
	}
	    
    //Das Anzeige-Detailfenster oeffnen, damit der uebergebene Anzeige
    //veraendert werden kann.
    protected void aendern()
    {
        int zeile = eineTabellenAnsicht.getSelectedRow();
        if (zeile >-1)
        {
        	try
        	{
	            Anzeige eineAnzeige = 
	            	anzeigenverwaltung.getAnzeigen().get(zeile);
	            this.setVisible(false);
	            anzeigeFenster.zeigen(eineAnzeige);
	            anzeigeFenster.setVisible(true);	            
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
    			JOptionPane.showMessageDialog(this, "Ein Fehler ist aufgetreten: " + e.getMessage(),
    			        "Fehler", JOptionPane.OK_OPTION );
        	}
        }
    }
    
    //Die selektierte Anzeige aus dem Container entfernen. Zusaetzlich
    //werden alle Links auf die Anzeige geloescht.
    protected void loeschen()
    {
        try
        {
            int zeile = eineTabellenAnsicht.getSelectedRow();
            if (zeile > -1)
            {
            	Anzeige eineAnzeige = 
                	anzeigenverwaltung.getAnzeigen().get(zeile);
                anzeigenverwaltung.entferneAnzeige(eineAnzeige);
                updateTabelle();
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ein Fehler ist aufgetreten: " + e.getMessage(),
			        "Fehler", JOptionPane.OK_OPTION );
        }
    }

	@Override
	protected void updateTabelle() 
	{
		try
		{
			anzeigenverwaltung.aktualisiereListen();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		einTabellenModell.update();
	}
}       
        