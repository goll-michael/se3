package client.application;

import javax.xml.rpc.Call;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.ParameterMode;
import javax.xml.namespace.QName;

/**
 * Client-Anwendung f�r den Aufruf des Web Services "AnzeigenService" 
 * Vor dem Ausf�hren dieser Client-Anwendung muss der JBoss-Anwendungsserver
 * mit der Anwendung "anzeigenverwaltung_web" gestartet werden. 
 * 
 */
public class WebserviceClient {

	private String NS_XSD;
	private Call anzeigenServiceCall;	

	/**
	 * Konstruktor mit Befehlen zum Initialisieren der Verbindungsparameter
	 * @param adresse Adresse des aufzurufenden Web Services
	 */
	public WebserviceClient(String adresse)
	{
		try
		{
			// XSL-Namespace definieren
			this.NS_XSD = "http://www.w3.org/2001/XMLSchema";
			
			// Fabrik zum Erstellen der Services
			ServiceFactory factory = ServiceFactory.newInstance();
			
			// ### Definition des Service-Aufrufs f�r den Web Service "AnzeigenService" ###
			// Erzeugen einer Service-Referenz mit dem im WSDL-Dokument unter "targetNamespace"
			// definierten Namensraum "http://webservice.server"
			Service anzeigenService = factory.createService(
				new QName(
					"http://webservice.server/",
					"AnzeigenService"
				)
			);
			// Erzeugen eines Objekts vom Typ Call, das den Service-Aufruf kapselt
			this.anzeigenServiceCall = anzeigenService.createCall(new QName(
				"http://webservice.server/",
				"AnzeigenServicePort"
			));	
			
			// Adresse des WSDL-Dokumentes an den Service-Aufruf �bergeben
			// Darf nicht mit Suffix "?wsdl" enden, also nicht:
			// http://127.0.0.1:8080/anzeigenverwaltung_ejb/AnzeigenService?wsdl
			// sondern
			// http://127.0.0.1:8080/anzeigenverwaltung_ejb/AnzeigenService
			this.anzeigenServiceCall.setTargetEndpointAddress(
					adresse.substring(0,adresse.length()-5)
			);					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Erstellen einer Anzeige �ber den Web Service "AnzeigenService"
	 * @param kundennummer
	 * @param rubrik
	 * @param titel
	 * @param beschreibung
	 * @param preis
	 * @return Erfolgs- oder Fehlermeldung
	 */
	public String erstelleAnzeige(String kundennummer, String rubrik, String titel, String beschreibung, String preis)
	{
		String result = "Beim Zugriff auf den Webservice ist ein Fehler aufgetreten!";
		try
		{
			// Namen der aufzurufenden Methode festlegen. Hier: erstelleAnzeige
			anzeigenServiceCall.setOperationName(
				new QName(
					"http://webservice.server/",
					"erstelleAnzeige"
				)
			);
			
			// R�ckgabetyp der Methode definieren. Hier: string
			QName QNAME_TYPE_STRING = new QName(NS_XSD, "string");
			anzeigenServiceCall.setReturnType(QNAME_TYPE_STRING);

			// Namen, Typen und Richtung (IN = Eingabeparameter)
			// der Eingabeparameter der Methode definieren
			anzeigenServiceCall.addParameter(
				"kundennummer", QNAME_TYPE_STRING, 
				ParameterMode.IN
			);
			anzeigenServiceCall.addParameter(
				"rubrik", QNAME_TYPE_STRING, 
				ParameterMode.IN
			);		      
			anzeigenServiceCall.addParameter(
				"titel", QNAME_TYPE_STRING, 
				ParameterMode.IN
			);
			anzeigenServiceCall.addParameter(
				"beschreibung", QNAME_TYPE_STRING, 
				ParameterMode.IN
			);
			anzeigenServiceCall.addParameter(
				"preis", QNAME_TYPE_STRING, 
				ParameterMode.IN
			);		      
			
			// Parameterwerte �bergeben
			String[] params = { kundennummer , rubrik, titel, beschreibung, preis };     

			// Methode aufrufen und R�ckgabe-String speichern
			result = (String) anzeigenServiceCall.invoke(params);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
		return result;
	}
}

