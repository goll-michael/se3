package server;

import java.io.IOException;

import jakarta.xml.ws.Endpoint;

import server.application.KundenanfragenManager;

class ServerStart 
{
	public static void main(String[] args) throws IOException 
	{
	    Endpoint e = Endpoint.publish( "http://localhost:18080/kundenverwaltung", 
                new KundenanfragenManager() ); 
  	    System.out.println("Der Kundenverwaltungsserver(soap) läuft, zum Stoppen bitte Enter drücken.");
 	    System.in.read();
         e.stop();
	}
}
