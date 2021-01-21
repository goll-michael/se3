package client.SocketClient;

import java.io.IOException;

import shared.Verbindung;

public class Client
{
	private Verbindung verbindung = new Verbindung();
	public String defaultZielPort = "12742";
	public String defaultZielServer = "localhost";

	public void verbindungStarten(String server, int serverport)
			throws IOException
	{
		verbindung.starteVerbindung(server, serverport);
	}

	public void newKunde()
	{
		try
		{
			verbindung.sendeZahl(3, true);
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}

	public int getNaechsteKundenNr()
	{
		int naechsteKundenNr = 0;
		try
		{
			verbindung.sendeZahl(5, false);
			naechsteKundenNr = verbindung.empfangeZahl();
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
		return naechsteKundenNr;
	}

	public void einfuegeKunde()
	{
		try
		{
			verbindung.sendeZahl(6, true);
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}

	public boolean derKundeGleichGleichNull()
	{
		try
		{
			verbindung.sendeZahl(7, false);
			int istNull = verbindung.empfangeZahl();
			System.out.println(istNull);
			if (istNull == 0)
				return true;
			return false;
		}
		catch (IOException e)
		{
			System.out.print(e);
			return true;
		}
	}

	public void setNummer(int nummer)
	{
		try
		{
			verbindung.sendeZahl(102, true);
			verbindung.sendeZahl(nummer, true);
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}

	public boolean setName(String name)
	{
		try
		{
			verbindung.sendeZahl(51, true);
			verbindung.sendeString(name);
			return true;
		}
		catch (IOException e)
		{
			System.out.print(e);
			return false;
		}
	}

	public void endeAnwendung()
	{
		try
		{
			verbindung.sendeZahl(8, false);
			verbindung.schliesseVerbindung();
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}

	public void setzeKundeAufNull()
	{
		try
		{
			verbindung.sendeZahl(1, true);
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}

	public String getKundeZuNr(int nr)
	{
		String error = "Error";
		String Name = error;
		try
		{
			if (verbindung.sendeZahl(101, true))
			{
				if (verbindung.sendeZahl(nr, false))
				{
					Name = verbindung.empfangeString();
				}
			}
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
		return Name.equals(error) ? null : Name;
	}
}
