package persistence;

import java.io.*;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

/**
 * XAResource definiert die Schnittstelle zu einem Ressourcen-Manager.
 * Diese Schnittstelle wird dann von dem Transaktionsmanager genutzt.
 * In dieser Fallstudie muessen die beiden Klasse Index und Datei
 * diese Schnittstelle implementieren.
 * 
 * Vom Transaktionsmanager werden verschiedene Dateien generiert:
 * - X.tm0.epoch: Fuer eindeutige XIDs (XID ist die ID fuer die durchgefuehrte Transaktion)
 * - tm.out: Log-Datei
 * Weitere Dateien fuer die Transaktionsverwaltung.
 */
public class Datei implements XAResource
{
	private RandomAccessFile eineStammdatei;
	
	//aktuelle Position des Dateizeigers
	private final int SATZLAENGE = 100;
	
	private String dateiname = "Stammdatei.txt";
	private String dateinameKopie = "Stammdatei_kopie.txt";
	private String datensatz;
	private int index;
	private boolean kopieDurchgefuehrt;

	public Datei()
	{
		oeffneDatei(dateiname);
	}


	public void setAktuellerDatensatz(String datensatz)
	{
		this.datensatz = datensatz;		
	}

	public void setAktuellerIndex(int index)
	{
		this.index = index;
	}
	
	public void speichereSatz(String satz, int index) throws IOException
	{
		//Speichert einen Datensatz Satz an einer Position index in der Datei
		if (eineStammdatei != null)
		{
			//interne Hilfsoperation
			positioniereAufSatz(index);

			//interne Hilfsoperation
			writeFixedString(satz, SATZLAENGE);
		}
	}

	public String leseSatz(int index) throws IOException
	{
		//Liest den Datensatz index aus der Datei und gibt ihn als String zurück
		if (eineStammdatei != null)
		{
			//interne Hilfsoperation
			positioniereAufSatz(index);
			
			//interne Hilfsoperation
			return readFixedString(SATZLAENGE);
		}
		else
			return null;
	}

	public void oeffneDatei(String name)
	{
		//Oeffnen der Datei zum Lesen und Schreiben
		try
		{
			eineStammdatei = new RandomAccessFile(name, "rw");
		}
		catch (IOException e)
		{
			//Testausgabe
			System.out.println("Datei:oeffneDatei: " + e);
		}
	}

	public void schliesseDatei()
	{
		//Schliessen der Datei
		try
		{
			eineStammdatei.close();
		}
		catch (java.io.IOException e)
		{
			//Testausgabe
			System.out.println("Datei:schliesseDatei: " + e);
		}
	}

	public int gibAnzahlDatensaetze()
	{
		//Rueckgabe der Dateilaenge in Datensaetzen (!)
		long anzahl = 0;
		try
		{
			anzahl = eineStammdatei.length();
		}
		catch (IOException e)
		{
			//Testausgabe
			System.out.println("Datei:gibAnzahlDatensaetze: " + e);
		}
		return (int) (anzahl / (long) (SATZLAENGE * 2));
		//Umrechnung auf Anzahl Datensaetze
	}

	//Hilfsoperationen
	private void positioniereAufSatz(int index) throws IOException
	{
		//Positioniert in der Datei auf den Datensatz mit der Position index
		if (eineStammdatei != null)
		{
			try
			{
				//fuer 1 char werden 2 bytes benoetigt
				eineStammdatei.seek(index * SATZLAENGE * 2);
			}
			catch (IOException e)
			{
				//Testausgabe
				System.out.println("Datei:positioniereAufSatz: " + e);
			}
		}
	}

	private String readFixedString(int Laenge) throws IOException
	{
		//Liest einen String der festen Laenge (Unicode = 1 Zeichen = 2 Byte) ein
		StringBuilder einPuffer = new StringBuilder(Laenge);
		
		int i = 0;
		while (i < Laenge)
		{
			char Zeichen = eineStammdatei.readChar();
			i++;
			if (Zeichen == 0) // Ende der Nutzdaten
			{
				/*
				 * eineStammdatei.skipBytes(2*(Laenge-i));
				 * Rest mit 0 ueberlesen wird benoetigt,
				 * wenn hinter dem String z. B. noch Zahlen kommen
				 */ 
				return einPuffer.toString();
			}
			else //Anhaengen an den Puffer
				einPuffer.append(Zeichen);

		}
		return einPuffer.toString();
	}

