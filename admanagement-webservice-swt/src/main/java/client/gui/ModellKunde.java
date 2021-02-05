package client.gui;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.*;

import server.schnittstelle.fachkonzept.Kunde;
import client.application.AnzeigenverwaltungDelegate;

/**
 * Datenmodell für die Kundentabelle
 */
public class ModellKunde extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;

	private AnzeigenverwaltungDelegate anzeigenverwaltung = AnzeigenverwaltungDelegate.getInstance();

	// Spaltenueberschriften
	private final String[] spalten = { "Kunden-Nr.", "Name", "Vorname" };

	// Redefinieren geerbter Operationen
	public int getColumnCount() 
	{
		return spalten.length;
	}

	public int getRowCount() 
	{
		try
		{
			return anzeigenverwaltung.getKundenzahl();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	public Object getValueAt(int Zeile, int Spalte) 
	{
		try 
		{
			Kunde einKunde = anzeigenverwaltung.getKunden().get(Zeile);
			Object d = null;
			switch (Spalte) {
			case 0:
				d = einKunde.getNummer() + "";
				break;
			case 1:
				d = einKunde.getName().getNachname();
				break;
			case 2:
				d = einKunde.getName().getVorname();
				break;
			}
			return d;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	// Wird von JTable benoetigt, um die Spaltentitel anzuzeigen
	public String getColumnName(int Spalte) 
	{
		return spalten[Spalte];
	}

	public boolean isCellEditable(int Zeile, int Spalte)
	{
		return false;
	}

	public void update() 
	{
		fireTableChanged(new TableModelEvent(this));
	}

	// Die Spalten der Tabelle entsprechend ihres Typs ausrichten
	public static void ausrichten(JTable eineTabelle) 
	{
		// Renderer anlegen, der den Text horizontal zentriert
		DefaultTableCellRenderer einZentralAusrichter = new DefaultTableCellRenderer();
		einZentralAusrichter.setHorizontalAlignment(JLabel.CENTER);

		// Inhalte der Spalten horizontal zentrieren, einZentralRenderer
		// zuweisen
		TableColumn aktSpalte = eineTabelle.getColumn("Kunden-Nr.");
		aktSpalte.setCellRenderer(einZentralAusrichter);

		aktSpalte = eineTabelle.getColumn("Name");
		aktSpalte.setCellRenderer(einZentralAusrichter);

		aktSpalte = eineTabelle.getColumn("Vorname");
		aktSpalte.setCellRenderer(einZentralAusrichter);
	}

}