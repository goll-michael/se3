package server;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class StartServerUI {

	private static final int port = 18080;

	public static void main(String[] args) throws Exception {
		WebServer webServer = new WebServer(port);

		XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

		PropertyHandlerMapping phm = new PropertyHandlerMapping();
		phm.addHandler("AnfragenBehandler", server.AnfragenBehandler.class);
		xmlRpcServer.setHandlerMapping(phm);

		XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
		serverConfig.setEnabledForExtensions(false);
		serverConfig.setContentLengthOptional(false);

		webServer.start();

		System.in.read();
		webServer.shutdown();
	}
}