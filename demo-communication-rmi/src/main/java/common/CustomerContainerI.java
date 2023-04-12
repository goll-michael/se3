package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CustomerContainerI extends Remote
{
	public void create(String name, int nummer) throws RemoteException;
	
	public int getNextNumber() throws RemoteException;
	
	public String getCustomerName(int nummer) throws RemoteException;

	public void close() throws RemoteException;
}