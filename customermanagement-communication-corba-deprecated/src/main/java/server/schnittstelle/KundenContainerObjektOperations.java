package server.schnittstelle;


/**
 * Generated from IDL interface "KundenContainerObjekt".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 09.07.2013 13:33:55
 */

public interface KundenContainerObjektOperations
{
	/* constants */
	/* operations  */
	void einfuegeKunde(server.schnittstelle.Kunde einKunde);
	server.schnittstelle.Kunde getKundeZuNr(int nummer);
	server.schnittstelle.Kunde erstelleKunde();
	int getNaechsteKundenNr();
	void endeAnwendung();
}
