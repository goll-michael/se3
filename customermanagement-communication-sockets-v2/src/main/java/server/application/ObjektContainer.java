package server.application;

/** Container zum Verwalten von Anzeigen und Kunden **/
import java.util.*;

import server.persistence.ObjektSpeicher;

public class ObjektContainer
{
	// Attribute
	private ObjektSpeicher eineObjektDatei;
	// Singleton-Muster
	private static ObjektContainer einObjektContainer = null;
	// Name der Datei, in der die Daten abgespeichert werden.
	private String datei = "Datenbasis.xml";
	private ArrayList<Kunde> meineKunden = new ArrayList<Kunde>();

	// Konstruktor -private!
	private ObjektContainer()
	{
		// fuer BlueJ
		Thread.currentThread().setContextClassLoader(
				getClass().getClassLoader());
		// Gespeicherte Daten einlesen
		// Falls noch keine Daten gespeichert wurden, kann keine
		// Datei gelesen werden, es gibt dann eine Ausnahme
		eineObjektDatei = new ObjektSpeicher(datei);
		try
		{
			meineKunden = (ArrayList) eineObjektDatei.leseObjekt();
			if (meineKunden == null)
				meineKunden = new ArrayList();
		}
		catch (Exception e)
		{
			// wenn keine Daten gelesen werden konnten, muss eine
			// neue Datenbasis angelegt werden
			System.out.println("Es wurde eine neue Datenbasis angelegt");
			meineKunden = new ArrayList();
		}
	}

	// Klassen-Operation, die die Objektreferenz liefert
	// Wenn Objekt noch nicht vorhanden, dann wird es erzeugt
	public static ObjektContainer getObjektreferenz()
	{
		if (einObjektContainer == null)
		{
			einObjektContainer = new ObjektContainer();
		}
		return einObjektContainer;
	}

	public int getKundenzahl()
	{
		return meineKunden.size();
	}

	public int getNaechsteKundenNr()
	{
		int max = 0;
		Iterator<Kunde> iter = iteratorKunden();
		while (iter.hasNext())
		{
			Kunde kunde = iter.next();
			max = Math.max(max, kunde.getNummer());
		}
		return max + 1;
	}

	// Methoden fuer Kunden------------------------------
	public void einfuegeKunde(Kunde eineKunde)
	{
		if (!meineKunden.contains(eineKunde))
		{
			meineKunden.add(eineKunde);// hinten anfuegen
		}
	}

	public Iterator<Kunde> iteratorKunden()
	{
		return meineKunden.iterator();
	}

	public void entferneKunde(Kunde einKunde)
	{
		meineKunden.remove(einKunde);
	}

	// liefert Kunde an angegebener Position
	public Kunde getKundeAnPos(int position)
	{
		if (position < getKundenzahl())
		{
			return meineKunden.get(position);
		}
		return null;
	}

	// liefert Kunden mit der angegebenen Nummer zurück
	public Kunde getKundeZuNr(int nummer)
	{
		Iterator<Kunde> iter = iteratorKunden();
		while (iter.hasNext())
		{
			Kunde kunde = iter.next();
			if (kunde.getNummer() == nummer)
				return kunde;
		}
		return null;
	}

	// Methode zum Speichern der Daten ------------------
	public void endeAnwendung()
	{
		eineObjektDatei.speichereObjekt(meineKunden);
		System.out.println("Datenbasis wurde gespeichert");
	}
}