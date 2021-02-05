package server.webservice;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import server.schnittstelle.fachkonzept.Anzeige;
import server.schnittstelle.fachkonzept.Kunde;
import server.schnittstelle.geschaeftslogik.AnzeigenverwaltungLocal;

/**
 * Web Service zum Erstellen einer neuen Anzeige
 */
@Stateless
@WebService
public class AnzeigenService {
	 
	/*
	 * Dependency Injection: Lokales Business Interface der Session Bean Anzeigenverwaltung
	 * Alternativ: JNDI-Lookup
	 * anzeigenverwaltung = (AnzeigenverwaltungLocal) ctx.lookup("avWEB-Server-earET/Anzeigenverwaltung/local");	
	 */
	@EJB
	private AnzeigenverwaltungLocal anzeigenverwaltung;
	
	public AnzeigenService()
	{
	}
	
	@WebMethod
	public String erstelleAnzeige(
			@WebParam(name="kundennummer") String kundennummer, 
			@WebParam(name="rubrik") String rubrik, 
			@WebParam(name="titel") String titel, 
			@WebParam(name="beschreibung") String beschreibung, 
			@WebParam(name="preis") String preis)
	{
		try
		{
			Kunde kunde = anzeigenverwaltung.getKundeZuNr(Long.valueOf(kundennummer));
			if(kunde == null)
				return "Fehler: Kunde nicht gefunden!";
			Anzeige anzeige = anzeigenverwaltung.erstelleAnzeige();
			anzeige.setRubrik(rubrik);
			anzeige.setTitel(titel);
			anzeige.setBeschreibung(beschreibung);
			anzeige.setPreis(Integer.valueOf(preis));
			anzeige.setKunde(kunde);
			anzeigenverwaltung.speichereAnzeige(anzeige);
			return "Anzeige erstellt";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Beim Speichern der Anzeige ist ein Fehler aufgetreten!";
		}
	}
	
}
