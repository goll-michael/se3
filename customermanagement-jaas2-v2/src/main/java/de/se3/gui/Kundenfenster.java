package de.se3.gui;

 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import de.se3.applikation.AdresseT;
import de.se3.applikation.Anzeige;
import de.se3.applikation.Anzeigenverwaltung;
import de.se3.applikation.BankT;
import de.se3.applikation.KontaktT;
import de.se3.applikation.Kunde;
import de.se3.applikation.NameT;
import de.se3.security.ac.swing.AcJButton;
import de.se3.security.ac.swing.AcJComboBox;
import de.se3.security.ac.swing.AcJLabel;
import de.se3.security.ac.swing.AcJPanel;
import de.se3.security.ac.swing.AcJRadioButton;
import de.se3.security.ac.swing.AcJScrollPane;
import de.se3.security.ac.swing.AcJTable;
import de.se3.security.ac.swing.AcJTextField;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 Das Kundenfenster
*/
public class Kundenfenster extends Unterfenster
{
	//Attribute
	//Referenz auf das einzige Objekt
	private static Kundenfenster dasKundenfenster = null;
	//Der Kunde, der gerade bearbeitet wird
	private static Kunde derKunde;
	//Die Anzeige zu dem eine Assoziation hergstellt werden soll
	private static Anzeige dieAnzeige;
	private Font meineSchrift = new Font("Dialog", Font.PLAIN, 12);
	
	private AnzeigenLinkModell einTabellenModell;

	private ArrayList<Anzeige> entfernteLinks = new ArrayList<Anzeige>();
	private ArrayList<Anzeige> zugefuegteLinks = new ArrayList<Anzeige>();
	 
    //Oberflaechenelemente
    //Alle Fuehrungstexte
	private AcJLabel plzortFuehrungstext    = new AcJLabel("Kundenfenster-plzortFuehrungstext ");
	private AcJLabel strasseFuehrungstextJ  = new AcJLabel("Kundenfenster-strasseFuehrungstextJ");
	private AcJLabel vornameFuehrungstextJ  = new AcJLabel("Kundenfenster-vornameFuehrungstextJ");
	private AcJLabel nachnameFuehrungstextJ = new AcJLabel("Kundenfenster-nachnameFuehrungstextJ");
	private AcJLabel anredeFuehrungstextJ   = new AcJLabel("Kundenfenster-anredeFuehrungstextJ");
	private AcJLabel kundennrFuehrungstextJ = new AcJLabel("Kundenfenster-kundennrFuehrungstextJ");
        private AcJLabel anzeigeLabel           = new AcJLabel("Kundenfenster-anzeigeLabel", "Anzeige");

    //Alle Textfelder
	private AcJTextField kundenNrFeld      = neuesTextfeld();
	private AcJTextField strasseTextfeldJ  = neuesTextfeld();
	private AcJTextField plzTextfeldJ      = neuesTextfeld();
	private AcJTextField ortTextfeldJ      = neuesTextfeld();
	private AcJTextField vornameTextfeldJ  = neuesTextfeld();
	private AcJTextField nachnameTextfeldJ = neuesTextfeld();

	//Alle Flaechen
	private AcJPanel nameFlaecheJ = new AcJPanel("Kundenfenster-nameFlaecheJ");
	private AcJTextField kontonrTextfeld;
	private AcJLabel kontonrFuehrungstext;
	private AcJTextField blzTextfeld;
	private AcJLabel blzFuehrungstext;
	private AcJTextField bankTextfeld;
	private AcJLabel bankFuehrungstext;
	private AcJTextField telefaxTextfeld;
	private AcJLabel telefaxFuehrungstext;
	private AcJTextField telefonTextfeld;
	private AcJLabel telefonFuehrungstext;
	private AcJLabel emailFuehrungstext;
	private AcJTextField emailTextfeld;
	private AcJTextField agbTextfeld;
	private AcJLabel agbFuehrungstext;
	private AcJPanel bankFlaecheJ   = new AcJPanel("Kundenfenster-bankFlaecheJ");
	private AcJPanel adresseFlaecheJ= new AcJPanel("Kundenfenster-adresseFlaecheJ");
	
