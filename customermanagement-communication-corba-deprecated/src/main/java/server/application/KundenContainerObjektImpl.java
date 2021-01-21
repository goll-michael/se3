package server.application;

import java.util.ArrayList;
import java.util.Iterator;
import server.persistence.ObjektSpeicher;
import server.schnittstelle.Kunde;
import server.schnittstelle.KundenContainerObjektPOA;

/** Container zum Verwalten von Anzeigen und Kunden **/

public class KundenContainerObjektImpl extends KundenContainerObjektPOA {
	private ObjektSpeicher einObjektSpeicher;
	private ArrayList<Kunde> meineKunden;
	
	public KundenContainerObjektImpl()
	{
		super();
		init();
	}

	@SuppressWarnings("unchecked")
	private void init()
	{
		einObjektSpeicher = new ObjektSpeicher();
		try
		{
			meineKunden = (ArrayList<Kunde>) einObjektSpeicher.leseObjekt();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (meineKunden == null)
		{
			meineKunden = new ArrayList<Kunde>();
			System.out.println("Es wurde eine neue Datenbasis angelegt");
		}
	}
	
	@Override
	public Kunde erstelleKunde()
	{
		Kunde einKunde = new Kunde();
		return einKunde;
	}
	
	@Override
	public void einfuegeKunde(Kunde einKunde)
	{
		//Pruefen, ob der Kunde bereits vorhanden ist
		if(getKundeZuNr(einKunde.nummer) == null)
		{
			//Kunde noch nicht vorhanden
			meineKunden.add(einKunde);
		}
		else
		{
			/*
			 * Kunde ist bereits vorhanden und soll ueberschrieben werden,
			 * daher ArrayList durchsuchen, Kunde auslesen und Werte entsprechend aendern.
			 */
			Iterator<Kunde> iter = meineKunden.iterator();
			while (iter.hasNext()) 
			{
				Kunde kunde = iter.next();
				if(kunde.nummer == einKunde.nummer)
					kunde.name = einKunde.name;
			}
		}
	}

	@Override
	public int getNaechsteKundenNr()
	{
		int max = 0;
		Iterator<Kunde> iter = meineKunden.iterator();
		while (iter.hasNext())
		{
			Kunde kunde = iter.next();
			max = Math.max(max, kunde.nummer);
		}
		return max + 1;
	}
	
	@Override
	public Kunde getKundeZuNr(int nummer)
	{
		for(int i=0;i<meineKunden.size();i++)
			if(meineKunden.get(i).nummer==nummer)
				return meineKunden.get(i);
		return null;
	}

	
	public void endeAnwendung()
	{
		System.out.println("jetzt speichern:");
		for(int i=0;i<meineKunden.size();i++)
			System.out.println("####"+meineKunden.get(i).name);
		
		einObjektSpeicher.speichereObjekt(meineKunden);
		System.out.println("Datenbasis wurde gespeichert");
	}
}