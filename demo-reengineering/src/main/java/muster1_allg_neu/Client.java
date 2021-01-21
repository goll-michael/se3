package muster1_allg_neu;

public class Client {
	public static void main(String[] args) {
		// Schnittstelle Aktion -> meineAktion.ausfuehren() hier nicht moeglich
		Aktion meineAktion = null;

		// Aktion A
		meineAktion = new AktionA();
		meineAktion.ausfuehren();

		// Aktion B
		meineAktion = new AktionB();
		meineAktion.ausfuehren();
	}
}
