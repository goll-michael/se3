package muster2_bsp_neu;

public class Client {
	public static void main(String[] args) {
		/*
		 * Barzahlung: 10% Rabatt Vorkasse: 5% Rabatt Lastschrift: 0% Rabatt
		 */

		Bezahlung obj;

		// Barzahlung
		obj = new Bar();
		obj.ausfuehren(100);

		// Vorkasse
		obj = new Vorkasse();
		obj.ausfuehren(100);

		// Lastschrift
		obj = new Lastschrift();
		obj.ausfuehren(100);
	}
}
