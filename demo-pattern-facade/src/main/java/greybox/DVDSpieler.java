package greybox;

//Die Methoden der Schnittstelle muessen implementiert werden,
//jedoch koennen weitere Methoden implementiert und genutzt werden.
//Dies tangiert FernbedienungDVDSpieler nicht
public class DVDSpieler implements IDVDSpieler {
	public void einschaltenDVDSpieler() {
		System.out.println("DVDSpieler ist eingeschaltet");
	}

	public void ausschaltenDVDSpieler() {
		System.out.println("DVDSpieler ist ausgeschaltet");
	}
}