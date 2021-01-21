package varianteC;

public class ImmobilieImpl implements ImmobilieIUse {
	private ImmobilieIUse2 eineImmobilie;
	private String besitzer;

	public ImmobilieImpl(char art) {
		if (art == 'P') // Privathaus
			eineImmobilie = new PrivathausImpl();
		else // Geschäftshaus
			eineImmobilie = new GeschaeftshausImpl();
	}

	public String getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(String besitzer) {
		this.besitzer = besitzer;
	}

	public int gibPreis() {
		// Weiterleitung
		return eineImmobilie.gibPreis();
	}
}
