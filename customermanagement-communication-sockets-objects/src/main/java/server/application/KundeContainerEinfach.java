package server.application;

/**
 * Container zum Verwalten von Kunden.
 * Im Konstruktor werden bereits vorhandene Kunden ausgelesen
 * und in eine ArrayList gespeichert. Erst beim Beenden der
 * Anwendung werden die Kunden der ArrayList persistent gespeichert.
**/

import java.util.*;

import server.persistence.ObjektSpeicher;
import server.schnittstelle.Kunde;
import server.schnittstelle.KundeContainerEinfachI;

public class KundeContainerEinfach implements KundeContainerEinfachI
{
	//Attribute
	private ObjektSpeicher eineObjektDatei;

	//Singleton-Muster
	private static KundeContainerEinfach einKundeContainer = null;

	//Verwaltung der Kunden
	private ArrayList<Kunde> meineKunden = new ArrayList<Kunde>();
  
	/**
	 * @SuppressWarnings("unchecked") unterdrueckt bestimmte Compiler-Warnungen.
	 * Dies ist notwendig, da der Cast zu "ArrayList<Kunde>" nicht geprueft ist.
	 */
	@SuppressWarnings({ "unchecked" })
	private KundeContainerEinfach()
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
	public static KundeContainerEinfach getObjektreferenz()
	{
		if (einKundeContainer == null)
			einKundeContainer = new KundeContainerEinfach();
		return einKundeContainer;
	}
   
	/*
	 * Die Schnittstelle KundeContainerEinfachI besitzt nur einfache Datentypen
	 * fuer die Kommunikation. Die Klasse KundeContainerEinfach (genauso wie
	 * die Klasse ServerProxy) implementieren diese Schnittstelle. Da die Methode
	 * getKundeZuNr keine Referenz auf ein Kundenobjekt zurueckgibt, welches
	 * bearbeitet werden koennte, muss die ArrayList noch einmal durchlaufen werden. 
	 */
	public void einfuegeKunde(String name, int nummer)
	{
		//Pruefen, ob der Kunde bereits vorhanden ist
		if(getKundeZuNr(nummer).equals(""))
		{
			//Kunde noch nicht vorhanden
			Kunde einKunde = new Kunde();
			einKunde.setName(name);
			einKunde.setNummer(nummer);
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
				if(kunde.getNummer() == nummer)
					kunde.setName(name);
			}
		}
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
	public String getKundeZuNr(int nummer)
	{
		Iterator<Kunde> iter = meineKunden.iterator();
		while (iter.hasNext()) 
		{
			Kunde kunde = iter.next();
			if(kunde.getNummer() == nummer)
				return kunde.getName();
		}
		return "";
	}
  
	//Methode zum Speichern der Daten
	public void endeAnwendung()
	{
		eineObjektDatei.speichereObjekt(meineKunden);
		System.out.println("Datenbasis wurde gespeichert");
	}

}