	private AcJPanel kontaktFlaecheJ= new AcJPanel("Kundenfenster-kontaktFlaecheJ");
	private AcJComboBox landCombobox;
	private AcJLabel landFuehrungstext;
	private AcJPanel anredeFlaecheJ = new AcJPanel("Kundenfenster-anredeFlaecheJ");
	private AcJTextField hausnrTextfeld;

    //Alle Auswahlknoepfe
	private AcJRadioButton herrOptionsfeldJ = new AcJRadioButton("Kundenfenster-herrOptionsfeldJ");
	private AcJRadioButton frauOptionsfeldJ = new AcJRadioButton("Kundenfenster-herrOptionsfeldJ");

    //Alle Kombinationsfelder

    //Alle Druckknoepfe
	private AcJButton uebernehmenKnopfJ = new AcJButton("Kundenfenster-uebernehmenKnopfJ");
	private AcJButton abbrechenKnopfJ   = new AcJButton("Kundenfenster-abbrechenKnopfJ");
	private AcJButton okDruckknopfJ     = new AcJButton("Kundenfenster-okDruckknopfJ");
  	private AcJButton neuerLinkKnopf    = new AcJButton("Kundenfenster-neuerLinkKnopf");
    private AcJButton loeschenLinkKnopf = new AcJButton("Kundenfenster-loeschenLinkKnopf");

    //Alle Tabellen
	private AcJTable anzeigenTabelle = new AcJTable("Kundenfenster-anzeigenTabelle");

    //Alle Knopfgruppen
	private ButtonGroup anredeGruppe = new ButtonGroup();

    //Alle Rahmen
    //private Border AnsprechpartnerUmrandung;
	//private Border FirmaUmrandung;

    //Alle Rollfelder
    private AcJScrollPane einRollfeld;
	
    // --- privater Konstruktor (Singleton-Pattern) ---
	private Kundenfenster(JFrame dasAnwendungsfenster, String Fenstertitel) 
	{
		super(dasAnwendungsfenster, Fenstertitel);		
        	
	    // Layout, Hintergrund und Groesse des obersten Containers setzen
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.lightGray);
        
        //Textfelder und Fuehrungstexte
		plzortFuehrungstext.setText("PLZ/Ort");
		plzortFuehrungstext.setFont(meineSchrift);
		plzortFuehrungstext.setBounds(13, 47, 72, 24);

		getContentPane().add(kundenNrFeld);
		kundenNrFeld.setBackground(new Color(255,255,159));
		kundenNrFeld.setBounds(120,20,73,24);
		kundenNrFeld.setEditable(false);
		
		//FirmaUmrandung = BorderFactory.createTitledBorder("Firma");
		//AnsprechpartnerUmrandung = BorderFactory.createTitledBorder("Ansprechpartner");

		strasseTextfeldJ.setBackground(java.awt.Color.white);
		strasseTextfeldJ.setBounds(112, 12, 204, 24);

		strasseFuehrungstextJ.setText("Strasse/Hausnr");
		strasseFuehrungstextJ.setFont(meineSchrift);
		strasseFuehrungstextJ.setBounds(12, 11, 100, 24);

		plzTextfeldJ.setBackground(Color.white);
		plzTextfeldJ.setBounds(112, 48, 60, 24);

		ortTextfeldJ.setBackground(java.awt.Color.white);
		ortTextfeldJ.setBounds(178, 48, 204, 24);
		//nameFlaecheJ.setBorder(AnsprechpartnerUmrandung);
		nameFlaecheJ.setLayout(null);
		getContentPane().add(nameFlaecheJ);
		nameFlaecheJ.setBackground(new java.awt.Color(226,226,226));
		nameFlaecheJ.setBounds(24, 55, 397, 103);

		nameFlaecheJ.add(vornameTextfeldJ);
		vornameTextfeldJ.setBackground(Color.white);
		vornameTextfeldJ.setBounds(111, 45, 276, 24);
		vornameFuehrungstextJ.setText("Vorname");
		nameFlaecheJ.add(vornameFuehrungstextJ);
		vornameFuehrungstextJ.setFont(meineSchrift);
		vornameFuehrungstextJ.setBounds(12, 43, 72, 24);
		
		nameFlaecheJ.add(nachnameTextfeldJ);
        nachnameTextfeldJ.setBackground(java.awt.Color.white);
		nachnameTextfeldJ.setBounds(111, 74, 274, 24);
		nachnameFuehrungstextJ.setText("Nachname");
		nameFlaecheJ.add(nachnameFuehrungstextJ);
		nachnameFuehrungstextJ.setFont(meineSchrift);
		nachnameFuehrungstextJ.setBounds(12, 73, 72, 24);
		anredeFuehrungstextJ.setText("Anrede");
		
