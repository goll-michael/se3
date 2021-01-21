package server.application;

/**
 * Im Prinzip werden die gleichen Methodennamen genutzt, wie in
 * der Schnittstelle KundeContainerEinfachI bereits festgelegt,
 * jedoch muss eine RPC-Methode einen Rueckgabewert liefern,
 * daher hier nicht "implements KundeContainerEinfachI". 
 */
public class AnfragenBehandler
{
	public boolean einfuegeKunde(String name, int nummer)
	{
		KundenContainerEinfach.getObjektreferenz().einfuegeKunde(name, nummer);
		return true;
	}
	
	public int getNaechsteKundenNr()
	{
		return KundenContainerEinfach.getObjektreferenz().getNaechsteKundenNr();
	}
	
	public String getKundeZuNr(int nummer)
	{
		return KundenContainerEinfach.getObjektreferenz().getKundeZuNr(nummer);
	}

	
	//Methoden muessen etwas zurueckgeben, rpc-Methode darf nicht void sein
	public boolean endeAnwendung()
	{
		KundenContainerEinfach.getObjektreferenz().endeAnwendung();
		return true;
	}
}