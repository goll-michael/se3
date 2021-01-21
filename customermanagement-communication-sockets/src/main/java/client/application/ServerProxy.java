package client.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import javax.swing.JOptionPane;
import server.schnittstelle.KundenContainerEinfachI;
import static server.schnittstelle.KommandoKonstante.*;

// ServerProxy stellt für die GUI-Klassen die Verbindung zum Server her
public class ServerProxy implements KundenContainerEinfachI
{
	//Singleton-Muster
	private static ServerProxy serverProxy = null;
	
	private final int PORT_NUMMER = 4444;
	private final String SERVER_NAME = "localhost";
	
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	
	public ServerProxy()
	{
		socket = null;
        out = null;
        in = null;
        
        try 
        {
			socket = new Socket(SERVER_NAME, PORT_NUMMER);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } 
        catch (UnknownHostException e) 
        {
        	String msg = "Host unbekannt:" + SERVER_NAME;
        	JOptionPane.showMessageDialog(null, msg, "Mitteilung", JOptionPane.OK_OPTION );
            System.exit(1);
        } 
        catch (IOException e) 
        {
        	String msg = "Keine I/O Verbindung mÃ¶glich zu: " + SERVER_NAME;
        	JOptionPane.showMessageDialog(null, msg, "Mitteilung", JOptionPane.OK_OPTION );
            System.exit(1);
        }
	}

	/*
	 * Klassen-Operation, die die Objektreferenz liefert.
	 * Wenn das Objekt noch nicht vorhanden ist, dann wird es erzeugt
	 */
	public static ServerProxy getObjektreferenz()
	{
		if (serverProxy == null)
			serverProxy = new ServerProxy();
		return serverProxy;
	}
	
	public void einfuegeKunde(String name, int nummer) 
	{
		out.println(String.format(CMD_EINFUEGE_KUNDE, nummer, name));
		String antwort = readServerResponse();
		if(antwort.equals(FEHLER))
		{
			try
			{
				throw new Exception();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public int getNaechsteKundenNr() 
	{
		out.println(CMD_GET_NAECHSTE_KUNDEN_NR);
		String response = readServerResponse();
		if(response != null && response.startsWith(OKAY))
		{
			List<String> args = getCommandArguments(response);
	    	if(!args.isEmpty()) 
	    	{
	    		return Integer.parseInt(args.get(0));
	    	}
		}
		return 0;
	}
	
	public String getKundeZuNr(int nummer) 
	{
		out.println(String.format(CMD_GET_KUNDE_ZU_NR, nummer));
		
		String servResponse = readServerResponse();
		if(servResponse != null && servResponse.startsWith(OKAY))
		{
			List<String> args = getCommandArguments(servResponse);
        	if(!args.isEmpty()) 
        	{
        		return args.get(0);
        	}
        	return "";
		}
		
		// Dieser Bereich wird nur erreicht, wenn ein Fehler aufgetreten ist.
		try
		{
			throw new Exception();
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	public void endeAnwendung() 
	{
		out.println(CMD_ENDE_ANWENDUNG);
		readServerResponse();
		out.close();
		try
		{
			in.close();
			socket.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private String readServerResponse()
	{
		String response = null; 
		try 
		{
			response = in.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return response;
	}
}