		nameFlaecheJ.add(anredeFuehrungstextJ);
		nameFlaecheJ.add(anredeFlaecheJ);
				
		anredeFuehrungstextJ.setFont(meineSchrift);
		anredeFuehrungstextJ.setBounds(12, 15, 48, 30);
		anredeFlaecheJ.setLayout(null);

		anredeFlaecheJ.setBackground(new Color(226,226,226));
		anredeFlaecheJ.setBounds(111,16,120,30);
		
		anredeGruppe.add(herrOptionsfeldJ);
		herrOptionsfeldJ.setText("Herr");
		herrOptionsfeldJ.setSelected(true);
        anredeFlaecheJ.add(herrOptionsfeldJ);
		herrOptionsfeldJ.setFont(meineSchrift);
		herrOptionsfeldJ.setBounds(-2, -9, 50, 30);
		
		anredeGruppe.add(frauOptionsfeldJ);
		frauOptionsfeldJ.setText("Frau");
        anredeFlaecheJ.add(frauOptionsfeldJ);
		frauOptionsfeldJ.setFont(meineSchrift);
		frauOptionsfeldJ.setBounds(47, -9, 50, 30);

		//adresseFlaecheJ.setBorder(FirmaUmrandung);
		adresseFlaecheJ.setLayout(null);
		getContentPane().add(adresseFlaecheJ);
		adresseFlaecheJ.setBackground(new Color(226,226,226));
		adresseFlaecheJ.setBounds(24, 170, 397, 116);
		adresseFlaecheJ.add(strasseFuehrungstextJ);
		adresseFlaecheJ.add(plzortFuehrungstext);
		adresseFlaecheJ.add(strasseTextfeldJ);
		adresseFlaecheJ.add(plzTextfeldJ);
		adresseFlaecheJ.add(ortTextfeldJ);
		{
			landFuehrungstext = new AcJLabel("Kundenfenster-landFuehrungstext");
			adresseFlaecheJ.add(landFuehrungstext);
			landFuehrungstext.setText("Land");
			landFuehrungstext.setFont(meineSchrift);
			landFuehrungstext.setBounds(12, 83, 84, 24);
		}
		{
			ComboBoxModel landComboboxModel = 
				new DefaultComboBoxModel(AdresseT.Land.values());
			landCombobox = new AcJComboBox("Kundenfenster-landCombobox");
			adresseFlaecheJ.add(landCombobox);
			landCombobox.setModel(landComboboxModel);
			landCombobox.setBounds(114, 84, 129, 23);
		}
		{
			hausnrTextfeld = neuesTextfeld();
			adresseFlaecheJ.add(hausnrTextfeld);
			hausnrTextfeld.setBounds(334, 12, 48, 23);
		}

		kundennrFuehrungstextJ.setText("Kundennr.");
		getContentPane().add(kundennrFuehrungstextJ);
		kundennrFuehrungstextJ.setFont(meineSchrift);
		kundennrFuehrungstextJ.setBounds(24,20,84,24);

		//Knoepfe zum Aufbauen der Links
		getContentPane().add(neuerLinkKnopf);
		neuerLinkKnopf.setBounds(396, 544, 30, 30);
		getContentPane().add(loeschenLinkKnopf);
		loeschenLinkKnopf.setBounds(437, 544, 30, 30);

	    //Fuehrungstext ueber Tabelle
	    getContentPane().add(anzeigeLabel);
	    anzeigeLabel.setBounds(12,403,60,30);

