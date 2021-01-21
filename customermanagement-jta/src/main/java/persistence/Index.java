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
public class Index implements XAResource
{
	private final int MAX = 100;
	private String dateiname = "Indexdatei.txt";
	private String dateinameKopie = "Indexdatei_kopie.txt";
	
	private int indextabelle[]; // 0..MAX-1
	private RandomAccessFile eineIndexDatei;
	private int schluessel;
	private int index;
	private boolean kopieDurchgefuehrt;

	public Index()
	{
		//Initialisierung der indextabelle
		indextabelle = new int[MAX];
		for (int i = 0; i < MAX; i++)
			indextabelle[i] = -1;
		//Kein Datensatz zum Schluessel vorhanden
	}

	public void setAktuellerSchluessel(int schluessel)
	{
		this.schluessel = schluessel;
	}

	public void setAktuellerIndex(int index)
	{
		this.index = index;
	}
	
	public void erzeugeEintrag(int schluessel, int index) throws IOException
	{
		/*
		 * Speichert zu einem Schluessel den zugehoerigen Datensatz-Index
		 * in der indextabelle
		 */
		if (schluessel < MAX)
		{
			//Aktualisieren der Indexdatei, d. h. Abspeichern der Datei
			indextabelle[schluessel] = index;
			aktualisiereIndexDatei(schluessel);
		}
	}

	public int gibIndexZuSchluessel(int schluessel)
	{
		//Gibt zu dem Schluessel den gefundenen Datensatz-Index zurueck...
		if (schluessel < MAX)
			return indextabelle[schluessel];
		//... oder 0, wenn Schluessel zu groß ist
		else
			return -1; 
	}

	public void ladeIndexDatei() throws IOException
	{
		/*
		 * Liest die Indextabelle vollstaendig aus einer Datei.
		 * Dies geschieht nur beim Start des Programms
		 */
		eineIndexDatei = new RandomAccessFile(dateiname, "r");
		int index;
		for (int schluessel = 0; schluessel < MAX; schluessel++)
		{
			index = eineIndexDatei.readInt();
			indextabelle[schluessel] = index;
		}
		eineIndexDatei.close();
	}

	public void speichereIndexDatei() throws IOException
	{
		/*
		 * Speichert die Indextabelle vollstaendig in einer Datei.
		 * Dies geschieht beim Beenden des Programms
		 */
		schreibeDatenInDatei();
		eineIndexDatei.close();
	}

	public void schreibeDatenInDatei() throws IOException
	{
		eineIndexDatei = new RandomAccessFile(dateiname, "rw");
		for (int schluessel = 0; schluessel < MAX; schluessel++)
			eineIndexDatei.writeInt(indextabelle[schluessel]);
	}
	
	private void aktualisiereIndexDatei(int schluessel) throws IOException
	{
		/*
		 * Aktualisiert die Indextabelle in der Indexdatei.
		 * Dies geschieht beim Hinzufuegen eines neuen Indexes
		 * oder Aendern eines alten Indexes
		 */
		eineIndexDatei = new RandomAccessFile(dateiname, "rw");
		
		/*
		 *  Positionieren auf den entsprechenden Eintrag;
		 *  eine int-Zahl belegt 4 Bytes
		 */
		eineIndexDatei.seek((long) (schluessel * 4));
		eineIndexDatei.writeInt(indextabelle[schluessel]);
		eineIndexDatei.close();
	}

	//Zum Testen
	public void gibIndextabelleAus()
	{
		int schluessel = 0;
		for (int element : indextabelle)
		{
			System.out.println(schluessel + "  " + element);
			schluessel++;
		}
	}
	
	//Die gesamte Indextabelle zurueckgeben
	public int[] getIndextabelle()
	{
		return indextabelle;
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
			schreibeDatenInDatei();
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
			erzeugeEintrag(schluessel, index);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new XAException();
		}		
		return XA_OK;
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
				eineIndexDatei.close();
				Hilfsklasse.kopiereDatei(new File(dateinameKopie), new File(dateiname));
				ladeIndexDatei();
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