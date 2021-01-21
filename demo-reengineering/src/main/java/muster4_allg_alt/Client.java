package muster4_allg_alt;

public class Client {
	public static enum STRATEGIE {
		A, B
	};

	public static STRATEGIE art;

	public static void main(String[] args) {
		art = STRATEGIE.A;
		aktion();

		art = STRATEGIE.B;
		aktion();
	}

	public static void aktion() {
		switch (art) {
		case A:
			StrategieA a = new StrategieA();
			a.aktion();
			break;
		case B:
			StrategieB b = new StrategieB();
			b.aktion();
			break;
		default:
		}
	}

}
