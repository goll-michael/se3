package muster4_allg_neu;

public class Client {
	public static void main(String[] args) {
		Kontext ctx;

		ctx = new Kontext(new StrategieA());
		ctx.ausfuehren();

		ctx = new Kontext(new StrategieB());
		ctx.ausfuehren();
	}

}
