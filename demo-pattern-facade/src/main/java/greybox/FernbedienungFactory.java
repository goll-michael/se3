package greybox;

public abstract class FernbedienungFactory {
	public static IRadio getRadio() {
		return new Radio();
	}

	public static IDVDSpieler getDVDSpieler() {
		return new DVDSpieler();
	}

	public static IFernseher getFernseher() {
		return new Fernseher();
	}
}
