package client.application;

import server.schnittstelle.KundenContainerEinfachI;

//ServerProxy stellt für die GUI-Klassen die Verbindung zum Server her
public class ServerProxy implements KundenContainerEinfachI
{
	//Singleton-Muster
	private static ServerProxy serverProxy = null;
	
	private KundenanfragenManagerService kms;
	private Kundenverwaltung kv;
	
	public ServerProxy()
	{
		kms = new KundenanfragenManagerService();
		kv = kms.getKundenverwaltungPort();
	}

	/*
	 * Klassen-Operation, die die Objektreferenz liefert.
	 * Wenn das Objekt noch nicht vorhanden ist, dann wird es erzeugt
	 */
	public static ServerProxy getObjektreferenz()
	{
		if (serverProxy == null)
			serverProxy = new ServerProxy();
		return serverProxy;
	}
	
	@Override
	public void einfuegeKunde(String name, int nummer)
	{
		try
		{
			kv.einfuegeKunde(name, nummer);			
		}
		catch (Exception e)
		{
			try
			{
				throw new Exception();
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}
	
	public int getNaechsteKundenNr()
	{		
        try
		{
        	return kv.getNaechsteKundenNr();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
        return 0;
	}
	
	public String getKundeZuNr(int nummer)
	{
		try
		{
			return kv.getKundeZuNr(nummer);			
		}
		catch (Exception e)
		{
			try
			{
				throw new Exception();
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public void endeAnwendung()
	{
		try
		{
			kv.endeAnwendung();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
