package muster6_allg_neu;

public class PluginB implements Plugin {
	private final String parameter = "B";

	public PluginB() {
		// Beim Erstellen soll ein Objekt direkt beim Manager registriert werden.
		Manager.getObjektreferenz().einfuegePlugin(this);
	}

	@Override
	public void action() {
		System.out.println("Methodenrumpf B");
	}

	@Override
	public String getParameter() {
		return parameter;
	}

	@Override
	public void entfernePlugin() {
		Manager.getObjektreferenz().entfernePlugin(this);
	}
}
