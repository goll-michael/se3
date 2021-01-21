package server.application;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(name="Kundenverwaltung") 
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class KundenanfragenManager
{
	@WebMethod 
	public boolean einfuegeKunde(String kndName, int kndNr)
	{
		Integer kdnNrInt = Integer.parseInt("" + kndNr);
		
		KundenContainerEinfach.getObjektreferenz().
			einfuegeKunde(kndName, kdnNrInt);

		return true;
	}
	
	@WebMethod
	public int getNaechsteKundenNr()
	{
    	return KundenContainerEinfach.getObjektreferenz().getNaechsteKundenNr();
	}
	
	@WebMethod 
	public String getKundeZuNr(int kndNr)
	{
		Integer kdnNrInt = Integer.parseInt("" +kndNr);			
		return KundenContainerEinfach.getObjektreferenz().getKundeZuNr(kdnNrInt);
	}

	@WebMethod 
	public boolean endeAnwendung()
	{
		KundenContainerEinfach.getObjektreferenz().endeAnwendung();
		return true;
	}
}