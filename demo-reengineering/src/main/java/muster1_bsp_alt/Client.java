package muster1_bsp_alt;

public class Client {
	public static void main(String[] args) {
		Ausgabe meineAusgabe = new Ausgabe();

		// Textnachricht ausgeben
		meineAusgabe.ausgeben(Ausgabe.ART.TEXT);

		// Bildnachricht ausgeben
		meineAusgabe.ausgeben(Ausgabe.ART.BILD);
	}
}
