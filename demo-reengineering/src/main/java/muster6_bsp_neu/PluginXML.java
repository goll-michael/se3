package muster6_bsp_neu;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PluginXML implements Plugin {
	private final String suffix = "xml";

	public PluginXML() {
		// Beim Erstellen soll ein Objekt direkt beim Manager registriert werden.
		Manager.getObjektreferenz().einfuegePlugin(this);
	}

	@Override
	public void action(String eineDatei) {
		try {
			XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(eineDatei)));
			Object result = d.readObject();
			d.close();
			System.out.println("Inhalt der XML-Datei: " + result);
		} catch (FileNotFoundException e) {
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
