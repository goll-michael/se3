package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.CustomerContainerI;

public class CommunicationProxy implements CustomerContainerI
{
	private static CommunicationProxy communicationProxy = null;
	
	private static final String RMI_SERVICE_NAME = "customermanagement";
	private static CustomerContainerI customerContainer;
	
	public CommunicationProxy()
	{
		try
		{	
			Registry registry = LocateRegistry.getRegistry();	
			customerContainer = (CustomerContainerI) registry.lookup(RMI_SERVICE_NAME);
		}
		catch (Exception e)
		{
			System.out.println(e.getStackTrace());
		}
	}

	public static CommunicationProxy getReference()
	{
		if (communicationProxy == null)
			communicationProxy = new CommunicationProxy();
		return communicationProxy;
	}
	
	public void create(String name, int number) 
	{
		try {
			customerContainer.create(name,  number);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getNextNumber() 
	{
		try {
			return customerContainer.getNextNumber();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public String getCustomerName(int number) 
	{
		try {
			return customerContainer.getCustomerName(number);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public void close() 
	{
		try {
			customerContainer.close();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
