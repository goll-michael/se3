package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

class ServerStart
{
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
        serHol.setInitParameter("jersey.config.server.provider.packages", 
                "server.application");

		server.start();
		server.join();
		
		System.out
			.println(String
				.format("Kundenverwaltung (Rest-Version) ist gestartet unter der URL %s.\n Zum Beenden bitte Enter drücken!",
						BASE_URI));
		System.in.read();
		server.stop();
		System.exit(0);
	}
}
