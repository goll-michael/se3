package muster6_bsp_neu;

public interface Plugin {
	// Die Methode, die der Manager ausfuehrt.
	public void action(String dateiname);

	// Zum Pruefen, ob ein Plugin fuer den aktuellen Suffix zustaendig ist.
	public String getSuffix();

	// Zum Entfernen des Plugins beim Manager.
	public void entfernePlugin();
}
