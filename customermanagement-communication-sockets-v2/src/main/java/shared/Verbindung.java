package shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Verbindung
{
	Socket ziel = new Socket();

	public Verbindung()
	{
	}

	public boolean istOffen()
	{
		if (this.ziel.isClosed())
			return false;
		return true;
	}

	public void schliesseVerbindung()
	{
		try
		{
			this.ziel.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void starteVerbindung(String servername, int serverport)
			throws IOException
	{
		Socket server = new Socket(servername, serverport);
		this.ziel = server;
	}

	public boolean akzeptiereVerbindung(int serverport)
	{
		try
		{
			ServerSocket server = new ServerSocket(serverport);
			this.ziel = server.accept();
			server.close(); // wenn die Zeile ausgelassen wird, gibt es Probleme
							// beim 2ten connecten
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendeString(String msg) throws IOException
	{
		return sendeString(msg, true);
	}

	public boolean sendeString(String msg, boolean erwarteBestaetigung) throws IOException
	{
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(ziel
				.getOutputStream()));
		printWriter.print(msg);
		printWriter.flush();
		if(!erwarteBestaetigung) return true;
		
		int ok = empfangeZahl(); // es wird gewartet bis der string komplett
									// übertragen wurde
		//printWriter.close();
		if (ok == 42)
			return true;
		return false;
	}
	
	public String empfangeString() throws IOException
	{
		return empfangeString(true);
	}
	
	public String empfangeString(boolean sendeBestaetigung) throws IOException
	{
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(ziel.getInputStream()));
		char[] buffer = new char[250];
		int stringLaenge = bufferedReader.read(buffer, 0, 250);
		String string = new String(buffer, 0, stringLaenge);
		
		if(sendeBestaetigung) sendeZahl(42); // Empfangsbestätigung
	
		return string;
	}
	
	public boolean sendeZahl(int Zahl) throws IOException
	{
		return sendeString(Integer.toString(Zahl), false);
	}

	public boolean sendeZahl(int Zahl, boolean sendeBestaetigung) throws IOException
	{
		return sendeString(Integer.toString(Zahl), sendeBestaetigung);
	}

	public int empfangeZahl() throws IOException
	{
		String s = empfangeString(false);
		return Integer.parseInt(s);
	}
}
