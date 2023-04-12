package server;

import jakarta.xml.ws.Endpoint;

public class StartServerUI {

public static final String BASE_URI = "http://localhost:9998/";
	
	public static void main(String[] args) throws Exception
	{
		Endpoint e = Endpoint.publish( "http://localhost:18080/kundenverwaltung", new KundenanfragenManager() ); 
 	    System.in.read();
 	    e.stop();
	}
}