	private void writeFixedString(String einDatensatz, int Laenge)
			throws IOException
	{
		//Schreibt einen String einDatensatz der festen Laenge weg
		for (int i = 0; i < Laenge; i++)
		{
			/*
			 * Liefert das Zeichen an der i-ten Stelle.
			 * Der Rest wird mit 0 aufgefuellt
			 */
			char Zeichen = 0;
			if (i < einDatensatz.length())
				Zeichen = einDatensatz.charAt(i);
			
			//Zeichenweises Schreiben in die Stammdatei
			eineStammdatei.writeChar(Zeichen);
		}
	}

	//Zum Testen
	public void gibDatensaetzeAus()
	{
		int anzahl = gibAnzahlDatensaetze();
		System.out.println("Anzahl der Datensätze: " + anzahl);
		for (int i = 0; i < anzahl; i++)
		{
			try
			{
				System.out.println(leseSatz(i));
			}
			catch (IOException e)
			{
				System.out.println("Fehler beim Satzlesen: " + e);
			}
		}
	}
	
	/*
	 * Mit dieser Methode wird dem Ressourcen-Manager mitgeteilt, sich auf ein commit
	 * vorzubereiten. In dieser Methode bereitet sich der Ressourcen-Manager auch auf
	 * ein eventuelles rollback vor, d.h. ein undo-log wird geschrieben oder es werden
	 * Sicherungskopien angelegt. Es ist der erste Schritt im Zwei-Phasen-Commit-Protokoll.
	 * Der Transaktionsmanager geht erst in die zweite (commit-)Phase über, wenn kein
	 * Ressourcen-Manager beim prepare()-Aufruf eine XAException geworfen hat.
	 */
    @Override
	public int prepare(Xid arg0) throws XAException
	{
		kopieDurchgefuehrt = false;
		try
		{
			Hilfsklasse.kopiereDatei(new File(dateiname), new File(dateinameKopie));
			kopieDurchgefuehrt = true;
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
			throw new XAException();
		}
		try
		{
			speichereSatz(datensatz, index);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new XAException();
		}
		return XA_OK;
	}
    
	/*
	 * Mit dieser Methode wird der Ressourcen-Manager aufgefordert, die Transaktion
	 * abzuschließen und ggfs. gesperrte Ressourcen freizugeben.
	 * An dieser Stelle wird lediglich die Sicherungskopie geloescht.
	 */
	@Override
	public void commit(Xid arg0, boolean arg1) throws XAException
	{
		try
		{
			Hilfsklasse.loescheDatei(new File(dateinameKopie));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new XAException();
		}
	}
	
	/*
	 * Beim Rollback soll der Ressourcen-Manager alle waehrend der Transaktion
	 * durchgefuehrten Aenderungen wieder rueckgaengig machen. Hierfuer werden die
	 * Sicherungen genutzt, die waehrend der prepare()-Phase angelegt worden sind.
	 */
	@Override
	public void rollback(Xid arg0) throws XAException
	{
		try
		{
			if(this.kopieDurchgefuehrt)
			{
				schliesseDatei();
				Hilfsklasse.kopiereDatei(new File(dateinameKopie), new File(dateiname));
				oeffneDatei(dateiname);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new XAException();
		}
	}

	@Override
	public void end(Xid arg0, int arg1) throws XAException
	{
	}

	@Override
	public void forget(Xid arg0) throws XAException
	{
	}

	@Override
	public int getTransactionTimeout() throws XAException
	{
		return 0;
	}

	@Override
	public boolean isSameRM(XAResource xares) throws XAException
	{
		return (xares == this);
	}

	@Override
	public Xid[] recover(int arg0) throws XAException
	{
		return null;
	}

	@Override
	public boolean setTransactionTimeout(int arg0) throws XAException
	{
		return false;
	}

	@Override
	public void start(Xid arg0, int arg1) throws XAException
	{		
	}
}
