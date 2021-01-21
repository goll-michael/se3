package inout;

import java.io.*;

/**
 * Diese Klasse stellt Methoden zur Verfügung, <br/>
 * um Texte und einfache Typen von Textdateien einzulesen<br/>
 * und in Textdateien zu schreiben.
 * <hr/>
 * 
 * @author Helmut Balzert
 * @version 1.0 / 1.6.2008
 */

public class FileIO {

	private static BufferedReader in;
	private static String inFilename;

	private static PrintWriter out;
	private static String outFilename;

	// Unterdrückung des default-Konstruktor,
	// um eine Objekterzeugung zu verhindern
	private FileIO() {
		// Dieser Konstruktor wird nie aufgerufen
	}

	/**
	 * Öffnet die Eingabedatei mit der Bezeichnung filename
	 */
	public static void openInFile(String filename) {
		inFilename = filename;
		try {
			FileReader infile = new FileReader(inFilename);
			in = new BufferedReader(infile);
		} catch (IOException io) {
			System.out.println("Fehler beim Öffnen der Datei " + inFilename);
			System.exit(1);
		}
	}

	/**
	 * Schließt die geöffnete Eingabedatei
	 */
	public static void closeInFile() {
		if (in != null) {
			try {
				in.close();
			} catch (IOException io) {
				System.out.println("Fehler beim Schließen der Datei " + inFilename);
				System.exit(1);
			}
		}
	}

	/**
	 * Liest eine Zahl vom Typ int von einer Textdatei
	 */
	public static int readIntFromFile() {
		// System.out.println("Einlesen einer Zahl von einer Datei");

		if (in == null) {
			System.out.println("Keine Datei zum Lesen geöffnet");
			System.exit(1);
		}
		int zahl = 0;
		try {
			String zeile = "";
			if ((zeile = in.readLine()) != null) {
				zahl = Integer.parseInt(zeile);
				// System.out.println("Eingelesene Zahl: " + zahl);
			}
			// in.close();

		} catch (NumberFormatException fehler) {
			System.out.println("\"" + fehler.getMessage() + "\" ist keine Zahl");
			System.exit(1);
		} catch (IOException io) {
			System.out.println("Eingabe-Fehler beim Einlesen einer Zahl von einer Datei");
			System.exit(1);
		}
		// System.out.println("Zahl = " + Zahl);
		// System.exit(0);
		return zahl;
	} // Ende readIntFromfile

	/**
	 * Liest eine Gleitpunktzahl vom Typ double von einer Textdatei
	 */
	public static double readDoubleFromFile() {
		if (in == null) {
			System.out.println("Keine Datei zum Lesen geöffnet");
			System.exit(1);
		}
		double zahl = 0.0;
		try {
			String zeile = "";
			if ((zeile = in.readLine()) != null) {
				zahl = Double.parseDouble(zeile);
				// System.out.println("Eingelesene Zahl: " + zahl);
			}
		} catch (NumberFormatException fehler) {
			System.out.println("\"" + fehler.getMessage() + "\" ist keine Zahl");
			System.exit(1);
		} catch (IOException io) {
			System.out.println("Eingabe-Fehler beim Einlesen einer Zahl von einer Datei");
			System.exit(1);
		}
		// System.out.println("Zahl = " + Zahl);
		// System.exit(0);
		return zahl;
	} // Ende readIDoubleFromFile

	/**
	 * Liest einen Text vom Typ String von einer Textdatei
	 */
	public static String readLineFromFile() {
		if (in == null) {
			System.out.println("Keine Datei zum Lesen geöffnet");
			System.exit(1);
		}
		String zeile = null;
		try {
			if ((zeile = in.readLine()) != null)
				return zeile;
		} catch (IOException io) {
			System.out.println("Fehler beim Lesen einer Zeile aus der Datei " + inFilename);
			System.exit(1);
		}
		return zeile;
	} // Ende readLineFromFile

//******************************************************************************

	/**
	 * Öffnet die Ausgabedatei mit der Bezeichnung filename
	 */
	public static void openOutFile(String filename) {
		outFilename = filename;
		try {
			FileWriter outfile = new FileWriter(outFilename, true);
			out = new PrintWriter(outfile, true);
		} catch (IOException io) {
			System.out.println("Fehler beim Öffnen der Datei " + outFilename);
			System.exit(1);
		}
	}

	/**
	 * Schließt die geöffnete Ausgabedatei
	 */
	public static void closeOutFile() {
		if (out != null)
			out.close();
	}

	/**
	 * Schreibt eine ganze Zahl vom Typ int in eine Textdatei
	 */
	public static void out(int zahl) {
		{
			if (out == null) {
				System.out.println("Es ist keine Datei zum Schreiben vorhanden.");
				System.exit(1);
			}
			out.println(zahl);
		}
	}

	/** Schreibt eine Gleitpunktzahl vom Typ double in eine Textdatei */
	public static void out(double zahl) {
		{
			if (out == null) {
				System.out.println("Es ist keine Datei zum Schreiben vorhanden.");
				System.exit(1);
			}
			out.println(zahl);
		}
	}

	/**
	 * Schreibt einen Text vom Typ String in eine Textdatei
	 */
	public static void out(String text) {
		{
			if (out == null) {
				System.out.println("Es ist keine Datei zum Schreiben vorhanden.");
				System.exit(1);
			}
			out.println(text);
		}
	}
}
