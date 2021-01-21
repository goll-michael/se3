package muster6_allg_neu;

public interface Plugin {
	// Die Methode, die der Manager ausfuehrt.
	public void action();

	// Zum Pruefen, ob ein Plugin fuer den Parameter
	public String getParameter();

	// Zum Entfernen des Plugins beim Manager.
	public void entfernePlugin();
}
