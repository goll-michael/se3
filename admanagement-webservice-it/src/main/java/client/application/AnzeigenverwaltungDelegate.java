package client.application;

import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import server.schnittstelle.fachkonzept.Anzeige;
import server.schnittstelle.fachkonzept.Kunde;
import server.schnittstelle.geschaeftslogik.AnzeigenverwaltungRemote;

/**
 * Delegations-Klasse: Clientseitige Abstraktion der Geschäftslogik
 * Setzt das J2EE-Muster Business Delegate um.
 */
public class AnzeigenverwaltungDelegate
{

	private static AnzeigenverwaltungDelegate instance = null; 	// Singleton-Instanz
	private AnzeigenverwaltungRemote anzeigenverwaltung; 		// Referenz zur Remote-EJB
	private InitialContext ctx;
	
	// Lokale Listen der Kunden und Anzeigen zur Verringerung der Remote-Zugriffe 
	// -> Caching-Funktion des Business Delegate-Musters
	private List<Kunde> kundenListe;
	private List<Anzeige> anzeigenListe;
	
	private AnzeigenverwaltungDelegate()
	{
		try {	
			/*
			 * Erfolgt der Konstruktor-Aufruf der Klasse InitialContext ohne Parameter
			 * werden automatisch die Informationen aus der Konfigurationsdatei jndi.properties verwendet.
			 */
			ctx = new InitialContext();
			
			/*
			 * JNDI-Lookup der Session Bean Anzeigenverwaltung über ihr Remote-Interface
			 * Anzeigenverwaltung ist in server.application vorhanden
			 */
			anzeigenverwaltung =
				(AnzeigenverwaltungRemote) ctx.lookup("ejb:amWS-IT-Server-ear/amWS-IT-Server-jar/Anzeigenverwaltung!server.schnittstelle.geschaeftslogik.AnzeigenverwaltungRemote");
		}
		catch(NamingException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode für den Zugriff auf die Singleton-Instanz
	 * @return Aktuelle Instanz der Delegations-Klasse
	 */
	public static AnzeigenverwaltungDelegate getInstance()
	{
		if(instance == null)
			instance = new AnzeigenverwaltungDelegate();
		return instance;
	}

	public void entferneAnzeige(Anzeige anzeige) throws Exception {
		try
		{
			anzeigenverwaltung.entferneAnzeige(anzeige);
			aktualisiereListen();
		}
		catch(Exception e)
		{
			throw new Exception("Beim Loeschen der Anzeige ist ein Fehler aufgetreten!");
		}
	}

	public void entferneKunde(Kunde einKunde) throws Exception {
		try
		{
			anzeigenverwaltung.entferneKunde(einKunde);
			aktualisiereListen();
		}
		catch(Exception e)
		{
			throw new Exception("Beim Loeschen des Kunden ist ein Fehler aufgetreten!");
		}		
	}

	public Anzeige erstelleAnzeige() throws Exception {
		try
		{
			return anzeigenverwaltung.erstelleAnzeige();
		}
		catch(Exception e)
		{
			throw new Exception("Beim Erstellen der Anzeige ist ein Fehler aufgetreten!");
		}
	}

	public Kunde erstelleKunde() throws Exception {
		try
		{
			return anzeigenverwaltung.erstelleKunde();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("Beim Erstellen des Kunden ist ein Fehler aufgetreten!");
		}
	}

	public Anzeige getAnzeigeZuNr(long nummer) throws Exception {
		try
		{
			return anzeigenverwaltung.getAnzeigeZuNr(nummer);
		}
		catch(Exception e)
		{
			throw new Exception("Beim Abfragen der Anzeige ist ein Fehler aufgetreten!");
		}
	}

	public List<Anzeige> getAnzeigen() throws Exception {
		if(anzeigenListe == null)
			aktualisiereListen();
		return anzeigenListe;
	}

	public int getAnzeigenzahl() throws Exception {
		return getAnzeigen().size();
	}

	public Kunde getKundeZuNr(long nummer) throws Exception {
		try
		{
			return anzeigenverwaltung.getKundeZuNr(nummer);
		}
		catch(Exception e)
		{
			throw new Exception("Beim Abfragen des Kunden ist ein Fehler aufgetreten!");
		}
	}

	public List<Kunde> getKunden() throws Exception {
		if(kundenListe == null)
			aktualisiereListen();
		return kundenListe;
	}

	public int getKundenzahl() throws Exception {
		return getKunden().size();
	}

	public void speichereAnzeige(Anzeige eineAnzeige) throws Exception {
		try
		{
			anzeigenverwaltung.speichereAnzeige(eineAnzeige);
			aktualisiereListen();
		}
		catch(Exception e)
		{
			throw new Exception("Beim Speichern der Anzeige ist ein Fehler aufgetreten!");
		}
	}

	public void speichereKunde(Kunde einKunde) throws Exception {
		try
		{
			anzeigenverwaltung.speichereKunde(einKunde);
			aktualisiereListen();
		}
		catch(Exception e)
		{
			throw new Exception("Beim Speichern des Kunden ist ein Fehler aufgetreten!");
		}
		
	}

	public void aktualisiereListen() throws Exception {
		try
		{
			this.anzeigenListe = anzeigenverwaltung.getAnzeigen();
			this.kundenListe = anzeigenverwaltung.getKunden();
		}
		catch(Exception e)
		{
			throw new Exception("Beim Aktualisieren der Listen ist ein Fehler aufgetreten!");
		}		
	}

	public String endeAnwendung() throws Exception {
		try
		{
			return anzeigenverwaltung.endeAnwendung();
		}
		catch(Exception e)
		{
			throw new Exception("Beim Beenden der Anwendung ist ein Fehler aufgetreten!");
		}
	}

}
