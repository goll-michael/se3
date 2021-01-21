package server.schnittstelle;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KundeI extends Remote
{
	public String getName() throws RemoteException;
	
	public int getNummer() throws RemoteException;

	public void setName(String name) throws RemoteException;

	public void setNummer(int nummer) throws RemoteException;
}