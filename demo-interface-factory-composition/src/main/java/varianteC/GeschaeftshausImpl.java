package varianteC;

public class GeschaeftshausImpl implements ImmobilieIUse2 {
	private int gewerbeflaeche = 500;

	public int gibPreis() {
		return gewerbeflaeche * 4;
	}
}
