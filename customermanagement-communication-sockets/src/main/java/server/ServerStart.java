package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
			
	        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(
					new InputStreamReader(
					clientSocket.getInputStream()));
	        String eingabeZeile, ausgabeZeile;
	        ServerProtokoll protokoll = new ServerProtokoll();
	        while ((eingabeZeile = in.readLine()) != null) 
	        {
	        	ausgabeZeile = protokoll.bearbeiteEingabe(eingabeZeile);
	            out.println(ausgabeZeile);
	            if (ausgabeZeile.equals("Bye."))
	               break;
	        }
	        out.close();
	        in.close();
	        clientSocket.close();
        }
	}
}
