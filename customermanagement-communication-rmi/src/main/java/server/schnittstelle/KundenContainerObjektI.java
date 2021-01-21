package server.schnittstelle;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Schnittstelle fuer die Uebertragung von komplexen Datentypen
 */
public interface KundenContainerObjektI extends Remote
{
	public KundeI erstelleKunde() throws RemoteException;
	
	public void einfuegeKunde(KundeI einKunde) throws RemoteException;
	
	public int getNaechsteKundenNr() throws RemoteException;
	
	public KundeI getKundeZuNr(int nummer) throws RemoteException;

	public void endeAnwendung() throws RemoteException;
}