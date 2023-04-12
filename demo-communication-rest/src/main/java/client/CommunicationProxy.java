package client;

import common.CustomerContainerI;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class CommunicationProxy implements CustomerContainerI
{
	private static CommunicationProxy serverProxy = null;
	
	private static final String BASE_URI = "http://localhost:9998/";

	private WebTarget kndRessource, kndNrRessource;
    
	public CommunicationProxy()
	{
		Client client = ClientBuilder.newClient();
		kndRessource = client.target(BASE_URI + "kunde");
		kndNrRessource = client.target(BASE_URI + "kundennummer");
        
	}

	public static CommunicationProxy getReference()
	{
		if (serverProxy == null)
			serverProxy = new CommunicationProxy();
		return serverProxy;
	}
	
	public void create(String name, int number) 
	{
		try {
			String kndNrStr = Integer.toString(number);
			Response resp = kndRessource.path("/" + kndNrStr).request(MediaType.TEXT_PLAIN)
					.put(Entity.entity(name, MediaType.TEXT_PLAIN));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNextNumber() 
	{
		try {
			return Integer.parseInt(kndNrRessource.request(MediaType.TEXT_PLAIN).get(String.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getCustomerName(int nummer) 
	{
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

	public void close() 
	{
	}
}
