package muster3_bsp_neu;

public class Client {
	public static void main(String[] args) {
		/*
		 * Berechnung von Einkaufs- bis Verkaufspreis ->Einkaufspreis (darauf eigene
		 * Kosten addieren mit 20% Aufschlag) ->Selbstkostenpreis (darauf 5% Gewinn
		 * addieren) ->Verkaufspreis (Preis, den der Kunde bezahlen muss)
		 */

		Preis meinStatus;

		// Initialzustand
		meinStatus = new Einkauf(100);
		meinStatus.ausgebenPreis();

		// Zustandswechsel
		meinStatus = meinStatus.naechsterStatus();
		meinStatus.ausgebenPreis();

		// Zustandswechsel
		meinStatus = meinStatus.naechsterStatus();
		meinStatus.ausgebenPreis();

		// Zustandswechsel
		meinStatus = meinStatus.naechsterStatus();
		meinStatus.ausgebenPreis();
	}
}
