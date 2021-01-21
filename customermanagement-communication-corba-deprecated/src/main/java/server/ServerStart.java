package server;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import server.application.KundenContainerObjektImpl;

public class ServerStart {
		
	public ServerStart(String[] args)
	{		
		//den Object-Request-Broker initialisieren
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		
        try
        {
        	//vom ORB eine Referenz auf einen Objekt-Adapter holen, RootPOA ist ein fest
            //definierter Name, den der ORB immer kennt
        	org.omg.PortableServer.POA poa =
        		org.omg.PortableServer.POAHelper.narrow(
        				orb.resolve_initial_references("RootPOA"));
            
            //den Objekt-Adapter aktivieren, damit er request entgegennehmen und 
            //z.B. Objekte erzeugen kann
            poa.the_POAManager().activate();

            //Zugriff auf den ObjektContainer
            org.omg.CORBA.Object tempObjektContainer = poa.servant_to_reference(new KundenContainerObjektImpl());
                        
            //Registrierung des Namensdienstes
            NamingContextExt nc = NamingContextExtHelper.narrow(
            		orb.resolve_initial_references("NameService"));
            nc.bind(nc.to_name("einKundeContainer"), tempObjektContainer);
        }
        catch (Exception e)
        {
        	System.out.println(e.getStackTrace());
        }
        orb.run();	
	}
	
	public static void main(String[] args)
	{
		// Server laeuft auf den lokalen Rechner.
		new ServerStart(args);
	}
}
