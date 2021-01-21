package muster6_allg_neu;

public class PluginA implements Plugin {
	private final String parameter = "A";

	public PluginA() {
		// Beim Erstellen soll ein Objekt direkt beim Manager registriert werden.
		Manager.getObjektreferenz().einfuegePlugin(this);
	}

	@Override
	public void action() {
		System.out.println("Methodenrumpf A");
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
