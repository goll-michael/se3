package application;

/**
 * Container zum Verwalten von Kunden.
 * Im Konstruktor werden bereits vorhandene Kunden ausgelesen
 * und in eine ArrayList gespeichert. Erst beim Beenden der
 * Anwendung werden die Kunden der ArrayList persistent gespeichert.
**/

import java.util.*;

import persistence.ObjektSpeicher;

public class KundenContainer
{
	//Attribute
	private ObjektSpeicher eineObjektDatei;

	//Singleton-Muster
	private static KundenContainer einKundeContainer = null;

	//Verwaltung der Kunden
	private ArrayList<Kunde> meineKunden = new ArrayList<Kunde>();
  
	/**
	 * @SuppressWarnings("unchecked") unterdrueckt bestimmte Compiler-Warnungen.
	 * Dies ist notwendig, da der Cast zu "ArrayList<Kunde>" nicht geprueft ist.
	 */
	@SuppressWarnings({ "unchecked" })
	private KundenContainer()
	{
		/*
		 * Gespeicherte Daten einlesen.
		 * Falls noch keine Daten gespeichert wurden, kann keine
		 * Datei gelesen werden, es gibt dann eine Ausnahme
		 */ 
		eineObjektDatei = new ObjektSpeicher();
		try
		{
			meineKunden = (ArrayList<Kunde>)eineObjektDatei.leseObjekt();
			if(meineKunden == null)
				meineKunden = new ArrayList<Kunde>();
		}
		catch (Exception e)
		{
			/*
			 * Wenn keine Daten gelesen werden konnten, muss eine
			 * neue Datenbasis angelegt werden
			 */
			System.out.println("Es wurde eine neue Datenbasis angelegt");
			meineKunden = new ArrayList<Kunde>();
		}
	}

	/*
	 * Klassen-Operation, die die Objektreferenz liefert.
	 * Wenn das Objekt noch nicht vorhanden ist, dann wird es erzeugt
	 */
	public static KundenContainer getObjektreferenz()
	{
		if (einKundeContainer == null)
			einKundeContainer = new KundenContainer();
		return einKundeContainer;
	}
   
	public void einfuegeKunde(Kunde einKunde)
	{
		if(!meineKunden.contains(einKunde)) 
			meineKunden.add(einKunde); //Am Ende einfuegen
	}
	
	public int getNaechsteKundenNr()
	{
		int max = 0;
		Iterator<Kunde> iter = meineKunden.iterator();
		while (iter.hasNext()) 
		{
			Kunde kunde = iter.next();
			max = Math.max(max, kunde.getNummer());
		}
		return max + 1;
	}

	//Liefert den Kunden mit der angegebenen Nummer
	public Kunde getKundeZuNr(int nummer)
	{
		Iterator<Kunde> iter = meineKunden.iterator();
		while (iter.hasNext()) 
		{
			Kunde kunde = iter.next();
			if(kunde.getNummer() == nummer)
				return kunde;
		}
		return null;
	}
  
	//Methode zum Speichern der Daten
	public void endeAnwendung()
	{
		eineObjektDatei.speichereObjekt(meineKunden);
		System.out.println("Datenbasis wurde gespeichert");
	} 
	}