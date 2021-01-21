package muster3_bsp_alt;

public class Client {
	private static enum PREIS {
		EINKAUF, SELBSTKOSTEN, VERKAUF
	};

	private static PREIS meinStatus = null;
	private static double meinPreis = 0.0;

	public static void main(String[] args) {
		// Initialzustand
		meinStatus = PREIS.EINKAUF;
		meinPreis = 100.0;
		System.out.println("Der aktuelle Preis liegt bei " + meinPreis + " Euro.");

		// Zustandswechsel
		wechsleStatus(); // EINKAUF->SELBSTKOSTEN
		System.out.println("Der aktuelle Preis liegt bei " + meinPreis + " Euro.");

		// Zustandswechsel
		wechsleStatus(); // SELBSTKOSTEN->VERKAUF
		System.out.println("Der aktuelle Preis liegt bei " + meinPreis + " Euro.");

		// Zustandswechsel
		wechsleStatus(); // VERKAUF
		System.out.println("Der aktuelle Preis liegt bei " + meinPreis + " Euro.");
	}

	public static void wechsleStatus() {
		switch (meinStatus) {
		case EINKAUF:
			meinPreis = meinPreis * 1.2;
			meinStatus = PREIS.SELBSTKOSTEN;
			break;
		case SELBSTKOSTEN:
			meinPreis = meinPreis * 1.05;
			meinStatus = PREIS.VERKAUF;
			break;
		case VERKAUF:
			System.out.println("Verkaufspreis bereits berechnet.");
			meinStatus = PREIS.VERKAUF;
			break;
		default:
		}
	}
}
