package muster3_allg_alt;

public class Client {
	private static enum ZUSTAND {
		A, B, C
	};

	private static ZUSTAND meinZustand = null;

	public static void main(String[] args) {
		// Initialzustand
		meinZustand = ZUSTAND.A;

		// Zustandswechsel
		wechsleZustand(); // A->B

		// Zustandswechsel
		wechsleZustand(); // B->C

		// Zustandswechsel (kein Wechsel, da bereits in C)
		wechsleZustand(); // C
	}

	public static void wechsleZustand() {
		switch (meinZustand) {
		case A:
			System.out.println("A->B");
			meinZustand = ZUSTAND.B;
			break;
		case B:
			System.out.println("B->C");
			meinZustand = ZUSTAND.C;
			break;
		case C:
			System.out.println("C");
			break;
		default:
		}
	}
}