	    //Fuehrungstext ueber Tabelle
	    getContentPane().add(anzeigeLabel);
	    anzeigeLabel.setBounds(12, 549, 80, 30);
	    anzeigeLabel.setText("Anzeigen");
	    {
	    	agbFuehrungstext = new AcJLabel("Kundenfenster-agbFuehrungstext");
	    	getContentPane().add(agbFuehrungstext);
	    	agbFuehrungstext.setText("AGB akzeptiert am (TT.MM.JJJJ)");
	    	agbFuehrungstext.setBounds(24, 514, 193, 16);
	    }
	    {
	    	agbTextfeld = neuesTextfeld();
	    	getContentPane().add(agbTextfeld);
	    	agbTextfeld.setBounds(216, 511, 135, 23);
	    }
	    {
	    	bankFlaecheJ = new AcJPanel("Kundenfenster-bankFlaecheJ");
	    	getContentPane().add(bankFlaecheJ);
	    	bankFlaecheJ.setBackground(new Color(226,226,226));
	    	//bankPanel.setBorder(FirmaUmrandung);
	    	bankFlaecheJ.setBounds(24, 397, 397, 102);
	    	bankFlaecheJ.setLayout(null);
	    	{
	    		bankFuehrungstext = new AcJLabel("Kundenfenster-bankFuehrungstext");
	    		bankFlaecheJ.add(bankFuehrungstext);
	    		bankFuehrungstext.setText("Bank");
	    		bankFuehrungstext.setBounds(12, 12, 68, 16);
	    	}
	    	{
	    		bankTextfeld = neuesTextfeld();
	    		bankFlaecheJ.add(bankTextfeld);
	    		bankTextfeld.setBounds(115, 9, 195, 23);
	    	}
	    	{
	    		blzFuehrungstext = new AcJLabel("Kundenfenster-blzFuehrungstext");
	    		bankFlaecheJ.add(blzFuehrungstext);
	    		blzFuehrungstext.setText("BLZ");
	    		blzFuehrungstext.setBounds(12, 45, 68, 16);
	    	}
	    	{
	    		blzTextfeld = neuesTextfeld();
	    		bankFlaecheJ.add(blzTextfeld);
	    		blzTextfeld.setBounds(115, 42, 195, 23);
	    	}
	    	{
	    		kontonrFuehrungstext = new AcJLabel("Kundenfenster-kontonrFuehrungstext");
	    		bankFlaecheJ.add(kontonrFuehrungstext);
	    		kontonrFuehrungstext.setText("Kontonummer");
	    		kontonrFuehrungstext.setBounds(12, 73, 91, 16);
	    	}
	    	{
	    		kontonrTextfeld = neuesTextfeld();
	    		bankFlaecheJ.add(kontonrTextfeld);
	    		kontonrTextfeld.setBounds(115, 70, 195, 23);
	    	}
	    }
	    {
	    	kontaktFlaecheJ = new AcJPanel("Kundenfenster-kontaktFlaecheJ");
	    	getContentPane().add(kontaktFlaecheJ);
	    	kontaktFlaecheJ.setBackground(new Color(226,226,226));
	    	//addressPanel.setBorder(FirmaUmrandung);
	    	kontaktFlaecheJ.setBounds(24, 298, 397, 87);
	    	kontaktFlaecheJ.setLayout(null);
	    	{
	    		emailFuehrungstext = new AcJLabel("Kundenfenster-emailFuehrungstext");
	    		kontaktFlaecheJ.add(emailFuehrungstext);
	    		emailFuehrungstext.setText("E-Mail");
	    		emailFuehrungstext.setBounds(12, 7, 76, 16);
	    	}
	    	{
	    		emailTextfeld = neuesTextfeld();
	    		kontaktFlaecheJ.add(emailTextfeld);
	    		emailTextfeld.setBounds(115, 4, 198, 23);
	    	}
	    	{
	    		telefonFuehrungstext = new AcJLabel("Kundenfenster-telefonFuehrungstext");
	    		kontaktFlaecheJ.add(telefonFuehrungstext);
	    		telefonFuehrungstext.setText("Telefon");
	    		telefonFuehrungstext.setBounds(12, 35, 76, 16);
	    	}
	    	{
	    		telefonTextfeld = neuesTextfeld();
	    		kontaktFlaecheJ.add(telefonTextfeld);
	    		telefonTextfeld.setBounds(115, 32, 198, 23);
	    	}
	    	{
	    		telefaxFuehrungstext = new AcJLabel("Kundenfenster-telefaxFuehrungstext");
	    		kontaktFlaecheJ.add(telefaxFuehrungstext);
	    		telefaxFuehrungstext.setText("Telefax");
	    		telefaxFuehrungstext.setBounds(12, 63, 76, 16);
	    	}
	    	{
	    		telefaxTextfeld = neuesTextfeld();
	    		kontaktFlaecheJ.add(telefaxTextfeld);
	    		telefaxTextfeld.setBounds(115, 60, 198, 23);
	    	}
	    }

