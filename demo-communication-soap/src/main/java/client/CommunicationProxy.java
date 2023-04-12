package client;

import common.CustomerContainerI;

public class CommunicationProxy implements CustomerContainerI
{
	private static CommunicationProxy serverProxy = null;
	
	private KundenanfragenManagerService kms;
	private Kundenverwaltung kv;
	
	public CommunicationProxy()
	{
		kms = new KundenanfragenManagerService();
		kv = kms.getKundenverwaltungPort();
	}

	public static CommunicationProxy getReference()
	{
		if (serverProxy == null)
			serverProxy = new CommunicationProxy();
		return serverProxy;
	}

	public void create(String name, int number) 
	{
		try
		{
			kv.einfuegeKunde(name, number);			
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

	public int getNextNumber() 
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
	
	public String getCustomerName(int number) 
	{
		try
		{
			return kv.getKundeZuNr(number);			
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

	public void close() 
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
