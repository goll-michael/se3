package varianteA;

public class Privathaus extends Immobilie {
	private int wohnflaeche = 80;

	@Override
	public int gibPreis() {
		return wohnflaeche * 5;
	}
}
