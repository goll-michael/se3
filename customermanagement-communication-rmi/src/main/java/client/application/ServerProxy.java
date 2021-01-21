package client.application;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.schnittstelle.KundeI;
import server.schnittstelle.KundenContainerEinfachI;
import server.schnittstelle.KundenContainerObjektI;

//ServerProxy stellt für die GUI-Klassen die Verbindung zum Server her
public class ServerProxy implements KundenContainerEinfachI
{
	//Singleton-Muster
	private static ServerProxy serverProxy = null;

	private static final String RMI_SERVICE_NAME = "kundenverwaltung";
	private static KundeI einKunde = null;
	private static KundenContainerObjektI datenService;
		
	public ServerProxy()
	{
		try
		{	
			Registry registry = LocateRegistry.getRegistry();	
			datenService = (KundenContainerObjektI) registry.lookup(RMI_SERVICE_NAME);
		}
		catch (Exception e)
		{
			System.out.println(e.getStackTrace());
		}
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
			einKunde = datenService.getKundeZuNr(nummer);
		
			/*
			 * Referenz eines neuen Kundenobjekts wird abgefragt.
			 * Bei RMI und bei CORBA werden Kundenobjekte ueber das
			 * Netzwerk uebertragen.
			 */
			if(einKunde == null)
				einKunde = datenService.erstelleKunde();
			einKunde.setName(name);
			einKunde.setNummer(nummer);
			datenService.einfuegeKunde(einKunde);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int getNaechsteKundenNr()
	{
		try
		{
			return datenService.getNaechsteKundenNr();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getKundeZuNr(int nummer)
	{
		try
		{
			einKunde = datenService.getKundeZuNr(nummer);
			if(einKunde==null)
				return "";
			return einKunde.getName();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void endeAnwendung()
	{
		try
		{
			datenService.endeAnwendung();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
}
