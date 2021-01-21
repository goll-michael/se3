package beispiel2.protect;

public class Personal implements PersonalI {
	String[] gehalt = { "Meyer: 2500", "Herbst: 4800" };

	/*
	 * Keine Angabe: Zugriff nur aus Klassen desselben Pakets Selbst bei Vererbung
	 * kein Zugriff
	 * 
	 * public: Von allen Klassen aller Pakete zugreifbar
	 * 
	 * protected: Von allen Klassen dieses Pakets zugreifbar Klassen von außerhalb
	 * können jedoch zugreifen, wenn sie Unterklassen sind
	 * 
	 * private: Nur von dieser Klasse aus zugreifbar
	 */
	Personal() // Package private (default)
	{
	}

	public String[] getListe() {
		return gehalt;
	}
}
