package client.application;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import server.schnittstelle.KundenContainerEinfachI;

//ServerProxy stellt für die GUI-Klassen die Verbindung zum Server her
public class ServerProxy implements KundenContainerEinfachI {
	// Singleton-Muster
	private static ServerProxy serverProxy = null;

	private static final String BASE_URI = "http://localhost:9998/";

	private WebTarget kndRessource, kndNrRessource;

	public ServerProxy() {
		Client client = ClientBuilder.newClient();
		kndRessource = client.target(BASE_URI + "kunde");
		kndNrRessource = client.target(BASE_URI + "kundennummer");
	}

	/*
	 * Klassen-Operation, die die Objektreferenz liefert. Wenn das Objekt noch nicht
	 * vorhanden ist, dann wird es erzeugt
	 */
	public static ServerProxy getObjektreferenz() {
		if (serverProxy == null)
			serverProxy = new ServerProxy();
		return serverProxy;
	}

	public void einfuegeKunde(String name, int nummer) {
		try {
			String kndNrStr = Integer.toString(nummer);
			Response resp = kndRessource.path("/" + kndNrStr).request(MediaType.TEXT_PLAIN)
					.put(Entity.entity(name, MediaType.TEXT_PLAIN));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNaechsteKundenNr() {
		try {
			return Integer.parseInt(kndNrRessource.request(MediaType.TEXT_PLAIN).get(String.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getKundeZuNr(int nummer) {
		try {
			String kndNrStr = Integer.toString(nummer);
			String s = kndRessource.path(kndNrStr).request(MediaType.TEXT_PLAIN).get(String.class);
			if (s == null)
				s = "";
			return s;
		} catch (Exception e) {
			try {
				throw new Exception();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * Es gibt für den Client keine Moeglichkeit "endeAnwendung()" aufzurufen, wie
	 * das z.B. in der SOAP-Variante moeglich ist. Die Methode "endeAnwendung()"
	 * entfaellt, weil sie keiner Ressource zuzuordnen ist. Die Methode veranlasste
	 * den Server dazu, die Daten persistent zu speichern. Die persistente
	 * Speicherung erfolgt nun in der REST-Variante so, dass bei jeder PUT-Anfrage
	 * "einfuegeKunde()" die Daten persistent gespeichert werden.
	 */
	public void endeAnwendung() {
	}
}
