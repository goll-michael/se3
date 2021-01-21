package server.SocketServer;

import java.io.IOException;

import server.application.Kunde;
import server.application.ObjektContainer;
import shared.Verbindung;

public class Server
{
	private static Kunde derKunde;
	private int port = 12742;
	private Verbindung verbindung = new Verbindung();

	void start() throws IOException
	{
		while (true)
		{
			verbindung.akzeptiereVerbindung(port);
			while (verbindung.istOffen())
			{
				empfangeKommando();
			}
		}
	}

	protected void empfangeKommando() throws IOException
	{
		int kommando;
		kommando = verbindung.empfangeZahl();

		System.out.println("Kommando empfangen: " + kommando);
		switch (kommando)
		{
		case 1:
			kundegleichNull();
			break;
		case 3:
			newKunde();
			break;
		case 5:
			getNaechsteKundenNr();
			break;
		case 6:
			einfuegeKunde();
			break;
		case 7:
			derKundeGleichGleichNull();
			break;
		case 8:
			endeAnwendung();
			verbindung.schliesseVerbindung();
			break;
		case 51:
			setName();
			break;
		case 101:
			verbindung.sendeZahl(42);
			int nr = verbindung.empfangeZahl();
			getKundeZuNr(nr);
			break;
		case 102:
			setNummer();
			break;
		case 204:
			System.out.println("Testkommando");
			break;
		default:
			System.out.println("Unbekanntes Kommando");
			break;
		}
	}

	protected void endeAnwendung()
	{
		System.out.println("ende Anwendung..");
		ObjektContainer.getObjektreferenz().endeAnwendung();
	}

	protected void setName()
	{
		try
		{
			verbindung.sendeZahl(42);
			String name = verbindung.empfangeString();
			derKunde.setName(name);
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}

	protected void derKundeGleichGleichNull()
	{
		int zusenden = 0;
		if (derKunde == null)
		{
			zusenden = 0;
		}
		else
		{
			zusenden = 1;
		}
		try
		{
			verbindung.sendeZahl(zusenden);
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}

	protected void einfuegeKunde() throws IOException
	{
		ObjektContainer.getObjektreferenz().einfuegeKunde(derKunde);
		verbindung.sendeZahl(42);
	}

	protected void setNummer()
	{
		int nr = 0;
		try
		{
			verbindung.sendeZahl(42);
			nr = verbindung.empfangeZahl();
			derKunde.setNummer(nr);
			verbindung.sendeZahl(42);
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}

	protected void getNaechsteKundenNr()
	{
		int kundennr = ObjektContainer.getObjektreferenz()
				.getNaechsteKundenNr();
		try
		{
			verbindung.sendeZahl(kundennr);
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}

	protected void kundegleichNull() throws IOException
	{
		derKunde = null;
		verbindung.sendeZahl(42);
	}

	protected void newKunde() throws IOException
	{
		derKunde = new Kunde();
		verbindung.sendeZahl(42);
	}

	protected void getName()
	{
		try
		{
			verbindung.sendeString(derKunde.getName());
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}

	protected void getKundeZuNr(int nr)
	{
		derKunde = ObjektContainer.getObjektreferenz().getKundeZuNr(nr);
		if (derKunde == null)
		{
			try
			{
				verbindung.sendeString("Error");
			}
			catch (IOException e)
			{
				System.out.print(e);
			}
		}
		else
		{
			try
			{
				verbindung.sendeString(derKunde.getName());
			}
			catch (IOException e)
			{
				System.out.print(e);
			}
		}
	}

	public static void main(String[] args)
	{
		try
		{
			new Server().start();
		}
		catch (IOException e)
		{
			System.out.print(e);
		}
	}
}
