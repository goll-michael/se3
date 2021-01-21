package blackbox;

public class Fernseher implements IGeraet {
	public void einschalten() {
		System.out.println("Fernseher ist eingeschaltet");
	}

	public void ausschalten() {
		System.out.println("Fernseher ist ausgeschaltet");
	}
}
