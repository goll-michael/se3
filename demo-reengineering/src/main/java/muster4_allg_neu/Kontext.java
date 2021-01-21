package muster4_allg_neu;

public class Kontext {
	private Strategie meineStrategie;

	public Kontext(Strategie eineStrategie) {
		meineStrategie = eineStrategie;
	}

	public void ausfuehren() {
		meineStrategie.aktion();
	}
}
