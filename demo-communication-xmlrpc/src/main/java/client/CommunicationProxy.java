package client;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import common.CustomerContainerI;

public class CommunicationProxy implements CustomerContainerI
{
	private static CommunicationProxy serverProxy = null;
	
	private XmlRpcClient xmlRpcClient;
    
	public CommunicationProxy()
	{
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
			config.setServerURL(new URL("http://127.0.0.1:18080/xmlrpc"));
		} catch (MalformedURLException e) {
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

	public static CommunicationProxy getReference()
	{
		if (serverProxy == null)
			serverProxy = new CommunicationProxy();
		return serverProxy;
	}
	
	public void create(String name, int number) 
	{
		int nr = Integer.valueOf(number);
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

	public int getNextNumber() 
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
	
	public String getCustomerName(int nummer) 
	{
		Object[] params = new Object[]{ nummer };
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

	public void close() 
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
