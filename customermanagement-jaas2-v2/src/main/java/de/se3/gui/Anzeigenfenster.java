package de.se3.gui;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import de.se3.applikation.Anzeige;
import de.se3.applikation.Anzeigenverwaltung;
import de.se3.applikation.Kunde;
import de.se3.security.ac.swing.AcJButton;
import de.se3.security.ac.swing.AcJLabel;
import de.se3.security.ac.swing.AcJPanel;
import de.se3.security.ac.swing.AcJTextArea;
import de.se3.security.ac.swing.AcJTextField;

/**
 * Das Anzeigenfenster
 */
public class Anzeigenfenster extends Unterfenster 
{
	// Attribute
	// Referenz auf das einzige Objekt
	private static Anzeigenfenster dasAnzeigefenster = null;
	private AcJTextField rubrikTextfeldJ;
	private static Anzeige dieAnzeige;
	private static Kunde uebergebenerKunde;
	private Kunde kundeInitial;

	// Oberflaechenelemente
	// Alle Flaechen
	private AcJPanel anzeigenFlaecheJ = new AcJPanel("Anzeigenfenster-anzeigenFlaecheJ");
	private AcJLabel nrFuehrungstext = new AcJLabel("Anzeigenfenster-nrFuehrungstext");
	private AcJLabel titelFuehrungstext = new AcJLabel("Anzeigenfenster-titelFuehrungstext");
	private AcJLabel beschreibungFuehrungstext = new AcJLabel("Anzeigenfenster-beschreibungFuehrungstext");
	private AcJLabel preisFuehrungstext = new AcJLabel("Anzeigenfenster-preisFuehrungstext");
	private AcJLabel kundeFuehrungstext = new AcJLabel("Anzeigenfenster-kundeFuehrungstext", "Kunden");

	// Alle Textfelder
	private AcJTextField nrTextfeldJ = neuesTextfeld();
	private AcJTextField titelTextfeldJ = neuesTextfeld();
	private AcJTextField preisTextfeldJ = neuesTextfeld();

	// Alle Textbereiche
	private AcJTextArea beschreibungTextbereichJ = new AcJTextArea("Anzeigenfenster-beschreibungTextbereichJ");

	// Alle Druckknoepfe
	private AcJButton okDruckknopfJ = new AcJButton("Anzeigenfenster-okDruckknopfJ");
	private AcJLabel rubrikFuehrungstext;
	private AcJTextField kundeTextfeld;
	private AcJButton uebernehmenKnopfJ = new AcJButton("Anzeigenfenster-uebernehmenKnopfJ");
	private AcJButton abbrechenKnopfJ = new AcJButton("Anzeigenfenster-abbrechenKnopfJ");
	private AcJButton neuerLinkKnopf   = new AcJButton("Anzeigenfenster-neuerLinkKnopf");
	private AcJButton auswahlLinkKnopf = new AcJButton("Anzeigenfenster-auswahlLinkKnopf");

	// Alle Rahmen
	private Border anzeigenRahmen;

	// Alle Tabellen

