package de.se3.gui;



import javax.swing.table.AbstractTableModel;

import de.se3.applikation.Anzeige;
import de.se3.applikation.Kunde;

import javax.swing.event.TableModelEvent;


public class AnzeigenLinkModell extends AbstractTableModel
{
    //Attribute
    //Spaltenueberschriften
	private final String[]Spalten = {"Anzeigen-Nr.", "Titel","Preis"};
    private Kunde derKunde;

    public void setKunde(Kunde derKunde)
    {
        this.derKunde = derKunde;
    }

    //Redefinieren geerbter Operationen
    public int getColumnCount()
    {
            return Spalten.length;
    }
        
    public int getRowCount()
    {
            return derKunde != null ? derKunde.getAnzeigen().size() : 0;
    }
        
    public Object getValueAt(int zeile, int spalte)
    {
        Anzeige einAnzeige = derKunde.getAnzeigen().get(zeile);
        Object d = null;
            
        if(derKunde != null)
	        switch(spalte)
	        {
	            case 0: d = new Integer(einAnzeige.getNummer());
	                    break;
	            case 1: d = einAnzeige.getTitel();
	                    break;
	            case 2: d = new Float(einAnzeige.getPreis());
	                    break;
	        }
        return d;
    }

    //Wird von AcJTable benoetigt, um die Spaltentitel anzuzeigen
    public String getColumnName(int spalte)
    {
        return Spalten[spalte];
    }
        
    public boolean isCellEditable(int zeile, int spalte)
    {
        return false;
    }
        
    public void update()
    {
        fireTableChanged(new TableModelEvent(this));
    }
}