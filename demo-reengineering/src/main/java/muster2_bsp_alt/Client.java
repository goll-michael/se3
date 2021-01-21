package muster2_bsp_alt;

public class Client {
	public static void main(String[] args) {
		/*
		 * Barzahlung: 10% Rabatt Vorkasse: 5% Rabatt Lastschrift: 0% Rabatt
		 */

		Bezahlung obj;

		// Barzahlung
		obj = new Bar();
		zahlen(obj, 100);

		// Bezahlvorgang mit Girokonto
		obj = new Vorkasse();
		zahlen(obj, 100);

		// Bezahlvorgang mit Kreditkarte
		obj = new Lastschrift();
		zahlen(obj, 100);
	}

	public static void zahlen(Bezahlung obj, double betrag) {
		if (obj instanceof Bar) {
			obj.ausgeben();
			((Bar) obj).berechneRabattBar(betrag);
		} else if (obj instanceof Vorkasse) {
			obj.ausgeben();
			((Vorkasse) obj).berechneRabattVorkasse(betrag);
		} else if (obj instanceof Lastschrift) {
			((Lastschrift) obj).berechneRabattLastschrift(betrag);
		}
	}
}
