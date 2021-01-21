package server.application;

import java.net.URI;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/kunde/{kundennummer}")
public class KundenRessource
{
	@Context
	UriInfo uriInfo;
	
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	public Response einfuegeKunde(
			@DefaultValue("") @PathParam("kundennummer") String kndNr, String kndName)
	{
		Response response;
		KundenContainerEinfach objContainer = 
			KundenContainerEinfach.getObjektreferenz();
		
		Integer kdnNrInt = Integer.parseInt(kndNr);
		String k = objContainer.getKundeZuNr(kdnNrInt);
		
		if(k.equals("")) 
		{
			
			URI uri = uriInfo.getAbsolutePathBuilder().build();
			response = Response.created(uri).build();
		}
		else
		{
			response = Response.noContent().build();
		}
		objContainer.einfuegeKunde(kndName, kdnNrInt);
		
		return response;
	}
	
	@GET
	@Produces("text/plain")
	public String getKundeZuNr(@PathParam("kundennummer") String kndNr)
	{
		Integer kdnNrInt = Integer.parseInt(kndNr);
		return KundenContainerEinfach.getObjektreferenz().getKundeZuNr(kdnNrInt);
	}
}