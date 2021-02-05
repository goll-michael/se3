package server.application;

import java.util.List;
import javax.ejb.Local;
import server.schnittstelle.fachkonzept.Anzeige;

@Local
public interface AnzeigenBeanLocal
{
	/**
	 * Methode zum Löschen einer Anzeige aus der Datenbank
	 */
	public void entferneAnzeige(Anzeige anzeige);
	
	/**
	 * Methode zum Suchen einer Anzeige anhand ihrer Nummer. Liefert null,
	 * wenn keine Anzeige zur übergebenen Nummer existiert.
	 */
	public Anzeige getAnzeigeZuNr(long nummer);
	
	/**
	 * Liefert eine Liste aller Anzeigen
	 */
	public List<Anzeige> getAnzeigenListe();
	
	/**
	 * Liefert die nächste freie Anzeigennummer
	 */
	public long getNaechsteAnzeigenNr();
	
	/**
	 * Speichert das übergebene Anzeigen-Objekt in der Datenbank
	 */
	public void speichereAnzeige(Anzeige eineAnzeige);
}
