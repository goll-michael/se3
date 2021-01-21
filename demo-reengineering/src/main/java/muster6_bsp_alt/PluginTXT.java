package muster6_bsp_alt;

import java.io.BufferedReader;
import java.io.FileReader;

public class PluginTXT {
	public void oeffneDatei(String eineDatei) {
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
}
