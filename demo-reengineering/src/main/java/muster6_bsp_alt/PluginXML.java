package muster6_bsp_alt;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PluginXML {
	public void leseDatei(String eineDatei) {
		try {
			XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(eineDatei)));
			Object result = d.readObject();
			d.close();
			System.out.println("Inhalt der XML-Datei: " + result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
