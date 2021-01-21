package muster1_bsp_neu;

public class Client {
	public static void main(String[] args) {
		// Schnittstelle -> meineAusgabe.ausgeben() hier nicht moeglich
		Ausgabe meineAusgabe;

		// Textausgabe
		meineAusgabe = new TextAusgabe();
		meineAusgabe.ausgeben();

		// Bildausgabe
		meineAusgabe = new BildAusgabe();
		meineAusgabe.ausgeben();
	}
}
