package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import server.application.ServerProtokoll;


class ServerStart 
{
	private static final int PORT_NUMMER = 4444;
	
	public static void main(String[] args) throws IOException  
	{
		ServerSocket serverSocket = null;
        try 
        {
            serverSocket = new ServerSocket(PORT_NUMMER);
        } 
        catch (IOException e) 
        {
            System.err.println("Kann nicht horchen auf dem Port: " + PORT_NUMMER);
            System.exit(1);
        }
        System.out.println("Der Kundenverwaltungsserver(sockets) ist gestartet...");
        
        Socket clientSocket = null;
        
        /*
         * "dient" einem Klienten nach dem anderen, endlos weiter
         * aber zu einem gegebenen Zeitpunkt immer nur einem Klienten
         */
        while(true) 
        {
	        try 
	        {
	            clientSocket = serverSocket.accept();
	        } 
	        catch (IOException e) 
	        {
	            System.err.println("accept fehlgeschlagen.");
	            System.exit(1);
	        }
			
	        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
	        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
	        
	        String eingabeZeile;
	        Object ausgabe = null; 
	        ServerProtokoll protokoll = new ServerProtokoll();
        	try {
				while ((eingabeZeile = (String) ois.readObject()) != null)
				{
					ausgabe = protokoll.bearbeiteEingabe(eingabeZeile);
					oos.writeObject(ausgabe);
					oos.flush();
					if (ausgabe.equals("Bye."))
		               break;
				}
			}
        	catch (ClassNotFoundException e)
        	{
				e.printStackTrace();
			}
			finally
			{
	        	oos.close();
	        	ois.close();
		        clientSocket.close();
			}
        }
	}
}
