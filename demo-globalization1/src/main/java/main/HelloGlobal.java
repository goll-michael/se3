package main;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelloGlobal {
	private static String BUNDLENAME = "main.res.Mitteilungen";

	static public void main(String[] args) {
		String sprache;
		String land;

		sprache = new String("de");
		land = new String("DE");

		Locale aktuelleLokalisierung;
		ResourceBundle mitteilungen;

		aktuelleLokalisierung = new Locale(sprache, land);

		mitteilungen = ResourceBundle.getBundle(BUNDLENAME, aktuelleLokalisierung);

		System.out.println(mitteilungen.getString("start"));
		System.out.println(mitteilungen.getString("frage"));
		System.out.println(mitteilungen.getString("ende"));

		sprache = new String("en");
		land = new String("US");

		aktuelleLokalisierung = new Locale(sprache, land);

		mitteilungen = ResourceBundle.getBundle(BUNDLENAME, aktuelleLokalisierung);

		System.out.println(mitteilungen.getString("start"));
		System.out.println(mitteilungen.getString("frage"));
		System.out.println(mitteilungen.getString("ende"));

		sprache = new String("de");
		land = new String("CH");

		aktuelleLokalisierung = new Locale(sprache, land);

		mitteilungen = ResourceBundle.getBundle(BUNDLENAME, aktuelleLokalisierung);

		System.out.println(mitteilungen.getString("start"));
		System.out.println(mitteilungen.getString("frage"));
		System.out.println(mitteilungen.getString("ende"));
	}
}