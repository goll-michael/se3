package server;

import server.application.CustomerContainer;

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
		CustomerContainer.getReference().create(name, nummer);
		return true;
	}
	
	public int getNaechsteKundenNr()
	{
		return CustomerContainer.getReference().getNextNumber();
	}
	
	public String getKundeZuNr(int nummer)
	{
		return CustomerContainer.getReference().getCustomerName(nummer);
	}

	
	//Methoden muessen etwas zurueckgeben, rpc-Methode darf nicht void sein
	public boolean endeAnwendung()
	{
		CustomerContainer.getReference().close();
		return true;
	}
}