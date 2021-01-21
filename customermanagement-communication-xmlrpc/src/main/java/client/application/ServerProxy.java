package client.application;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import server.schnittstelle.KundenContainerEinfachI;

// ServerProxy stellt für die GUI-klassen die Verbindung zum Server her
public class ServerProxy implements KundenContainerEinfachI
{
	//Singleton-Muster
	private static ServerProxy serverProxy = null;
	
	private XmlRpcClient xmlRpcClient;
	public ServerProxy() 
	{
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
			config.setServerURL(new URL("http://127.0.0.1:18080/xmlrpc"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        config.setEnabledForExtensions(false);  
        config.setConnectionTimeout(60 * 1000);
        config.setReplyTimeout(60 * 1000);

        xmlRpcClient = new XmlRpcClient();
      
        // use Commons HttpClient as transport
        xmlRpcClient.setTransportFactory(
            new XmlRpcCommonsTransportFactory(xmlRpcClient));
        // set configuration
        xmlRpcClient.setConfig(config);
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
	
	public void einfuegeKunde(String name, int nummer) 
	{
		int nr = Integer.valueOf(nummer);
		Object[] params = new Object[]{ name, nr };
        try
		{
			xmlRpcClient.execute("AnfragenBehandler.einfuegeKunde", params);
		}
		catch (XmlRpcException e)
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
			return (Integer)xmlRpcClient.execute("AnfragenBehandler.getNaechsteKundenNr", new Object[]{});
		}
		catch (XmlRpcException e)
		{
			e.printStackTrace();
		}
        return 0;
	}
	
	public String getKundeZuNr(int kundenNr) 
	{
		Object[] params = new Object[]{ kundenNr };
        try
		{
			String kundenName = 
				(String)xmlRpcClient.execute("AnfragenBehandler.getKundeZuNr", params);
			/*
			 * Server liefert leer statt "" zurueck.
			 * "" als Rueckgabewert in xmlrpc nicht erlaubt
			 */
			if(kundenName.isEmpty()) 
				return "";
			return kundenName;
		}
		catch (XmlRpcException e)
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

	public void endeAnwendung() 
	{
		try
		{
			xmlRpcClient.execute("AnfragenBehandler.endeAnwendung", new Object[]{});
		}
		catch (XmlRpcException e)
		{
			e.printStackTrace();
		}
	}
}
