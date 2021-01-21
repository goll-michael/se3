package de.se3.gui;


import javax.swing.*;

import de.se3.applikation.Anzeigenverwaltung;
import de.se3.applikation.Kunde;

import java.util.*;

public class Kundenliste extends ListenFenster implements Observer {
	// Attribute
	// Referenz auf das einzige Objekt
	private static Kundenliste dieKundenliste = null;
	private KundenModell einTabellenModell = new KundenModell();

	// privater Konstruktor
	private Kundenliste(JFrame dasAnwendungsfenster, String Fenstertitel) {
		super(dasAnwendungsfenster, Fenstertitel);
		eineTabellenAnsicht.setModel(einTabellenModell);
		Anzeigenverwaltung.getObjektreferenz().addObserver(this);

		KundenModell.ausrichten(eineTabellenAnsicht);
	}

	// Operationen
	// Vergleiche Anzeigeliste
	public static Kundenliste anzeigenKundenliste(JFrame dasAnwendungsfenster,
			String Fenstertitel) {
		if (dieKundenliste == null)
			dieKundenliste = new Kundenliste(dasAnwendungsfenster, Fenstertitel);
		// Konstruktor kann nur einmal aufgerufen werden
		dieKundenliste.setVisible(true);
		return dieKundenliste;
	}

	protected void neu() {
		Kundenfenster.anzeigenKundenfenster(
				(JFrame) dieKundenliste.getParent(), "Neuer Kunde", null, null);
		einTabellenModell.update();
	}

	protected void aendern() {
		try {
			int Zeile = eineTabellenAnsicht.getSelectedRow();
			if (Zeile > -1) {
				Kunde einKunde = (Kunde) Anzeigenverwaltung.getObjektreferenz()
						.gibKunde(Zeile);
				Kundenfenster.anzeigenKundenfenster((JFrame) dieKundenliste
						.getParent(), "Aendern Kunde", einKunde, null);
			}
		} catch (Exception e) {
		}
	}

	protected void loeschen() {
		try {
			int Zeile = eineTabellenAnsicht.getSelectedRow();
			if (Zeile > -1) {

				Kunde einKunde = (Kunde) Anzeigenverwaltung.getObjektreferenz()
						.gibKunde(Zeile);
				Anzeigenverwaltung.getObjektreferenz().entferneKunde(einKunde);

				einTabellenModell.update();
			}
		} catch (Exception e) {
		}
	}

	public void update(Observable o, Object arg) {
		einTabellenModell.update();
	}
}
