package client.gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.*;
import javax.swing.table.*;

import server.schnittstelle.fachkonzept.Anzeige;
import client.application.AnzeigenverwaltungDelegate;

/**
 * Datenmodell für die Anzeigen-Tabelle
 */
public class ModellAnzeige extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;

	private AnzeigenverwaltungDelegate anzeigenverwaltung = AnzeigenverwaltungDelegate.getInstance();
	
	// Spaltenueberschriften
	private final String[] spalten = { "Anzeigen-Nr.", "Titel", "Preis" };

	// Redefinieren geerbter Operationen
	public int getColumnCount() 
	{
		return spalten.length;
	}

	public int getRowCount() 
	{
		try
		{
			int zahl = anzeigenverwaltung.getAnzeigenzahl();
			return zahl;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	public Object getValueAt(int Zeile, int Spalte) 
	{
		try {
			Anzeige eineAnzeige = anzeigenverwaltung.getAnzeigen().get(Zeile);
			Object d = null;
			switch (Spalte) {
			case 0:
				d = eineAnzeige.getNummer();
				break;
			case 1:
				d = eineAnzeige.getTitel();
				break;
			case 2:
				d = eineAnzeige.getPreis();
				break;
			}
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Wird von JTable benoetigt, um die Spaltentitel anzuzeigen
	public String getColumnName(int spalte) 
	{
		return spalten[spalte];
	}

	public boolean isCellEditable(int zeile, int spalte) 
	{
		return false;
	}

	public void update() 
	{
		fireTableChanged(new TableModelEvent(this));
	}

	// Richtet die Spalten entsprechend des enthaltenen Typs aus
	public static void ausrichten(JTable eineTabelle) 
	{
		// Wie sollen die Inhalte der einzelnen Spalten angeordnet werden
		TableColumn aktSpalte = eineTabelle.getColumn("Anzeigen-Nr.");
		DefaultTableCellRenderer einZentralAusrichter = new DefaultTableCellRenderer();
		einZentralAusrichter.setHorizontalAlignment(JLabel.CENTER);
		aktSpalte.setCellRenderer(einZentralAusrichter);

		aktSpalte = eineTabelle.getColumn("Titel");
		aktSpalte.setCellRenderer(einZentralAusrichter);

		aktSpalte = eineTabelle.getColumn("Preis");
		aktSpalte.setCellRenderer(einZentralAusrichter);
	}
}
