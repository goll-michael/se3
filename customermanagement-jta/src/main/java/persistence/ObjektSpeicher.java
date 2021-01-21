package persistence;
/* 
 * Das Transaktionsbasierte ist in diesem Fall, dass die Index- und die Stammdatendatei
 * geschrieben werden und nur wenn beides fehlerfrei durchgefuehrt wurde, ist die Transaktion beendet.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import application.Kunde;

import com.atomikos.icatch.jta.UserTransactionManager;

public class ObjektSpeicher {
	private Index derIndex;
	private Datei dieDatei;

	private ArrayList<Kunde> meineKunden;

	public ObjektSpeicher() {
		derIndex = new Index();
		try {
			// Indexdatei oeffnen und Indextabelle laden
			derIndex.ladeIndexDatei();
		} catch (IOException e) {
			try // Wenn nicht vorhanden, dann neu anlegen
			{
				derIndex.speichereIndexDatei();
			} catch (IOException e1) {
				System.out.println("Fehler beim Anlegen der Indexdatei");
			}
			System.out.println("Indexdatei nicht vorhanden, wird neu angelegt");
		}

		// Hauptdatei öffnen
		dieDatei = new Datei();
	}

	/*
	 * Die Kunden werden erst beim Beenden des Programms gespeichert. Aus diesem
	 * Grund auch hier Schliessen der Dateien.
	 */
	@SuppressWarnings("unchecked")
	public void speichereObjekt(Object einObjekt) {
		// Das Objekt ist vom Typ ArrayList<Kunde>
		meineKunden = (ArrayList<Kunde>) einObjekt;

		// Alle Kunden durchlaufen und persistent speichern
		Iterator<Kunde> iter = meineKunden.iterator();
		while (iter.hasNext()) {
			Kunde einKunde = iter.next();

			String datensatz = einKunde.getName();
			int schluessel = einKunde.getNummer();
			int index = derIndex.gibIndexZuSchluessel(schluessel);
			if (index != -1) {
				System.out.println("Datensatz schon vorhanden: " + schluessel + " " + index);

				// Ueberschreibt den vorhandenen Datensatz in der Hauptdatei
				try {
					dieDatei.speichereSatz(datensatz, index);
				} catch (IOException e) {
					System.out.println("Fehler bei speichereSatz: " + e);
				}
			} else
			// Datenatz ist neu, hinten anhaengen
			{
				// Anzahl Datensaetze in Datei ermitteln, daraus neuen Index ermitteln
				index = dieDatei.gibAnzahlDatensaetze();

				// Transaktionsbasiert:
				TransactionManager tm = new UserTransactionManager();

				try {
					tm.begin();
					Transaction t = tm.getTransaction();
					derIndex.setAktuellerSchluessel(schluessel);
					derIndex.setAktuellerIndex(index);
					t.enlistResource(derIndex);
					dieDatei.setAktuellerDatensatz(datensatz);
					dieDatei.setAktuellerIndex(index);
					t.enlistResource(dieDatei);
					tm.commit();
				} catch (NotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SystemException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					System.err.println("transaction rolled back");
					// e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (HeuristicMixedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (HeuristicRollbackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// Datei schliessen
		if (derIndex != null) {
			try {
				// Indexdatei speichern
				derIndex.speichereIndexDatei();
			} catch (IOException e) {
				System.out.println("Probleme beim Schließen der Indexdatei " + e);
			}

			// Schliessen der Hauptdatei
			if (dieDatei != null)
				dieDatei.schliesseDatei();
		}

	}

	/*
	 * KundeContainer erwartet, dass eine ArrayList mit allen bereits persistent
	 * gespeicherten Kunden zurueckgegeben wird.
	 */
	public Object leseObjekt() throws Exception {
		meineKunden = new ArrayList<Kunde>();

		// Indextabelle mit allen vorhandenen Eintraegen wird ausgelesen
		int[] indextabelle = derIndex.getIndextabelle();

		// Indextabelle durchlaufen
		for (int i = 0; i < indextabelle.length - 1; i++) {
			Kunde einKunde = null;
			String datensatz = "";
			if (indextabelle[i] != -1) // Ein Datensatz-Index ist vorhanden
			{
				datensatz = dieDatei.leseSatz(indextabelle[i]);
				einKunde = new Kunde();
				einKunde.setName(datensatz);
				einKunde.setNummer(indextabelle[i]);
				meineKunden.add(einKunde);
			}
		}
		return meineKunden;
	}
}
