package client.gui;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import server.schnittstelle.fachkonzept.Anzeige;
import server.schnittstelle.fachkonzept.Kunde;
import client.application.AnzeigenverwaltungDelegate;

/**
 * Unterfenster für die Bearbeitung einer Anzeige
 */
public class UnterfensterAnzeige extends Unterfenster 
{
	private static final long serialVersionUID = 1L;

	private AnzeigenverwaltungDelegate anzeigenverwaltung = AnzeigenverwaltungDelegate.getInstance();
	
	private Anzeige dieAnzeige;
	
	private JTextField rubrikTextfeldJ;

	// Oberflaechenelemente
	// Alle Flaechen
	private JPanel anzeigenFlaecheJ = new JPanel();
	private JLabel nrFuehrungstext = new JLabel();
	private JLabel titelFuehrungstext = new JLabel();
	private JLabel beschreibungFuehrungstext = new JLabel();
	private JLabel preisFuehrungstext = new JLabel();
	private JLabel kundeFuehrungstext = new JLabel("Kunden");

	// Alle Textfelder
	private JTextField nrTextfeldJ    = neuesTextfeld();
	private JTextField titelTextfeldJ = neuesTextfeld();
	private JTextField preisTextfeldJ = neuesTextfeld();

	// Alle Textbereiche
	private JTextArea beschreibungTextbereichJ = new JTextArea();

	// Alle Druckknoepfe
	private JButton okDruckknopfJ = new JButton();
	private JLabel rubrikFuehrungstext;
	private JComboBox kundeCombobox;
	private JButton uebernehmenKnopfJ = new JButton();
	
	// Alle Rahmen
	private Border anzeigenRahmen;

	// Alle Tabellen

