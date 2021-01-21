package varianteA;

public abstract class Immobilie {
	private String besitzer;

	public String getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(String besitzer) {
		this.besitzer = besitzer;
	}

	public abstract int gibPreis();
}
