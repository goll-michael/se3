package client.application;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import server.schnittstelle.Kunde;
import server.schnittstelle.KundenContainerEinfachI;
import server.schnittstelle.KundenContainerObjekt;
import server.schnittstelle.KundenContainerObjektHelper;

public class ServerProxy implements KundenContainerEinfachI
{
	//Singleton-Muster
	private static ServerProxy serverProxy = null;
	
	private static KundenContainerObjekt datenService;
	private static Kunde einKunde = null;
	
	public ServerProxy()
	{
		try
		{				
			//wird bei der Initialisierung benoetigt
			String args[]=null;
			
			//den Object-Request-Broker initialisieren
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
            
            NamingContextExt nc = NamingContextExtHelper.narrow(
            		orb.resolve_initial_references("NameService"));

            datenService=KundenContainerObjektHelper.narrow(
            		nc.resolve(nc.to_name("einKundeContainer")));
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
			try
			{
				einKunde = datenService.getKundeZuNr(nummer);
			}
			catch (org.omg.CORBA.SystemException se)
			{
				/*
				 * Eine serverseitige NullPointerException kann geworfen werden.
				 * Dies wird hier abgefangen.
				 */
				einKunde = datenService.erstelleKunde();
			}
		
			/*
			 * Referenz eines neuen Kundenobjekts wird abgefragt.
			 * Bei RMI und bei CORBA werden Kundenobjekte ueber das
			 * Netzwerk uebertragen.
			 */		
			einKunde.setName(name);
			einKunde.setNummer(nummer);
			datenService.einfuegeKunde(einKunde);
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

	@Override
	public int getNaechsteKundenNr()
	{
        try
		{
        	return datenService.getNaechsteKundenNr();
		}
		catch (Exception e)
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
			try
			{
				einKunde = datenService.getKundeZuNr(nummer);
			}
			catch (org.omg.CORBA.SystemException se)
			{
				/*
				 * Eine serverseitige NullPointerException kann geworfen werden.
				 * Dies wird hier abgefangen.
				 */
				return "";
			}
			return einKunde.getName();
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
			datenService.endeAnwendung();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
