package muster6_bsp_neu;

import java.io.BufferedReader;
import java.io.FileReader;

public class PluginTXT implements Plugin {
	private final String suffix = "txt";

	public PluginTXT() {
		// Beim Erstellen soll ein Objekt direkt beim Manager registriert werden.
		Manager.getObjektreferenz().einfuegePlugin(this);
	}

	@Override
	public void action(String eineDatei) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(eineDatei));
			String result = br.readLine();
			br.close();
			System.out.println("Inhalt der TXT-Datei: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	@Override
	public void entfernePlugin() {
		Manager.getObjektreferenz().entfernePlugin(this);
	}
}
