package client.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import javax.swing.JOptionPane;

import server.schnittstelle.Kunde;
import server.schnittstelle.KundeContainerEinfachI;
import static server.schnittstelle.KommandoKonstante.*;

// ServerProxy stellt für die GUI-Klassen die Verbindung zum Server her
public class ServerProxy implements KundeContainerEinfachI
{
	//Singleton-Muster
	private static ServerProxy serverProxy = null;
	private static Kunde einKunde=null;
	
	private final int PORT_NUMMER = 4444;
	private final String SERVER_NAME = "localhost";
	
	private Socket socket;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public ServerProxy()
	{
		socket = null;
		oos = null;
		ois = null;
        
        try 
        {
			socket = new Socket(SERVER_NAME, PORT_NUMMER);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
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
		String antwort=FEHLER;
		try
		{
			oos.writeObject(String.format(CMD_EINFUEGE_KUNDE, nummer, name));
			antwort = readServerResponse();
		}
		catch (IOException e)
		{
			try
			{
				throw new Exception();
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		
		if(antwort.equals(FEHLER))
		{
			try
			{
				throw new Exception();
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}

	public int getNaechsteKundenNr() 
	{
		String response = null;
		try
		{
			oos.writeObject(CMD_GET_NAECHSTE_KUNDEN_NR);
			response = (String)ois.readObject();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
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
		try
		{
			oos.writeObject(String.format(CMD_GET_KUNDE_ZU_NR, nummer));
			einKunde = (Kunde) ois.readObject();
			if(einKunde==null)
				return "";
			return einKunde.getName();
		}
		catch (Exception e)
		{
			try
			{
				throw new Exception();
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		return null;
	}

	public void endeAnwendung() 
	{
		try
		{
			oos.writeObject(CMD_ENDE_ANWENDUNG);
			readServerResponse();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				oos.close();
				ois.close();
				socket.close();
			}
			catch (Exception e1)
			{
			}
		}
	}
	
	private String readServerResponse()
	{
		String response = null; 
		try 
		{
			response = (String) ois.readObject();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return response;
	}
}
