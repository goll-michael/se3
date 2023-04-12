package server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import server.application.CustomerContainer;

@Path("/kundennummer")
public class RessourceCustomerNumber
{
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getNaechsteKundenNr()
	{
		int nextKdnNr = 
				CustomerContainer.getReference().getNextNumber();
		
    	return Integer.toString(nextKdnNr);
	}
}
