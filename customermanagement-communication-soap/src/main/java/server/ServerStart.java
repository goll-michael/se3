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
  	    System.out.println("Der Kundenverwaltungsserver(soap) l�uft, zum Stoppen bitte Enter dr�cken.");
 	    System.in.read();
         e.stop();
	}
}
