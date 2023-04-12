package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class StartServerUI {

public static final String BASE_URI = "http://localhost:9998/";
	
	public static void main(String[] args) throws Exception
	{
		Server server = new Server(9998);

        ServletContextHandler ctx = 
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
                
        ctx.setContextPath("/");
        server.setHandler(ctx);

        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter("jersey.config.server.provider.packages", "server");

		server.start();
		server.join();
		
		System.in.read();
		server.stop();
		System.exit(0);
	}
}