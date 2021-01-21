package varianteB;

public class Privathaus2 {
	private Immobilie2 eineImmobilie = new Immobilie2();

	private int wohnflaeche = 80;

	public int gibPreis() {
		return wohnflaeche * 5;
	}

	public String getBesitzer() {
		return eineImmobilie.getBesitzer();
	}

	public void setBesitzer(String besitzer) {
		eineImmobilie.setBesitzer(besitzer);
	}
}
