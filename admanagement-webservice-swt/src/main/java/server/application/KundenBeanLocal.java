package server.application;

import java.util.List;
import javax.ejb.Local;
import server.schnittstelle.fachkonzept.Kunde;

@Local
public interface KundenBeanLocal
{
	/**
	 * L�scht einen Kunden aus de Datenbank
	 */
	public void entferneKunde(Kunde einKunde);
	
	/**
	 * Liefert das Kunden-Objekt zur �bergebenen Kundennummer oder null,
	 * wenn kein Kunde mit der Nummer existiert
	 */
	public Kunde getKundeZuNr(long nummer);
	
	/**
	 * Liefert eine Liste alle Kunden
	 */
	public List<Kunde> getKundenListe();
	
	/**
	 * Liefert die n�chste freie Kundennummer
	 */
	public long getNaechsteKundenNr();
	
	/**
	 * Speichert ein Kunden-Objekt in der Datenbank
	 */
	public void speichereKunde(Kunde einKunde);
}
