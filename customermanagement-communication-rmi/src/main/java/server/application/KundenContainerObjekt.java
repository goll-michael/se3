package server.application;

/** Container zum Verwalten von Anzeigen und Kunden **/

import java.io.Serializable;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import server.persistence.ObjektSpeicher;
import server.schnittstelle.KundeI;
import server.schnittstelle.KundenContainerObjektI;

public class KundenContainerObjekt implements KundenContainerObjektI, Serializable
{
	/**
	 * Beim Serialisieren eines Objektes wird auch die serialVersionUID der zugehoerigen
	 * Klasse mit in die Ausgabedatei geschrieben. Soll das Objekt spaeter deserialisiert
	 * werden, so wird die in der Datei gespeicherte serialVersionUID mit der aktuellen
	 * serialVersionUID des geladenen .class-Files verglichen. Stimmen beide nicht
	 * ueberein, so gibt es eine Ausnahme des Typs InvalidClassException, und der
	 * Deserialisierungsvorgang bricht ab.
	 */
	private static final long serialVersionUID = 1L;

	// Attribute
	private ObjektSpeicher einObjektSpeicher;

	// Name der Datei, in der die Daten abgespeichert werden.
	private ArrayList<KundeI> meineKunden;
	private ArrayList<Remote> exportierteObjekte;

	// Konstruktor -private!
	public KundenContainerObjekt() throws RemoteException
	{
		super();
		init();
	}
	
	@SuppressWarnings("unchecked")
	private void init() throws RemoteException
	{
		exportierteObjekte = new ArrayList<Remote>();
		einObjektSpeicher = new ObjektSpeicher();
		try
		{
			meineKunden = (ArrayList<KundeI>) einObjektSpeicher.leseObjekt();
			
			// Alle Kunden exportieren
			for (int i = 0; i < meineKunden.size(); i++)
				meineKunden.set(i, (KundeI) exportiereRemoteObject(meineKunden.get(i)));
		} catch (Exception e)
		{
			meineKunden = new ArrayList<KundeI>();
			System.out.println("Es wurde eine neue Datenbasis angelegt");
		}
	}

	@Override
	public KundeI erstelleKunde() throws RemoteException 
	{
		KundeI kunde = new Kunde();
		exportiereRemoteObject(kunde);
		return kunde;
	}
	
	public void einfuegeKunde(KundeI einKunde) throws RemoteException
	{
		if (!meineKunden.contains(einKunde))
		{
			meineKunden.add(einKunde);// hinten anfuegen
		}
	}
	
	public int getNaechsteKundenNr() throws RemoteException
	{
		int max = 0;
		Iterator<KundeI> iter = meineKunden.iterator();
		while (iter.hasNext())
		{
			KundeI kunde = iter.next();
			max = Math.max(max, kunde.getNummer());
		}
		return max + 1;
	}

	public KundeI getKundeZuNr(int nummer) throws RemoteException
	{
		Iterator<KundeI> iter = meineKunden.iterator();
		while (iter.hasNext())
		{
			KundeI kunde = iter.next();
			if (kunde.getNummer() == nummer)
				return kunde;
		}
		return null;
	}

	public void endeAnwendung() throws RemoteException
	{
		for (int i = 0; i < meineKunden.size(); i++)
		{
			KundeI kunde = getKunde(meineKunden.get(i));
			if (kunde != null)
				meineKunden.set(i, kunde);
		}

		einObjektSpeicher.speichereObjekt(meineKunden);

		// Alle Remote-Objekte aufraeumen.
		System.out.println("Systemressourcen werden freigegeben...");
		Iterator<Remote> itR = exportierteObjekte.iterator();
		while (itR.hasNext())
		{
			try
			{
				UnicastRemoteObject.unexportObject(itR.next(), true);
			}
			catch (NoSuchObjectException e)
			{
				e.printStackTrace();
			}
			itR.remove();
		}
		System.out.println("Datenbasis wurde gespeichert");
	}
	
	private Remote exportiereRemoteObject(Remote remoteObj)
	{
		Remote stub = null;
		if (exportierteObjekte.contains(remoteObj))
		{
			try
			{
				stub = RemoteObject.toStub(remoteObj);
			}
			catch (NoSuchObjectException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				stub = UnicastRemoteObject.exportObject(remoteObj, 1099);
			}
			catch (RemoteException e)
			{
				e.printStackTrace();
			}
			exportierteObjekte.add(remoteObj);
		}
		return stub;
	}

	private KundeI getKunde(Remote stub)
	{
		Iterator<Remote> it = exportierteObjekte.iterator();
		while (it.hasNext())
		{
			Remote r = it.next();
			try
			{
				if (stub.equals(r) || stub.equals(RemoteObject.toStub(r)))
				{
					return (KundeI) r;
				}
			}
			catch (NoSuchObjectException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
}