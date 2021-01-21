package server.application;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/kundennummer")
public class KundennummerRessource
{
	@GET
	@Produces("text/plain")
	public String getNaechsteKundenNr()
	{
		int nextKdnNr = 
			KundenContainerEinfach.getObjektreferenz().getNaechsteKundenNr();
		
    	return Integer.toString(nextKdnNr);
	}
}
