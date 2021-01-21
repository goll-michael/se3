package blackbox;

public abstract class FactoryFassade {
	public static IFassade getFassade() {
		return new Fassade();
	}
}
