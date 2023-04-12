package server;

import java.net.URI;

import common.CustomerContainerI;
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
import server.application.CustomerContainer;

@Path("/kunde/{kundennummer}")
public class RessourceCustomer
{
	@Context
	UriInfo uriInfo;
	
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	public Response einfuegeKunde(
			@DefaultValue("") @PathParam("kundennummer") String kndNr, String kndName)
	{
		Response response;
		CustomerContainerI objContainer = 
				CustomerContainer.getReference();
		
		Integer kdnNrInt = Integer.parseInt(kndNr);
		String k = objContainer.getCustomerName(kdnNrInt);
		
		if(k.equals("")) 
		{
			
			URI uri = uriInfo.getAbsolutePathBuilder().build();
			response = Response.created(uri).build();
		}
		else
		{
			response = Response.noContent().build();
		}
		objContainer.create(kndName, kdnNrInt);
		
		return response;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getKundeZuNr(@PathParam("kundennummer") String kndNr)
	{
		Integer kdnNrInt = Integer.parseInt(kndNr);
		return CustomerContainer.getReference().getCustomerName(kdnNrInt);
	}
}