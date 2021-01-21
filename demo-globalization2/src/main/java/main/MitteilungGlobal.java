package main;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Date;
import java.text.MessageFormat;

public class MitteilungGlobal {
	static void anzeigeMitteilung(Locale aktuelleLokalisierung) {
		System.out.println("Sprache und Land: " + aktuelleLokalisierung.toString());
		System.out.println();

		ResourceBundle mitteilungen = ResourceBundle.getBundle("main.res.MitteilungSchablone",
				aktuelleLokalisierung);

		Object[] argumenteDerMitteilung = { mitteilungen.getString("geschaeft"), 10, new Date() };

		MessageFormat format = new MessageFormat("");
		format.setLocale(aktuelleLokalisierung);

		format.applyPattern(mitteilungen.getString("schablone"));
		String output = format.format(argumenteDerMitteilung);
		System.out.println(output);
	}

	static public void main(String[] args) {
		anzeigeMitteilung(new Locale("de", "DE"));
		System.out.println();
		anzeigeMitteilung(new Locale("en", "US"));
	}
}
