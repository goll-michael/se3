package varianteA;

public class Geschaeftshaus extends Immobilie {
	private int gewerbeflaeche = 500;

	@Override
	public int gibPreis() {
		return gewerbeflaeche * 4;
	}
}