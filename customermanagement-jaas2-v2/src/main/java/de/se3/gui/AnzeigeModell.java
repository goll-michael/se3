package de.se3.gui;



import javax.swing.event.TableModelEvent;
import javax.swing.table.*;

import de.se3.applikation.Anzeige;
import de.se3.applikation.Anzeigenverwaltung;
import de.se3.security.ac.swing.AcJLabel;
import de.se3.security.ac.swing.AcJTable;

public class AnzeigeModell extends AbstractTableModel {
	// Attribute
	// Spalten�berschriften
	private final String[] spalten = { "Anzeigen-Nr.", "Titel", "Preis" };

	// Redefinieren geerbter Operationen
	public int getColumnCount() {
		return spalten.length;
	}

	public int getRowCount() {
		return Anzeigenverwaltung.getObjektreferenz().getAnzeigenzahl();
	}

	public Object getValueAt(int Zeile, int Spalte) {
		try {
			Anzeige eineAnzeige = Anzeigenverwaltung.getObjektreferenz()
					.gibAnzeige(Zeile);
			Object d = null;
			switch (Spalte) {
			case 0:
				d = eineAnzeige.getNummer() + "";
				break;
			case 1:
				d = eineAnzeige.getTitel();
				break;
			case 2:
				d = eineAnzeige.getPreis() + "";
				break;
			}
			return d;
		} catch (Exception e) {
			return null;
		}
	}

	// Wird von AcJTable benoetigt, um die Spaltentitel anzuzeigen
	public String getColumnName(int spalte) {
		return spalten[spalte];
	}

	public boolean isCellEditable(int zeile, int spalte) {
		return false;
	}

	public void update() {
		fireTableChanged(new TableModelEvent(this));
	}

	// Richtet die Spalten entsprechend des enthaltenen Typs aus
	public static void ausrichten(AcJTable eineTabelle) {
		// Wie sollen die Inhalte der einzelnen Spalten angeordnet werden
		TableColumn aktSpalte = eineTabelle.getColumn("Anzeigen-Nr.");
		DefaultTableCellRenderer einZentralAusrichter = new DefaultTableCellRenderer();
		einZentralAusrichter.setHorizontalAlignment(AcJLabel.CENTER);
		aktSpalte.setCellRenderer(einZentralAusrichter);

		aktSpalte = eineTabelle.getColumn("Titel");
		aktSpalte.setCellRenderer(einZentralAusrichter);

		aktSpalte = eineTabelle.getColumn("Preis");
		aktSpalte.setCellRenderer(einZentralAusrichter);
	}
}