	// privater Konstruktor
	public Anzeigenfenster(JFrame dasAnwendungsfenster, String Fenstertitel) {
		super(dasAnwendungsfenster, Fenstertitel);

		setTitle("Neue Anzeige");
		// Layoutmanager auf null setzen
		getContentPane().setLayout(null);
		// Hintergrungfarbe der Inhaltsflaeche setzen
		getContentPane().setBackground(java.awt.Color.lightGray);
		// Zunaechst unsichtbar machen
		setVisible(false);
		// Die Anzeigeflaeche mit einem Rahmen versehen
		anzeigenRahmen = BorderFactory.createTitledBorder("Anzeige");
		anzeigenFlaecheJ.setBorder(anzeigenRahmen);
		// Den Layoutmanager der Anzeigeflaeche auf null setzen
		anzeigenFlaecheJ.setLayout(null);
		// Die Anzeigeflaeche der Inhaltsflaeche hinzufuegen
		getContentPane().add(anzeigenFlaecheJ);
		// Hintergrundfarbe fuer die Anzeigeflaeche setzen
		anzeigenFlaecheJ.setBackground(new java.awt.Color(217, 217, 217));
		// Groesse der Anzeigeflaeche
		anzeigenFlaecheJ.setBounds(12, 12, 426, 293);

		// Die Fuehrungstexte setzen und einfuegen
		nrFuehrungstext.setText("Anzeigennr.");
		anzeigenFlaecheJ.add(nrFuehrungstext);
		nrFuehrungstext.setBounds(24, 24, 78, 26);

		titelFuehrungstext.setText("Titel");
		anzeigenFlaecheJ.add(titelFuehrungstext);
		titelFuehrungstext.setBounds(24, 93, 78, 26);

		beschreibungFuehrungstext.setText("Beschreibung");
		anzeigenFlaecheJ.add(beschreibungFuehrungstext);
		beschreibungFuehrungstext.setBounds(24, 117, 90, 35);

		preisFuehrungstext.setText("Preis");
		anzeigenFlaecheJ.add(preisFuehrungstext);
		preisFuehrungstext.setBounds(24, 222, 84, 40);

		// Die Textfelder setzen und einfuegen
		anzeigenFlaecheJ.add(nrTextfeldJ);
		nrTextfeldJ.setBackground(new java.awt.Color(255, 255, 159));
		nrTextfeldJ.setBounds(108, 24, 95, 24);
		nrTextfeldJ.setEditable(false);
		
		anzeigenFlaecheJ.add(titelTextfeldJ);
		titelTextfeldJ.setBackground(new java.awt.Color(255, 255, 159));
		titelTextfeldJ.setBounds(108, 93, 232, 24);

		anzeigenFlaecheJ.add(beschreibungTextbereichJ);
		beschreibungTextbereichJ.setBounds(24, 147, 316, 72);

		anzeigenFlaecheJ.add(preisTextfeldJ);
		{
			rubrikFuehrungstext = new AcJLabel("Anzeigenfenster-rubrikFuehrungstext");
			anzeigenFlaecheJ.add(rubrikFuehrungstext);
			rubrikFuehrungstext.setText("Rubrik");
			rubrikFuehrungstext.setBounds(24, 61, 78, 26);
		}
		{
			rubrikTextfeldJ = neuesTextfeld();
			anzeigenFlaecheJ.add(rubrikTextfeldJ);
			rubrikTextfeldJ.setText("");
			rubrikTextfeldJ.setBackground(new java.awt.Color(255, 255, 159));
			rubrikTextfeldJ.setBounds(108, 61, 232, 24);
			anzeigenFlaecheJ.add(kundeFuehrungstext);
		}
		preisTextfeldJ.setBounds(108, 231, 89, 24);

		// Knoepfe zum Aufbauen der Links
		neuerLinkKnopf.setBounds(352, 252, 30, 30);
		neuerLinkKnopf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				neuAktion();
			}
		});

		auswahlLinkKnopf.setBounds(387, 252, 30, 30);
		auswahlLinkKnopf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				auswahlAktion();
			}
		});

		// Fuehrungstext ueber Tabelle
		kundeFuehrungstext.setBounds(24, 261, 80, 30);
		kundeFuehrungstext.setText("Kunde");
		{
			kundeTextfeld = neuesTextfeld();
			anzeigenFlaecheJ.add(kundeTextfeld);
			// kundeTextfeld.setText(dieAnzeige.getTitel());
			kundeTextfeld.setBackground(new java.awt.Color(255, 255, 159));
			kundeTextfeld.setBounds(108, 261, 232, 24);
			kundeTextfeld.setEditable(false);
			anzeigenFlaecheJ.add(auswahlLinkKnopf);
			anzeigenFlaecheJ.add(neuerLinkKnopf);
		}

		// Knoepfe fuer die elementaren Operationen (OK,Uebernehmen,...)
		okDruckknopfJ.setText("OK");
		getContentPane().add(okDruckknopfJ);
		okDruckknopfJ.setBackground(java.awt.Color.lightGray);
		okDruckknopfJ.setBounds(17, 317, 88, 30);
		okDruckknopfJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				okAktion();
			}
		});

		uebernehmenKnopfJ.setText("Uebernehmen");
		getContentPane().add(uebernehmenKnopfJ);
		uebernehmenKnopfJ.setBackground(java.awt.Color.lightGray);
		uebernehmenKnopfJ.setBounds(110, 317, 130, 30);
		uebernehmenKnopfJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uebernehmenAktion();
			}
		});

		abbrechenKnopfJ.setText("Abbrechen");
		getContentPane().add(abbrechenKnopfJ);
		abbrechenKnopfJ.setBackground(java.awt.Color.lightGray); // AbstandKnopfe=5,
		// textknopfbreite=110
		abbrechenKnopfJ.setBounds(245, 317, 110, 29);
		abbrechenKnopfJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abbrechenAktion();
			}
		});
	}

	public static Anzeigenfenster anzeigenAnzeigefenster(
			JFrame dasAnwendungsfenster, String Fenstertitel,
			Anzeige einAnzeige, Kunde einKunde) {
		if (dasAnzeigefenster == null)
			dasAnzeigefenster = new Anzeigenfenster(dasAnwendungsfenster,
					Fenstertitel);
		// Konstruktor kann nur einmal aufgerufen werden
		dasAnzeigefenster.setTitle(Fenstertitel);
		dasAnzeigefenster.setSize(461, 389);
		if (einKunde != null)// Ein Kunde wurde zum Verlinken uebergeben
		{
			uebergebenerKunde = einKunde;
			dasAnzeigefenster.setModal(true);
            dasAnzeigefenster.neuerLinkKnopf.setEnabled(false);
            dasAnzeigefenster.auswahlLinkKnopf.setEnabled(false);
		} else// Kein Kunde wurde zum Verlinken uebergeben
		{
			uebergebenerKunde = null;
			dasAnzeigefenster.setModal(false); dasAnzeigefenster.neuerLinkKnopf.setEnabled(true);
            dasAnzeigefenster.neuerLinkKnopf.setEnabled(true);
            dasAnzeigefenster.auswahlLinkKnopf.setEnabled(true);
		}
		dasAnzeigefenster.zeigen(einAnzeige); // Fenster nach vorne holen
		dasAnzeigefenster.kundeInitial = dieAnzeige.getKunde(); // speichere
		// Anfangsverbindung
		return dasAnzeigefenster;
	}

	public void zeigen(Anzeige einAnzeige) 
{
		if (einAnzeige == null)// Es soll ein neuer Anzeige angelegt werden
		{
			dieAnzeige = new Anzeige();
			int anzeigenZahl = Anzeigenverwaltung.getObjektreferenz()
					.getAnzeigenzahl();
			dieAnzeige.setNummer(anzeigenZahl + 1);
			dasAnzeigefenster.setTitle("Neu Anzeige");
		} else // Es soll ein Anzeige geaendert werden
		{
			dieAnzeige = einAnzeige;
			dasAnzeigefenster.setTitle("Aendern " + dieAnzeige.getTitel());
		}

		// Alle Felder auf die entsprechenden Attributwerte von derAnzeige
		// setzen
		nrTextfeldJ.setText(dieAnzeige.getNummer() + "");
		titelTextfeldJ.setText(dieAnzeige.getTitel());
		beschreibungTextbereichJ.setText(dieAnzeige.getBeschreibung());
		preisTextfeldJ.setText(Integer.toString(dieAnzeige.getPreis()));
		rubrikTextfeldJ.setText(dieAnzeige.getRubrik());
		Kunde kunde = dieAnzeige.getKunde();
	kundeTextfeld.setText(kunde != null ? kunde.getAnzeigename() : 
        	(uebergebenerKunde != null ? uebergebenerKunde.getAnzeigename() : ""));

		// Das Anzeigefenster sichtbar machen
		dasAnzeigefenster.setVisible(true);
	}

	// liefert die bearbeitete Anzeige
	public static Anzeige getAnzeige() 
	{
		return dieAnzeige;		
	}
	
	private void okAktion()
	{
	    if (schreibeAnzeige())  //Konnten alle Daten korrekt gelesen und
	    {                       //in derAnzeige uebernommen werden
            //dieAnzeige in AnzeigeContainer einfuegen
	    	if(this.uebergebenerKunde == null)
	    		Anzeigenverwaltung.getObjektreferenz().einfuegeAnzeige(dieAnzeige);
	        
	        //Mache das Anzeigefenster unsichtbar
	        setVisible(false);
	    }
	}

	private void uebernehmenAktion() {
		if (schreibeAnzeige()) {
			Anzeigenverwaltung.getObjektreferenz().einfuegeAnzeige(dieAnzeige);
			// Neuen Anzeige erzeugen und Eingabeelemente zuruecksetzen
			zeigen(null);
		}
	}

	// Fenster schliessen und Eingabeelemente zuruecksetzen
	private void abbrechenAktion() {
		dieAnzeige.setKundeBeidseitig(kundeInitial);
		zuruecksetzen();
		setVisible(false);
		pack();
	}

	// Einen neuen Kundeen eingeben und einen Link auf ihn setzen
	private void neuAktion() {
		Kundenfenster.anzeigenKundenfenster((JFrame) this.getParent(),
				"Neu Kunde", null, dieAnzeige);
		Kunde kunde = dieAnzeige.getKunde();
		if (kunde != null) {
			kundeTextfeld.setText(kunde.getAnzeigename());
		}
	}

	// Einen Kundeen auswaehlen zu dem ein Link aufgebaut werden soll
	private void auswahlAktion() {
		KundenAuswahl.anzeigenKundenAuswahl((JFrame) this.getParent(),
				"Auswahl Kunde", dieAnzeige);
		Kunde kunde = dieAnzeige.getKunde();
		if (kunde != null) {
			kundeTextfeld.setText(kunde.getAnzeigename());
		}
	}

	// Alle Eingaben aus dem Anzeigefenster uebernehmen und in derAnzeige
	// eintragen. War dies erfolgreich wird true zurueckgegeben, ansonsten false
	public boolean schreibeAnzeige() 
{
		String nummer = nrTextfeldJ.getText();
		String rubrik = rubrikTextfeldJ.getText();
		String titel = titelTextfeldJ.getText();
		String beschreibung = beschreibungTextbereichJ.getText();
		String preis = preisTextfeldJ.getText();

		if (nummer.length() == 0
				|| titel.length() == 0
				|| (uebergebenerKunde == null && this.kundeTextfeld.getText()
						.isEmpty()))// Wurde
		// ein
		// Mussfeld
		// nicht
		// ausgefuellt?
		{
			// Mitteilung, dass ein Mussfeld nicht ausgefuellt wurde
			OptionenDialog.zeigen(this);
			return false;
		}

		try {
			dieAnzeige.setNummer(Integer.valueOf(nummer).intValue());
			dieAnzeige.setRubrik(rubrik);
			dieAnzeige.setTitel(titel);
			dieAnzeige.setBeschreibung(beschreibung);
			dieAnzeige.setPreis(Integer.valueOf(preis).intValue());
			if (dieAnzeige.getKunde() != null
					&& dieAnzeige.getKunde() != kundeInitial) {
				if (kundeInitial != null)
					kundeInitial.removeLinkAnzeige(dieAnzeige);
				dieAnzeige.getKunde().setLinkAnzeige(dieAnzeige);
			}

			if (uebergebenerKunde != null) {
				uebergebenerKunde.setLinkAnzeige(dieAnzeige);
			}
			zuruecksetzen();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Alle Eingabefelder zuruecksetzen
	public void zuruecksetzen() {
		resetTextFelder();
	}
}