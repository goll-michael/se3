package server;

import java.io.IOException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

class ServerStart 
{
	private static final int port = 18080;
	public static void main(String[] args) throws XmlRpcException, IOException
	{
        WebServer webServer = new WebServer(port);
        
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
      
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        phm.addHandler("AnfragenBehandler", server.application.AnfragenBehandler.class);
        xmlRpcServer.setHandlerMapping(phm);
      
        XmlRpcServerConfigImpl serverConfig =
            (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(false);
        serverConfig.setContentLengthOptional(false);

        webServer.start();
        
  	    System.out.println("Der Kundenverwaltungs-Server(rpc) läuft, zum Stoppen bitte Enter drücken.");
 	    System.in.read();
         webServer.shutdown();
	}
}
