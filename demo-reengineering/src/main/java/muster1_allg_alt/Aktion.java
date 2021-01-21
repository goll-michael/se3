package muster1_allg_alt;

public class Aktion {
	public static enum AKTIONSART {
		A, B
	};

	public void ausfuehren(AKTIONSART aktionsart) {
		switch (aktionsart) {
		case A:
			System.out.println("A");
			break;
		case B:
			System.out.println("B");
			break;
		default:
		}
	}
}
