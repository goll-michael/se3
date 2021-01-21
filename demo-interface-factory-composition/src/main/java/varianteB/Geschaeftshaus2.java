package varianteB;

public class Geschaeftshaus2 {
	private Immobilie2 eineImmobilie = new Immobilie2();

	private int gewerbeflaeche = 500;

	public int gibPreis() {
		return gewerbeflaeche * 4;
	}

	public String getBesitzer() {
		return eineImmobilie.getBesitzer();
	}

	public void setBesitzer(String besitzer) {
		eineImmobilie.setBesitzer(besitzer);
	}
}
