package server.schnittstelle.geschaeftslogik;

import java.util.List;
import javax.ejb.Remote;

import server.schnittstelle.fachkonzept.Anzeige;
import server.schnittstelle.fachkonzept.Kunde;


@Remote
public interface AnzeigenverwaltungRemote {
	/**
	 * Speichert eine Anzeige in der Datenbank.
	 * @param eineAnzeige Die zu speichernde Anzeige
	 */
	public void speichereAnzeige(Anzeige eineAnzeige);

	/**
	 * Speichert einen Kunden in der Datenbank.
	 * @param einKunde Der zu speichernde Kunde
	 */
	public void speichereKunde(Kunde einKunde);

	/**
	 * L�scht eine Anzeige aus der Datenbank.
	 * @param anzeige Die zu l�schende Anzeige
	 */
	public void entferneAnzeige(Anzeige anzeige);

	/**
	 * L�scht einen Kunden aus der Datenbank.
	 * @param einKunde Der zu l�schende Kunde
	 */
	public void entferneKunde(Kunde einKunde);

	/**
	 * Erstellt eine neue Anzeige und weist ihr dabei die n�chste freie
	 * Anzeigennummer zu.
	 * @return Ein neues Anzeigen-Objekt
	 */
	public Anzeige erstelleAnzeige();

	/**
	 * Erstellt einen neuen Kunden und weist ihm die n�chste freie 
	 * Kundennummer zu.
	 * @return Ein neues Kunden-Objekt
	 */
	public Kunde erstelleKunde();

	/**
	 * Sucht eine Anzeige zur �bergebenen Anzeigennummer.
	 * @param nummer Nummer der Anzeige
	 * @return Die Anzeige zur �bergebenen Nummer oder null, wenn keine solche Anzeige existiert
	 */
	public Anzeige getAnzeigeZuNr(long nummer);

	/**
	 * Sucht einen Kunden zur �bergebenen Kundennummer.
	 * @param nummer Nummer des Kunden
	 * @return Der Kunde zur �bergebenen Kundennummer oder null, wenn kein solcher Kunde existiert
	 */
	public Kunde getKundeZuNr(long nummer);
	
	/**
	 * Liefert eine Liste aller gespeicherten Kunden.
	 * @return Kundenliste
	 */
	public List<Kunde> getKunden();
	
	/**
	 * Liefert eine Liste aller gespeicherten Anzeigen.
	 * @return Anzeigenliste
	 */
	public List<Anzeige> getAnzeigen();
	
	/**
	 * Gibt vor dem Beenden der Anwendung eventuell belegte Resourcen frei.
	 * @return Status-Meldung
	 */
	public String endeAnwendung();	
}
