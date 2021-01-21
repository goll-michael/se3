package muster3_allg_neu;

public class Client {
	public static void main(String[] args) {
		// Initialzustand
		Zustand meinZustand = new A();

		// Zustandswechsel
		meinZustand = meinZustand.naechsterZustand(); // A->B

		// Zustandswechsel
		meinZustand = meinZustand.naechsterZustand(); // B->C

		// Zustandswechsel (kein Wechsel, da bereits in C)
		meinZustand = meinZustand.naechsterZustand(); // C
	}
}
