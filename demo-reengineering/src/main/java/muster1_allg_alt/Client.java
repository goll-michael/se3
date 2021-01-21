package muster1_allg_alt;

public class Client {
	public static void main(String[] args) {
		Aktion meineAktion = new Aktion();

		// Aktion A
		meineAktion.ausfuehren(Aktion.AKTIONSART.A);

		// Aktion B
		meineAktion.ausfuehren(Aktion.AKTIONSART.B);
	}
}
