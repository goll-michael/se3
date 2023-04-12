package server;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import server.application.CustomerContainer;

@WebService(name="Kundenverwaltung") 
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class KundenanfragenManager
{
	@WebMethod 
	public boolean einfuegeKunde(String kndName, int kndNr)
	{
		Integer kdnNrInt = Integer.parseInt("" + kndNr);
		CustomerContainer.getReference().create(kndName, kdnNrInt);
		return true;
	}
	
	@WebMethod
	public int getNaechsteKundenNr()
	{
    	return CustomerContainer.getReference().getNextNumber();
	}
	
	@WebMethod 
	public String getKundeZuNr(int kndNr)
	{
		Integer kdnNrInt = Integer.parseInt("" + kndNr);			
		return CustomerContainer.getReference().getCustomerName(kdnNrInt);
	}

	@WebMethod 
	public boolean endeAnwendung()
	{
		CustomerContainer.getReference().close();
		return true;
	}
}