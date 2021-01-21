package de.se3.gui;

import javax.swing.event.TableModelEvent;
import javax.swing.table.*;

import de.se3.applikation.Anzeigenverwaltung;
import de.se3.applikation.Kunde;
import de.se3.security.ac.swing.AcJLabel;
import de.se3.security.ac.swing.AcJTable;

public class KundenModell extends AbstractTableModel {
	// Attribute
	// Spaltenueberschriften
	private final String[] Spalten = { "Kunden-Nr.", "Name", "Vorname" };

	// Redefinieren geerbter Operationen
	public int getColumnCount() {
		return Spalten.length;
	}

	public int getRowCount() {
		return Anzeigenverwaltung.getObjektreferenz().getKundenzahl();
	}

	public Object getValueAt(int Zeile, int Spalte) {
		try {
			Kunde einKunde = (Kunde) Anzeigenverwaltung.getObjektreferenz()
					.gibKunde(Zeile);
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
		} catch (Exception e) {
			return null;
		}
	}

	// Wird von AcJTable benoetigt, um die Spaltentitel anzuzeigen
	public String getColumnName(int Spalte) {
		return Spalten[Spalte];
	}

	public boolean isCellEditable(int Zeile, int Spalte) {
		return false;
	}

	public void update() {
		fireTableChanged(new TableModelEvent(this));
	}

	// Die Spalten der Tabelle entsprechend ihres Typs ausrichten
	public static void ausrichten(AcJTable eineTabelle) {
		// Renderer anlegen, der den Text horizontal zentriert
		DefaultTableCellRenderer einZentralAusrichter = new DefaultTableCellRenderer();
		einZentralAusrichter.setHorizontalAlignment(AcJLabel.CENTER);

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