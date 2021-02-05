package server.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import server.schnittstelle.fachkonzept.Anzeige;
import server.schnittstelle.fachkonzept.Kunde;
import server.schnittstelle.geschaeftslogik.AnzeigenverwaltungLocal;

/**
 * Managed Bean f�r die JSF-Webanwendung
 * Sie benutzt die lokale Schnittstelle der EJB Anzeigenverwaltung,
 * um auf deren Dienste zur�ckzugreifen.
 *
 */
@Named
@SessionScoped
public class AnzeigeWebBean implements Serializable
{
	/*
	 * Dependency Injection: Lokales Business Interface der Session Bean Anzeigenverwaltung
	 * Alternativ: JNDI-Lookup
	 * anzeigenverwaltung = (AnzeigenverwaltungLocal) ctx.lookup("avWEB-Server-ear/Anzeigenverwaltung/local");	
	 */
	@EJB
	private AnzeigenverwaltungLocal anzeigenverwaltung;
	
	private Long kundennummer;
	private Anzeige aktAnzeige;
	private List<Anzeige> anzeigenListe;
	private List<Kunde> kundenListe;
	
	public AnzeigeWebBean()
	{
	}
	
	public String starteAssistenten()
	{
		this.aktAnzeige = anzeigenverwaltung.erstelleAnzeige();
		return "START";
	}
	
	public Anzeige getAktAnzeige()
	{	
		return aktAnzeige;
	}

	public void setAktAnzeige(Anzeige aktAnzeige)
	{
		this.aktAnzeige = aktAnzeige;
	}		
	
	public Long getKundennummer()
	{
		return kundennummer;
	}

	public void setKundennummer(Long kundennummer)
	{
		this.kundennummer = kundennummer;
	}
	
	public String pruefeKundennummer()
	{
		Kunde kunde = anzeigenverwaltung.getKundeZuNr(this.kundennummer);
		if(kunde != null)
		{
			aktAnzeige.setKunde(kunde);
			return "KUNDENDATEN";
		}
		else
		{
			String meldung = "Ein Kunde mit dieser Nummer existiert nicht!";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, meldung, meldung));	
			return null;
		}
	}

	public List<Anzeige> getAnzeigenListe()
	{
		if(anzeigenListe == null)
			aktualisiereListen();
		return anzeigenListe;
	}
	
	public List<Kunde> getKundenListe()
	{
		if(kundenListe == null)
			aktualisiereListen();
		return kundenListe;
	}	
	
	public String speichereAnzeige()
	{
		// ZUERST Kunden in der Datenbank speichern, damit die Anzeige darauf verweisen kann
		if(aktAnzeige.getKunde().getAgbBestaetigt() == null)
			aktAnzeige.getKunde().setAgbBestaetigt(new Date());	
		anzeigenverwaltung.speichereKunde(aktAnzeige.getKunde());
		anzeigenverwaltung.speichereAnzeige(aktAnzeige);		
		aktualisiereListen();
		return "ANZEIGENLISTE";
	}
	
	public String entferneAnzeige(String anzeigenummer)
	{
		Anzeige anzeige = anzeigenverwaltung.getAnzeigeZuNr(Long.valueOf(anzeigenummer));		
		anzeigenverwaltung.entferneAnzeige(anzeige);
		aktualisiereListen();
		return "ANZEIGENLISTE";
	}
	
	public String entferneKunde(String kundennummer)
	{
		Kunde kunde = anzeigenverwaltung.getKundeZuNr(Long.valueOf(kundennummer));	
		anzeigenverwaltung.entferneKunde(kunde);
		aktualisiereListen();
		return "KUNDENLISTE";
	}	
	
	public String aktualisiereListen()
	{
		this.anzeigenListe = anzeigenverwaltung.getAnzeigen();
		this.kundenListe = anzeigenverwaltung.getKunden();
		return null;
	}
	
	public String neuerKunde()
	{
		aktAnzeige.setKunde(anzeigenverwaltung.erstelleKunde());
		return "KUNDENDATEN";
	}
	
	public String neueAnzeige()
	{	
		return "ANZEIGENDATEN";
	}
	
	public String zeigeAnzeigenListe()
	{
		return "ANZEIGENLISTE";
	}
	
	public String zeigeKundenListe()
	{
		return "KUNDENLISTE";
	}	
	
	public String beendeAssistenten()
	{
		return "STARTSEITE";
	}
	
	public String zeigeStartseite()
	{
		return "STARTSEITE";
	}	
}
