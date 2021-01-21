package server.schnittstelle;

/**
 * Schnittstelle fuer die Uebertragung von einfachen Datentypen.
 */
public interface KundenContainerEinfachI
{
	public void einfuegeKunde(String name, int nummer);
	
	public int getNaechsteKundenNr();
	
	public String getKundeZuNr(int nummer);

	public void endeAnwendung();
}