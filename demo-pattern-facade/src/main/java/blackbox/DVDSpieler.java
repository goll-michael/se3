package blackbox;

public class DVDSpieler implements IGeraet {
	public void einschalten() {
		System.out.println("DVDSpieler ist eingeschaltet");
	}

	public void ausschalten() {
		System.out.println("DVDSpieler ist ausgeschaltet");
	}
}