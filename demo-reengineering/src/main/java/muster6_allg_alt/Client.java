package muster6_allg_alt;

public class Client {
	public static void main(String[] args) {
		// Plugin A
		ausfuehren("A");

		// Plugin B
		ausfuehren("B");
	}

	public static void ausfuehren(String plugin) {
		// Pruefung, welches Plugin in Frage kommt
		if (plugin.equals("A")) {
			PluginA pluginA = new PluginA();
			pluginA.methodeA();
		} else if (plugin.equals("B")) {
			PluginB pluginB = new PluginB();
			pluginB.methodeB();
		}
	}
}
