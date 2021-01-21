package muster6_bsp_alt;

public class Client {
	public static void main(String[] args) {
		// Die einzulesenden Dateien (im
		String dateiEins = "dateiEins.txt";
		String dateiZwei = "dateiZwei.xml";

		// Dateien oeffnen
		pruefe(dateiEins);
		pruefe(dateiZwei);
	}

	public static void pruefe(String datei) {
		// Pruefung, welches Plugin in Frage kommt
		if (datei.substring(datei.length() - 3, datei.length()).equals("txt")) {
			PluginTXT pluginTXT = new PluginTXT();
			pluginTXT.oeffneDatei(datei);
		} else if (datei.substring(datei.length() - 3, datei.length()).equals("xml")) {
			PluginXML pluginXML = new PluginXML();
			pluginXML.leseDatei(datei);
		}
	}

}
