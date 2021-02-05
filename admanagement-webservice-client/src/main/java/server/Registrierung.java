package server;

import javax.xml.registry.JAXRException;

import juddi.Abfrage;


/**
 * Klasse zum Registrieren von Services im jUDDI-Verzeichnis
 */
public class Registrierung
{
    /**
     * Main-Methode mit beispielhaften Anfragen an das Verzeichnis
     * @param args 
     */
    public static void main(String[] args)
    {
        try
        {
        	Abfrage abfrage = new Abfrage();
        	abfrage.loescheOrganisation("Zeitung");
        	
            System.out.println("=========================================================");
            System.out.println("===============Neue Organisationen erstellen=============");
            System.out.println("=========================================================");
            
            // Zeitung als Organisation registrieren
            abfrage.erstelleOrganisation("SWT Zeitung");
            // AnzeigenService registrieren
            abfrage.erstelleService(
        		"SWT Service",
        		"Erstellen von Zeitungsanzeigen in der SWT Zeitung",
        		"http://localhost:8080/amWS-SWT-Server-jar/AnzeigenService?wsdl",
        		abfrage.sucheOrganisationenNachNamen("SWT Zeitung").iterator().next()
    		);

            // Zeitung als Organisation registrieren
            abfrage.erstelleOrganisation("ET Zeitung");
            // AnzeigenService registrieren
            abfrage.erstelleService(
        		"ET Service",
        		"Erstellen von Zeitungsanzeigen in der ET Zeitung",
        		"http://localhost:8080/amWS-ET-Server-jar/AnzeigenService?wsdl",
        		abfrage.sucheOrganisationenNachNamen("ET Zeitung").iterator().next()
    		);

            // Zeitung als Organisation registrieren
            abfrage.erstelleOrganisation("IT Zeitung");
            // AnzeigenService registrieren
            abfrage.erstelleService(
        		"IT Service",
        		"Erstellen von Zeitungsanzeigen in der IT Zeitung",
        		"http://localhost:8080/amWS-IT-Server-jar/AnzeigenService?wsdl",
        		abfrage.sucheOrganisationenNachNamen("IT Zeitung").iterator().next()
    		);            
        } 
        catch (JAXRException theException)
        {
            theException.printStackTrace();
        }
    }
}