	// privater Konstruktor
	public UnterfensterAnzeige(Anzeige eineAnzeige) 
	{
		super();
		// Layoutmanager auf null setzen
		setLayout(null);
		// Zunaechst unsichtbar machen
		setVisible(false);
		// Die Anzeigeflaeche mit einem Rahmen versehen
		anzeigenRahmen = BorderFactory.createTitledBorder("Anzeige");
		anzeigenFlaecheJ.setBorder(anzeigenRahmen);
		// Den Layoutmanager der Anzeigeflaeche auf null setzen
		anzeigenFlaecheJ.setLayout(null);
		// Die Anzeigeflaeche der Inhaltsflaeche hinzufuegen
		add(anzeigenFlaecheJ);
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
		macheZuPflichtfeld(nrTextfeldJ);
		nrTextfeldJ.setBounds(108, 24, 95, 24);
		nrTextfeldJ.setEditable(false);
		
		anzeigenFlaecheJ.add(titelTextfeldJ);
		macheZuPflichtfeld(titelTextfeldJ);
		titelTextfeldJ.setBounds(108, 93, 232, 24);

		anzeigenFlaecheJ.add(beschreibungTextbereichJ);
		beschreibungTextbereichJ.setBounds(24, 147, 316, 72);
		macheZuPflichtfeld(beschreibungTextbereichJ);

		anzeigenFlaecheJ.add(preisTextfeldJ);
		{
			rubrikFuehrungstext = new JLabel();
			anzeigenFlaecheJ.add(rubrikFuehrungstext);
			rubrikFuehrungstext.setText("Rubrik");
			rubrikFuehrungstext.setBounds(24, 61, 78, 26);
		}
		{
			rubrikTextfeldJ = neuesTextfeld();
			anzeigenFlaecheJ.add(rubrikTextfeldJ);
			rubrikTextfeldJ.setText("");
			macheZuPflichtfeld(rubrikTextfeldJ);
			rubrikTextfeldJ.setBounds(108, 61, 232, 24);
			anzeigenFlaecheJ.add(kundeFuehrungstext);
		}
		preisTextfeldJ.setBounds(108, 231, 89, 24);
		macheZuPflichtfeld(preisTextfeldJ);
	
		// Fuehrungstext ueber Tabelle
		kundeFuehrungstext.setBounds(24, 261, 80, 30);
		kundeFuehrungstext.setText("Kunde");
		{
			try
			{
				List<Kunde> kunden = (List<Kunde>)anzeigenverwaltung.getKunden();
				String[] kundenNamen = new String[kunden.size()];
				for(int i = 0; i < kunden.size(); i++)
					kundenNamen[i] = kunden.get(i).getAnzeigename();
				kundeCombobox = new JComboBox(kundenNamen);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
			anzeigenFlaecheJ.add(kundeCombobox);
			// kundeTextfeld.setText(dieAnzeige.getTitel());
			macheZuPflichtfeld(kundeCombobox);
			kundeCombobox.setBounds(108, 261, 232, 24);
			kundeCombobox.setEditable(false);
			if(kundeCombobox.getItemCount() > 0)
				kundeCombobox.setSelectedIndex(0);

		}

		// Knoepfe fuer die elementaren Operationen (OK,Uebernehmen,...)
		okDruckknopfJ.setText("OK");
		add(okDruckknopfJ);
		okDruckknopfJ.setBounds(17, 317, 88, 30);
		okDruckknopfJ.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				okAktion();
			}
		});

		uebernehmenKnopfJ.setText("Uebernehmen");
		add(uebernehmenKnopfJ);
		uebernehmenKnopfJ.setBounds(110, 317, 130, 30);
		uebernehmenKnopfJ.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				uebernehmenAktion();
			}
		});
		
		zeigen(eineAnzeige);
	}

	public void zeigen(Anzeige eineAnzeige) 
    {
		zuruecksetzen();
		
		try
		{	
			if (eineAnzeige == null)// Es soll ein neuer Anzeige angelegt werden
			{
				dieAnzeige = anzeigenverwaltung.erstelleAnzeige();
			} 
			else // Es soll ein Anzeige geaendert werden
			{
				dieAnzeige = eineAnzeige;
			}
	
			// Alle Felder auf die entsprechenden Attributwerte von derAnzeige
			// setzen
			nrTextfeldJ.setText(dieAnzeige.getNummer() + "");
			titelTextfeldJ.setText(dieAnzeige.getTitel());
			beschreibungTextbereichJ.setText(dieAnzeige.getBeschreibung());
			preisTextfeldJ.setText(Integer.toString(dieAnzeige.getPreis()));
			rubrikTextfeldJ.setText(dieAnzeige.getRubrik());
			Kunde kunde = dieAnzeige.getKunde();
		
			// kunden wiederherstellen
			kundeCombobox.removeAllItems();
			for(Kunde k : anzeigenverwaltung.getKunden())
			{
				kundeCombobox.addItem(k.getAnzeigename());
				if(kunde != null && kunde.getNummer() == k.getNummer())
					kundeCombobox.setSelectedIndex(kundeCombobox.getItemCount() - 1); // Das zuletzt gesetzte Element auswählen
			}
			
			// Das Anzeigefenster sichtbar machen
			setVisible(true);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void okAktion()
	{
	    if (schreibeAnzeige())  //Konnten alle Daten korrekt gelesen und
	    {                       //in derAnzeige uebernommen werden
	    	try
	    	{
	    		anzeigenverwaltung.speichereAnzeige(dieAnzeige);
		        getHauptfenster().zeigeAnzeigeListe();
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    }
	}

	private void uebernehmenAktion() {
	    if (schreibeAnzeige())  //Konnten alle Daten korrekt gelesen und
	    {                       //in derAnzeige uebernommen werden
			try
	    	{
	    		anzeigenverwaltung.speichereAnzeige(dieAnzeige);
	    		zeigen(null);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
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

		if (!pflichtTextfelderAusgefuellt())
		{
			// Mitteilung, dass ein Mussfeld nicht ausgefuellt wurde
			zeigePflichtfeldFehlerMitteilung();
			return false;
		}

		try 
		{
			dieAnzeige.setNummer(Integer.valueOf(nummer).intValue());
			dieAnzeige.setRubrik(rubrik);
			dieAnzeige.setTitel(titel);
			dieAnzeige.setBeschreibung(beschreibung);
			dieAnzeige.setPreis(Integer.valueOf(preis).intValue());

			Kunde kundeAusgewaehlt = (Kunde)anzeigenverwaltung.getKunden().get(kundeCombobox.getSelectedIndex());
				
			if (dieAnzeige.getKunde() != null && dieAnzeige.getKunde().getNummer() != kundeAusgewaehlt.getNummer()) 
			{
				if(dieAnzeige.getKunde() != null)
					dieAnzeige.getKunde().removeLinkAnzeige(dieAnzeige);
			}
			dieAnzeige.setKunde(kundeAusgewaehlt);
			kundeAusgewaehlt.setLinkAnzeige(dieAnzeige);	
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Alle Eingabefelder zuruecksetzen
	public void zuruecksetzen() 
	{
		resetTextFelder();
	}
}