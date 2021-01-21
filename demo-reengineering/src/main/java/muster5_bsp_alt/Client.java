package muster5_bsp_alt;

public class Client {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Datumausgabe datum = null;

		// Objekt ist null, Methode wird nicht ausgefuehrt
		if (datum != null)
			datum.ausgeben();

		datum = new Datumausgabe();

		// Objekt ist nicht null, Methode wird ausgefuehrt
		if (datum != null)
			datum.ausgeben();
	}
}