	    einTabellenModell = new AnzeigenLinkModell();
		anzeigenTabelle.setModel(einTabellenModell);
		
		//Wie sollen die Inhalte der einzelnen Spalten angeordnet werden
	    TableColumn NummernSpalte = anzeigenTabelle.getColumn("Anzeigen-Nr.");
        DefaultTableCellRenderer einNummernAusrichter = new DefaultTableCellRenderer();
        //Anzeige-Nr.: rechts
        einNummernAusrichter.setHorizontalAlignment(AcJLabel.RIGHT);
        NummernSpalte.setCellRenderer(einNummernAusrichter);

	    TableColumn Preisspalte = anzeigenTabelle.getColumn("Preis");
        DefaultTableCellRenderer einPreisAusrichter = new DefaultTableCellRenderer();
        //Verkaufspreis: zentriert
        einPreisAusrichter.setHorizontalAlignment(AcJLabel.CENTER);
        Preisspalte.setCellRenderer(einPreisAusrichter);
		anzeigenTabelle.setToolTipText("Es koennen nur Zeilen selektiert werden");
	    //Zeilen selektieren erlaubt
	    anzeigenTabelle.setRowSelectionAllowed(true);
	    //Einbettung der Tabellenansicht in ein Rollfeld
	    einRollfeld = new AcJScrollPane("Kundenfenster-einRollfeld", anzeigenTabelle);
	    einRollfeld.setBounds(12, 579, 455, 100);
	    //Hinzufuegen des Rollfelds zum Fenster
	    getContentPane().add(einRollfeld);


        //Knoepfe fuer die elementaren Operationen (OK,Uebernehmen,...)
		okDruckknopfJ.setText("OK");
		getContentPane().add(okDruckknopfJ);
		okDruckknopfJ.setBackground(java.awt.Color.lightGray);
		okDruckknopfJ.setBounds(12, 689, 110, 30);
		
		uebernehmenKnopfJ.setText("Uebernehmen");
		getContentPane().add(uebernehmenKnopfJ);
		uebernehmenKnopfJ.setBackground(java.awt.Color.lightGray);
		uebernehmenKnopfJ.setBounds(127, 689, 130, 30);

		abbrechenKnopfJ.setText("Abbrechen");
		getContentPane().add(abbrechenKnopfJ);
		abbrechenKnopfJ.setBackground(java.awt.Color.lightGray);
		abbrechenKnopfJ.setBounds(262, 689, 110, 30);
		
