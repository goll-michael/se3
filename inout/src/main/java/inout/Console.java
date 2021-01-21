package inout;

import java.util.*;
import java.util.regex.*;
import java.text.*;

/**
 * Diese Klasse stellt Methoden zur Verfügung, <br/>
 * um Texte und einfache Typen von der Konsole einzulesen.<br/>
 * Die Ausnahmebehandlung ist Aufgabe des Aufrufers.
 * <hr/>
 * 
 * @author Helmut Balzert
 * @version 2.0 / 1.7.2006
 */
public class Console {
	private static Scanner sc;

	// Unterdrückung des default-Konstruktor,
	// um eine Objekterzeugung zu verhindern
	private Console() {
		// Dieser Konstruktor wird nie aufgerufen
	}

	/**
	 * Liest eine Zeile von der Konsole
	 * 
	 * @return Eingelesene Zeile vom Typ String.
	 * @exception NoSuchElementException: Es wurde keine Eingabezeile gefunden.
	 * @exception IllegalStateException:  Die verwendete Methode ist nicht geöffnet.
	 */
	public static String readString() throws NoSuchElementException, IllegalStateException {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	/**
	 * Liest eine Zeile von der Konsole
	 * 
	 * @return Eingelesene Zeile vom Typ char[].
	 * @exception NoSuchElementException: Es wurde keine Eingabezeile gefunden.
	 * @exception IllegalStateException:  Die verwendete Methode ist nicht geöffnet.
	 */
	public static char[] readCharArray() throws NoSuchElementException, IllegalStateException {
		sc = new Scanner(System.in);
		String text = sc.nextLine();
		return text.toCharArray();
	}

	/**
	 * Liest einen booleschen Wert von der Konsole
	 * 
	 * @return Boolescher Wert true oder false.
	 * @exception NoSuchElementException: Es wurde keine Eingabezeile gefunden.
	 * @exception IllegalStateException:  Die verwendete Methode ist nicht geöffnet.
	 * @exception InputMismatchException: Die Eingabe entspricht nicht dem Typ.
	 */
	public static boolean readBoolean() throws InputMismatchException, NoSuchElementException, IllegalStateException {
		sc = new Scanner(System.in);
		return sc.nextBoolean();
	}

	/**
	 * Liest eine ganze Zahl vom Typ int von der Konsole
	 * 
	 * @return Ganze Zahl vom Typ int.
	 * @exception InputMismatchException: Die Eingabe entspricht nicht dem Typ.
	 * @exception NoSuchElementException: Es wurde keine Eingabezeile gefunden.
	 * @exception IllegalStateException:  Die verwendete Methode ist nicht geöffnet.
	 */
	public static int readInt() throws InputMismatchException, NoSuchElementException, IllegalStateException {
		return new Scanner(System.in).nextInt();
	}

	/**
	 * Liest eine ganze Zahl vom Typ long von der Konsole
	 * 
	 * @return Ganze Zahl vom Typ long
	 * @exception InputMismatchException: Die Eingabe entspricht nicht dem Typ.
	 * @exception NoSuchElementException: Es wurde keine Eingabezeile gefunden.
	 * @exception IllegalStateException:  Die verwendete Methode ist nicht geöffnet.
	 */
	public static long readLong() throws InputMismatchException, NoSuchElementException, IllegalStateException {
		return new Scanner(System.in).nextLong();
	}

	/**
	 * Liest eine Gleitpunktzahl vom Typ float von der Konsole Englische Notation:
	 * Trennung der Nachkommastellen durch Punkt
	 * 
	 * @return Gleitpunktzahl vom Typ float
	 * @exception InputMismatchException: Die Eingabe entspricht nicht dem Typ.
	 * @exception NoSuchElementException: Es wurde keine Eingabezeile gefunden.
	 * @exception IllegalStateException:  Die verwendete Methode ist nicht geöffnet.
	 */
	public static float readFloatPoint() throws InputMismatchException, NoSuchElementException, IllegalStateException {
		Locale.setDefault(Locale.ENGLISH);
		return new Scanner(System.in).nextFloat();
	}

	/**
	 * Liest eine Gleitpunktzahl vom Typ float von der Konsole Deutsche Notation:
	 * Trennung der Nachkommastellen durch Komma
	 * 
	 * @return Gleitpunktzahl vom Typ float
	 * @exception InputMismatchException: Die Eingabe entspricht nicht dem Typ.
	 * @exception NoSuchElementException: Es wurde keine Eingabezeile gefunden.
	 * @exception IllegalStateException:  Die verwendete Methode ist nicht geöffnet.
	 */
	public static float readFloatComma() throws InputMismatchException, NoSuchElementException, IllegalStateException {
		Locale.setDefault(Locale.GERMAN);
		return new Scanner(System.in).nextFloat();
	}

	/**
	 * Liest eine Gleitpunktzahl vom Typ double von der Konsole Englische Notation:
	 * Trennung der Nachkommastellen durch Punkt
	 * 
	 * @return Gleitpunktzahl vom Typ double
	 * @exception InputMismatchException: Die Eingabe entspricht nicht dem Typ.
	 * @exception NoSuchElementException: Es wurde keine Eingabezeile gefunden.
	 * @exception IllegalStateException:  Die verwendete Methode ist nicht geöffnet.
	 */
	public static double readDoublePoint()
			throws InputMismatchException, NoSuchElementException, IllegalStateException {
		Locale.setDefault(Locale.ENGLISH);
		return new Scanner(System.in).nextDouble();
	}

	/**
	 * Liest eine Gleitpunktzahl vom Typ double von der Konsole Deutsche Notation:
	 * Trennung der Nachkommastellen durch Komma
	 * 
	 * @return Gleitpunktzahl vom Typ double
	 * @exception InputMismatchException: Die Eingabe entspricht nicht dem Typ.
	 * @exception NoSuchElementException: Es wurde keine Eingabezeile gefunden.
	 * @exception IllegalStateException:  Die verwendete Methode ist nicht geöffnet.
	 */
	public static double readDoubleComma()
			throws InputMismatchException, NoSuchElementException, IllegalStateException {
		Locale.setDefault(Locale.GERMAN);
		return new Scanner(System.in).nextDouble();
	}

	/**
	 * Liest ein Zeichen vom Typ char von der Konsole
	 * 
	 * @return Erstes eingegebene Zeichen vom Typ char.
	 * @exception NoSuchElementException: Es wurde keine Eingabezeile gefunden.
	 */
	public static char readChar() throws NoSuchElementException, IllegalStateException {
		String s = new Scanner(System.in).next();
		return s.charAt(0);
	}

	/**
	 * Liest ein Datum vom Typ Calendar von der Konsole Eingabeformat: tt.mm.jjjj
	 * oder tt-mm-jjj oder tt/mm/jjjj es erfolgt eine Prüfung, ob der Tag zwischen 1
	 * und 31 liegt, der Monat zwichen 1 und 12 und das Jahr zwischen 1 und 2100
	 * 
	 * @return Eingegebenes Datum, wenn Leereingabe, dann 01.01.0001
	 * @exception Exception:      Fehlerhafte Datumsangabe.
	 * @exception ParseException: Falscher Datumsaufbau.
	 */
	public static Calendar readDate() throws Exception, ParseException
	// NoSuchElementException,IllegalStateException
	{
		DateFormat meinKurzformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);
		String einDatumStr = Console.readString();
		Calendar einDatum = Calendar.getInstance();
		if (einDatumStr.equals("")) {
			einDatum.clear();
			einDatum.set(1, 0, 1);// 1.1.0001
			// System.out.println("Leere Datumeingabe " + einDatum.getTime());
			return einDatum;
		} else // Überprüfung der Eingaben
		{
			// System.out.println("Eingelesener String: " + einDatumStr);
			Pattern p = Pattern.compile("[/.-]");
			String[] ttmmjjjj = p.split(einDatumStr);
			int tag = Integer.valueOf(ttmmjjjj[0]);
			int monat = Integer.valueOf(ttmmjjjj[1]);
			int jahr = Integer.valueOf(ttmmjjjj[2]);

			if ((monat < 1 || monat > 12) || (tag < 1 || tag > 31) || (jahr < 1 || jahr > 2100))
				throw new Exception("Fehlerhafte Datumsangabe");
			// Eingaben sind korrekt
			Date datum = null;
			datum = meinKurzformat.parse(einDatumStr);
			einDatum.setTime(datum);
			return einDatum;
		}
	}
}
