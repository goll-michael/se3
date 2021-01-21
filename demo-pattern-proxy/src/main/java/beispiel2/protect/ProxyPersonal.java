package beispiel2.protect;

public class ProxyPersonal implements PersonalI {
	private Personal einePersonalliste;
	private String einName, zugangName = "Sommer";
	private String einPasswort, zugangPasswort = "geheim";
	private String[] eineFehlermeldung = { "Keine Zugangsberechtigung" };

	public ProxyPersonal(String einName, String einPasswort) {
		this.einName = einName;
		this.einPasswort = einPasswort;
		einePersonalliste = new Personal();
	}

	public String[] getListe() {
		if (einName.equals(zugangName) && einPasswort.equals(zugangPasswort))
			return einePersonalliste.getListe();
		else
			return eineFehlermeldung;
	}
}
