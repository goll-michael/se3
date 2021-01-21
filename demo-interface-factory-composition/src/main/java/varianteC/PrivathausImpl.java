package varianteC;

public class PrivathausImpl implements ImmobilieIUse2 {
	private int wohnflaeche = 80;

	public int gibPreis() {
		return wohnflaeche * 5;
	}
}