		// Abhoerer
	    okDruckknopfJ.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent arg0) 
			{
				okAktion();
			}
	    });
	    uebernehmenKnopfJ.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent arg0) 
			{
				uebernehmenAktion();
			}
	    });
	    abbrechenKnopfJ.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent arg0) 
			{
				abbrechenAktion();
			}
	    });
	    neuerLinkKnopf.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent arg0) 
			{
				neuAktion();
			}
	    });
		loeschenLinkKnopf.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent arg0) 
			{
				loeschenAktion();
			}
	    });
		herrOptionsfeldJ.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent arg0) 
			{}
	    });
		frauOptionsfeldJ.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent arg0) {}
	    });
	}
	
	
	public static Kundenfenster anzeigenKundenfenster(JFrame dasAnwendungsfenster, String Fenstertitel, Kunde einKunde, Anzeige eineAnzeige)
	{
	    if (dasKundenfenster == null)
	        dasKundenfenster = new Kundenfenster(dasAnwendungsfenster, Fenstertitel);
	        //Konstruktor kann nur einmal aufgerufen werden
	    dasKundenfenster.setTitle(Fenstertitel);
	    if (eineAnzeige != null)//Wurde ein Anzeige ubergeben, um einen Link aufzubauen
        {
            dieAnzeige = eineAnzeige;
            dasKundenfenster.setModal(true);
        }
        else//Kein Anzeige wurde uebergeben
        {
        	dieAnzeige = null;
            dasKundenfenster.setModal(false);
        }
	    //Groesse des Fensters setzen
		dasKundenfenster.setSize(487, 761);
		
		dasKundenfenster.entfernteLinks.clear();
		
        dasKundenfenster.zeigen(einKunde); //Fenster nach vorne holen
	    return dasKundenfenster;
	}
	

	//Fenster initialisieren und sichtbar machen
	public void zeigen(Kunde einKunde)
	{
		if (einKunde == null)
		{
	        derKunde = new Kunde();
	        int kundennr = Anzeigenverwaltung.getObjektreferenz().getNaechsteKundenNr();
	        derKunde.setNummer(kundennr);
	        dasKundenfenster.setTitle("Neu Kunde");
	        //Groesse des Fensters setzen
			dasKundenfenster.setSize(487, 761);
	        zuruecksetzen();
	    }
        else//Es wurde ein Kunde zum Veraendern uebergeben
        {
            derKunde = einKunde;
            dasKundenfenster.setTitle("Aendern des Kunden:"+derKunde.getAnzeigename());
            
            //name
            if (derKunde.getName().getAnrede() == NameT.Anrede.HERR)
	        {
	           herrOptionsfeldJ.setSelected(true);
	        }
	        else
	        {
	            frauOptionsfeldJ.setSelected(true);
	        }
            vornameTextfeldJ.setText (derKunde.getName().getVorname());
            nachnameTextfeldJ.setText(derKunde.getName().getNachname());
            
	        // adresse
            plzTextfeldJ.setText(derKunde.getAdresse().getPlz());
            ortTextfeldJ.setText(derKunde.getAdresse().getOrt());
            hausnrTextfeld.setText(derKunde.getAdresse().getHausnummer());
            strasseTextfeldJ.setText(derKunde.getAdresse().getStrasse());
            landCombobox.setSelectedItem(derKunde.getAdresse().getLand());
            
	        // kontakt
            emailTextfeld.setText(derKunde.getKontakt().getEmail());
            telefonTextfeld.setText(derKunde.getKontakt().getTelefon());
            telefaxTextfeld.setText(derKunde.getKontakt().getFax());
            
            //bank
	        blzTextfeld.setText(Integer.toString(derKunde.getBank().getBlz()));
            bankTextfeld.setText(derKunde.getBank().getName());
            kontonrTextfeld.setText(Integer.toString(derKunde.getBank().getKontonummer()));
            
	        //agb
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            agbTextfeld.setText(df.format(derKunde.getAgbBestaetigt()));
	    }
		 
	    einTabellenModell.setKunde(derKunde);
        
	   //Das Tabellenmodell und damit die Ansicht aktualisieren
        einTabellenModell.update();
        //Alle Eingabeelemente auf die Attributwerte von derKunde setzen
        this.kundenNrFeld.setText(Integer.toString(derKunde.getNummer()));
		
  	    
        //Das Fenster sichtbar machen
	    dasKundenfenster.setVisible(true);
	}
	


    //Alle Daten uebernehmen und Fenster schliessen
	private void okAktion()
	{
	    if (schreibeKunde())//Konnten alle Daten uebernommen werden?
	    {
	        Anzeigenverwaltung.getObjektreferenz().einfuegeKunde(derKunde);
	        
	        setVisible(false);
	    }
	}
	
	//Alle Daten uebernehmen und Eingabeelemente zuruecksetzen
	private void uebernehmenAktion()
	{
	    if (schreibeKunde())
	    {
	        Anzeigenverwaltung.getObjektreferenz().einfuegeKunde(derKunde);

	        zeigen(null);
	    }
	}
	
	//Fenster schliessen
	private void abbrechenAktion()
	{
	    zuruecksetzen();
	    
	    // entfernte links wieder hinzuf�gen
	    Iterator<Anzeige> iter = entfernteLinks.iterator();
        while(iter.hasNext()) {
        	derKunde.setLinkAnzeige(iter.next());
        }
	    
        iter = zugefuegteLinks.iterator();
        while(iter.hasNext()) {
        	derKunde.removeLinkAnzeige(iter.next());
        }
        
	    setVisible(false);
	    pack();
	}
	
	//Einen neuen Anzeige erzeugen und einen Link zu ihm aufbauen
	private void neuAktion()
	{
	    Anzeigenfenster.anzeigenAnzeigefenster((JFrame)this.getParent(),"Neu Anzeige",null,derKunde);
	    if(Anzeigenfenster.getAnzeige() != null)
	    	this.zugefuegteLinks.add(Anzeigenfenster.getAnzeige());
	    einTabellenModell.update();
	}
	
	//Einen Link auf einen ausgewaehlten Anzeige loeschen
	private void loeschenAktion()
	{
  	    try
        {
            int Zeile = anzeigenTabelle.getSelectedRow();
            if (Zeile >-1)
            {
                Anzeige eineAnzeige = (Anzeige) derKunde.getAnzeigen().get(Zeile);
                derKunde.removeLinkAnzeige(eineAnzeige);
                einTabellenModell.update();
                entfernteLinks.add(eineAnzeige);
            }
        }
        catch (Exception e)
        {
        }

	}
	
	//Alle Daten aus den Eingabeelementen uebernehmen.
	//Gibt true zurueck, falls erfolgreich ansonsten false.
	public boolean schreibeKunde()
	{
	    String nummer = kundenNrFeld.getText();
	    String nachname = nachnameTextfeldJ.getText();
	    
	    if (nummer.length()==0||nachname.length()==0)
	    {
	        OptionenDialog.zeigen(this);
	        return false;
	    }
	    
	    try
        {
	    	//nummer
	    	derKunde.setNummer(Integer.valueOf(nummer));
	    	// name
	        NameT.Anrede eineAnrede = 
	        	herrOptionsfeldJ.isSelected() ? 
	        			NameT.Anrede.HERR : NameT.Anrede.FRAU;  	        
	        NameT name = new NameT();
	        name.setVorname(this.vornameTextfeldJ.getText());
	        name.setNachname(this.nachnameTextfeldJ.getText());
	        name.setAnrede(eineAnrede);
	        derKunde.setName(name);
	        // adresse
	        AdresseT adresse = new AdresseT();
	        adresse.setPlz(this.plzTextfeldJ.getText());
	        adresse.setOrt(this.ortTextfeldJ.getText());
	        AdresseT.Land land = (AdresseT.Land)this.landCombobox.getSelectedItem();
	        adresse.setLand(land);
	        adresse.setHausnummer(this.hausnrTextfeld.getText());
	        adresse.setStrasse(this.strasseTextfeldJ.getText());
	        derKunde.setAdresse(adresse);
	        // kontakt
	        KontaktT kontakt = new KontaktT();
	        kontakt.setEmail(this.emailTextfeld.getText());
	        kontakt.setTelefon(this.telefonTextfeld.getText());
	        kontakt.setFax(this.telefaxTextfeld.getText());
	        derKunde.setKontakt(kontakt);
	        //bank
	        BankT bank = new BankT();
	        bank.setBlz(Integer.valueOf(this.blzTextfeld.getText()));
	        bank.setName(this.bankTextfeld.getText());
	        bank.setKontonummer(Integer.valueOf(this.kontonrTextfeld.getText()));
	        derKunde.setBank(bank);
	        //agb
	        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	        Date date = df.parse(this.agbTextfeld.getText());
	        derKunde.setAgbBestaetigt(date);
	        
	        
	        if (dieAnzeige != null)
	        {
	        	dieAnzeige.setKunde(derKunde);
	        }
	        
	        // setze andere Seiten der Assos:
	        ArrayList<Anzeige> kundeAnzeigen = derKunde.getAnzeigen();
	        Iterator<Anzeige> iter = kundeAnzeigen.iterator();
	        while(iter.hasNext())
	        {
	        	Anzeige anzeige = iter.next();
	        	if(anzeige.getKunde() != derKunde) 
	        	{
	        		if(anzeige.getKunde() != null) 
	        		   anzeige.getKunde().removeLinkAnzeige(anzeige);
	        	    anzeige.setKunde(derKunde);
	        	}
	        	// sicherstellen, dass es in obj.container ist
	        	Anzeigenverwaltung.getObjektreferenz().einfuegeAnzeige(anzeige);
	        }
	        iter = entfernteLinks.iterator();
	        while(iter.hasNext()) {
	        	Anzeigenverwaltung.getObjektreferenz().entferneAnzeige(iter.next());
	        }
	        
            zuruecksetzen();	        
	        return true;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	        return false;
	    }
	}
    
    //Alle Eingabeelemente zuruecksetzen
    public void zuruecksetzen()
    {
    	resetTextFelder();
	    herrOptionsfeldJ.setSelected(true);
	    this.landCombobox.setSelectedIndex(0);
	    
	    einTabellenModell.update();
   }
}