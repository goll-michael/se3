package server.application;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import server.schnittstelle.fachkonzept.Anzeige;
import server.schnittstelle.fachkonzept.Kunde;
import server.schnittstelle.geschaeftslogik.AnzeigenverwaltungLocal;
import server.schnittstelle.geschaeftslogik.AnzeigenverwaltungRemote;

/**
 * Stateless Session Bean, die als Session Facade für Client-Anwendungen dient
 * und Dienste der SLSBs KundenBean und AnzeigenBean nutzt
 */
@Stateless
public class Anzeigenverwaltung implements
	AnzeigenverwaltungRemote, AnzeigenverwaltungLocal
{
	private static final long serialVersionUID = 8129863993685959657L;
	
	/*
	 * Dependency Injection: Lokales Business Interface der Session Bean KundenBean
	 * Alternativ: JNDI-Lookup
	 * kundenBean = (KundenBeanLocal) ctx.lookup("avWEB-Server-ear/KundenBean/local");	
	 */
	@EJB
	private KundenBeanLocal kundenBean;
	
	/*
	 * Dependency Injection: Lokales Business Interface der Session Bean AnzeigenBean
	 * Alternativ: JNDI-Lookup
	 * anzeigenBean = (AnzeigenBeanLocal) ctx.lookup("avWEB-Server-ear/AnzeigenBean/local");
	 */
	@EJB
	private AnzeigenBeanLocal anzeigenBean;
	
	public Anzeigenverwaltung()
	{
	}
	
	/**
	 * Methode zum Erstellen eines neuen Kunden. Dem Kunden wird dabei automatisch die nächste freie Kundennummer zugewiesen.
	 */
	public Kunde erstelleKunde()
	{
		Kunde kunde = new Kunde();	
		kunde.setNummer(kundenBean.getNaechsteKundenNr());
		return kunde;
	}
	
	/**
	 * Methode zum Speichern eines Kunden in der Datenbank.
	 */
	public void speichereKunde(Kunde einKunde)
	{
		kundenBean.speichereKunde(einKunde);
	}

	/**
	 * Methode zum Löschen eines Kunden aus der Datenbank.
	 */
	public void entferneKunde(Kunde einKunde)
	{	
		kundenBean.entferneKunde(einKunde);
	}

	/**
	 * Suche eines Kunden anhand seiner Kundennummer.
	 */
	public Kunde getKundeZuNr(long nummer)
	{	
		return kundenBean.getKundeZuNr(nummer);
	}
	
	/**
	 * Liefert eine Liste aller gespeicherten Kunden.
	 */
	public List<Kunde> getKunden()
	{
		return kundenBean.getKundenListe();
	}
	
	/**
	 * Erstellt eine neue Anzeige und weist dieser dabei die nächste freie Anzeigennummer zu.
	 */
	public Anzeige erstelleAnzeige()
	{		
		Anzeige anzeige = new Anzeige();		
		anzeige.setNummer(anzeigenBean.getNaechsteAnzeigenNr());
		return anzeige;
	}
	
	/**
	 * Speichert eine Anzeige in der Datenbank.
	 */
	public void speichereAnzeige(Anzeige eineAnzeige)
	{	
		anzeigenBean.speichereAnzeige(eineAnzeige);
	}

	/**
	 * Löscht eine Anzeige aus der Datenbank.
	 */
	public void entferneAnzeige(Anzeige eineAnzeige)
	{	
		anzeigenBean.entferneAnzeige(eineAnzeige);
	}

	/**
	 * Sucht eine Anzeige anhand ihrer Nummer.
	 */
	public Anzeige getAnzeigeZuNr(long nummer)
	{	
		return anzeigenBean.getAnzeigeZuNr(nummer);
	}	
	
	/**
	 * Liefert eine Liste aller gespeicherter Anzeigen.
	 */
	public List<Anzeige> getAnzeigen()
	{		
		return anzeigenBean.getAnzeigenListe();
	}		
	
	/**
	 * Diese Methode wird ausgeführt, bevor die SLSB zerstört wird. 
	 * In diesem Fall ist keine Aktion durchzuführen, da die SLSB keine besonderen Ressourcen reserviert.
	 */
	@Remove
	public String endeAnwendung()
	{		
		return null;
	}
}
