package client.gui;

 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import server.schnittstelle.fachkonzept.AdresseT;
import server.schnittstelle.fachkonzept.Anzeige;
import server.schnittstelle.fachkonzept.BankT;
import server.schnittstelle.fachkonzept.KontaktT;
import server.schnittstelle.fachkonzept.Kunde;
import server.schnittstelle.fachkonzept.NameT;
import client.application.AnzeigenverwaltungDelegate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Unterfenster für die Bearbeitung eines Kunden
 */
public class UnterfensterKunde extends Unterfenster
{
	private static final long serialVersionUID = 1L;

	private AnzeigenverwaltungDelegate anzeigenverwaltung = AnzeigenverwaltungDelegate.getInstance();
	
	// Der Kunde, der gerade bearbeitet wird
	private Kunde derKunde;
	private Font meineSchrift = new Font("Dialog", Font.PLAIN, 12);
	
	private ArrayList<Anzeige> zuEntfernendeLinks = new ArrayList<Anzeige>();
	private JTextField mobilEingabefeldJ = neuesTextfeld();

    // Oberflaechenelemente
    // Alle Fuehrungstexte
	private JLabel plzortFuehrungstext    = new JLabel();
	private JLabel strasseFuehrungstextJ  = new JLabel();
	private JLabel vornameFuehrungstextJ  = new JLabel();
	private JLabel nachnameFuehrungstextJ = new JLabel();
	private JLabel anredeFuehrungstextJ   = new JLabel();
	private JLabel kundennrFuehrungstextJ = new JLabel();
    private JLabel anzeigeLabel           = new JLabel("Anzeige");

    // Alle Textfelder
	private JTextField kundenNrFeld      = neuesTextfeld();
	private JTextField strasseTextfeldJ  = neuesTextfeld();
	private JTextField plzTextfeldJ      = neuesTextfeld();
	private JTextField ortTextfeldJ      = neuesTextfeld();
	private JTextField vornameTextfeldJ  = neuesTextfeld();
	private JTextField nachnameTextfeldJ = neuesTextfeld();

	// Alle Flaechen
	private JPanel nameFlaecheJ = new JPanel();
	private JTextField kontonrTextfeld;
	private JLabel kontonrFuehrungstext;
	private JTextField blzTextfeld;
	private JLabel blzFuehrungstext;
	private JTextField bankTextfeld;
	private JLabel bankFuehrungstext;
	private JTextField telefaxTextfeld;
	private JLabel telefaxFuehrungstext;
	private JTextField telefonTextfeld;
	private JLabel telefonFuehrungstext;
	private JLabel emailFuehrungstext;
	private JTextField emailTextfeld;
	private JTextField agbTextfeld;
	private JLabel agbFuehrungstext;
	private JPanel bankFlaecheJ   = new JPanel();;
	private JPanel adresseFlaecheJ= new JPanel();;
	
	private JPanel kontaktFlaecheJ= new JPanel();;
	private JComboBox landCombobox;
	private JLabel landFuehrungstext;
	private JPanel anredeFlaecheJ = new JPanel();
	private JTextField hausnrTextfeld;
  	private JLabel mobilFuehrungstextJ;
  	

    // Alle Druckknoepfe
	private JButton uebernehmenKnopfJ = new JButton();
	private JButton okDruckknopfJ     = new JButton();
  	private JButton loeschenLinkKnopf = 
  		new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/del.gif"))));
  	private JComboBox anredeAuswahlfeldJ;

    // Alle Tabellen
  	private JList anzeigenListe = new JList();
  	  
    //Alle Rollfelder
    private JScrollPane einRollfeld;
    
    private DefaultListModel listenModel = new DefaultListModel();
    
    // --- privater Konstruktor (Singleton-Pattern) ---
	public UnterfensterKunde(Kunde einKunde) 
	{
		super();
	    // Layout, Hintergrund und Groesse des obersten Containers setzen
		setLayout(null);
        
        //Textfelder und Fuehrungstexte
		plzortFuehrungstext.setText("PLZ/Ort");
		plzortFuehrungstext.setFont(meineSchrift);
		plzortFuehrungstext.setBounds(13, 47, 72, 24);

		add(kundenNrFeld);
		macheZuPflichtfeld(kundenNrFeld);
		kundenNrFeld.setBounds(120,20,73,24);
		kundenNrFeld.setEditable(false);

		macheZuPflichtfeld(strasseTextfeldJ);
		strasseTextfeldJ.setBounds(112, 12, 204, 24);
		
		strasseFuehrungstextJ.setText("Strasse/Hausnr");
		strasseFuehrungstextJ.setFont(meineSchrift);
		strasseFuehrungstextJ.setBounds(12, 11, 100, 24);

		macheZuPflichtfeld(plzTextfeldJ);
		plzTextfeldJ.setBounds(112, 48, 60, 24);

		macheZuPflichtfeld(ortTextfeldJ);
		ortTextfeldJ.setBounds(178, 48, 204, 24);
		nameFlaecheJ.setLayout(null);
		add(nameFlaecheJ);
		nameFlaecheJ.setBackground(new java.awt.Color(226,226,226));
		nameFlaecheJ.setBounds(24, 55, 397, 103);

		nameFlaecheJ.add(vornameTextfeldJ);
		vornameTextfeldJ.setBounds(111, 45, 276, 24);
		macheZuPflichtfeld(vornameTextfeldJ);
		vornameFuehrungstextJ.setText("Vorname");
		nameFlaecheJ.add(vornameFuehrungstextJ);
		vornameFuehrungstextJ.setFont(meineSchrift);
		vornameFuehrungstextJ.setBounds(12, 43, 72, 24);
		
		nameFlaecheJ.add(nachnameTextfeldJ);
		nachnameTextfeldJ.setBounds(111, 74, 274, 24);
		macheZuPflichtfeld(nachnameTextfeldJ);
		nachnameFuehrungstextJ.setText("Nachname");
		nameFlaecheJ.add(nachnameFuehrungstextJ);
		nachnameFuehrungstextJ.setFont(meineSchrift);
		nachnameFuehrungstextJ.setBounds(12, 73, 72, 24);
		anredeFuehrungstextJ.setText("Anrede");
		
		nameFlaecheJ.add(anredeFuehrungstextJ);
		nameFlaecheJ.add(anredeFlaecheJ);
				
		anredeFuehrungstextJ.setFont(meineSchrift);
		anredeFuehrungstextJ.setBounds(12, 12, 48, 30);
		anredeFlaecheJ.setLayout(null);

		anredeFlaecheJ.setBackground(new Color(226,226,226));
		anredeFlaecheJ.setBounds(111,16,120,30);
		{
			anredeAuswahlfeldJ = new JComboBox(NameT.Anrede.values());
			anredeFlaecheJ.add(anredeAuswahlfeldJ);
			anredeAuswahlfeldJ.setBounds(0, 0, 120, 21);
		}

		adresseFlaecheJ.setLayout(null);
		add(adresseFlaecheJ);
		adresseFlaecheJ.setBackground(new Color(226,226,226));
		adresseFlaecheJ.setBounds(24, 170, 397, 116);
		adresseFlaecheJ.add(strasseFuehrungstextJ);
		adresseFlaecheJ.add(plzortFuehrungstext);
		adresseFlaecheJ.add(strasseTextfeldJ);
		adresseFlaecheJ.add(plzTextfeldJ);
		adresseFlaecheJ.add(ortTextfeldJ);
		{
			landFuehrungstext = new JLabel();
			adresseFlaecheJ.add(landFuehrungstext);
			landFuehrungstext.setText("Land");
			landFuehrungstext.setFont(meineSchrift);
			landFuehrungstext.setBounds(12, 83, 84, 24);
			ComboBoxModel landComboboxModel = 
				new DefaultComboBoxModel(AdresseT.Land.values());
			landCombobox = new JComboBox();
			adresseFlaecheJ.add(landCombobox);
			landCombobox.setModel(landComboboxModel);
			landCombobox.setBounds(114, 84, 129, 23);
			macheZuPflichtfeld(landCombobox);
			hausnrTextfeld = neuesTextfeld();
			adresseFlaecheJ.add(hausnrTextfeld);
			hausnrTextfeld.setBounds(334, 12, 48, 23);
			macheZuPflichtfeld(hausnrTextfeld);
		}

		kundennrFuehrungstextJ.setText("Kundennr.");
		add(kundennrFuehrungstextJ);
		kundennrFuehrungstextJ.setFont(meineSchrift);
		kundennrFuehrungstextJ.setBounds(24,20,84,24);

		//Knoepfe zum Aufbauen der Links
		add(loeschenLinkKnopf);
		loeschenLinkKnopf.setBounds(397, 589, 24, 23);

	    //Fuehrungstext ueber Tabelle
	    add(anzeigeLabel);
	    anzeigeLabel.setBounds(12,403,60,30);

	    //Fuehrungstext ueber Tabelle
	    add(anzeigeLabel);
	    anzeigeLabel.setBounds(24, 594, 93, 23);
	    anzeigeLabel.setText("Anzeigen");
	    {
	    	agbFuehrungstext = new JLabel();
	    	add(agbFuehrungstext);
	    	agbFuehrungstext.setText("AGB akzeptiert am (TT.MM.JJJJ)");
	    	agbFuehrungstext.setBounds(24, 556, 195, 23);
	    	agbTextfeld = neuesTextfeld();
	    	add(agbTextfeld);
	    	agbTextfeld.setBounds(216, 553, 118, 23);
	    	macheZuPflichtfeld(agbTextfeld);
	    }
	    {
	    	bankFlaecheJ = new JPanel();
	    	add(bankFlaecheJ);
	    	bankFlaecheJ.setBackground(new Color(226,226,226));
	    	bankFlaecheJ.setBounds(24, 445, 397, 102);
	    	bankFlaecheJ.setLayout(null);
    		bankFuehrungstext = new JLabel();
    		bankFlaecheJ.add(bankFuehrungstext);
    		bankFuehrungstext.setText("Bank");
    		bankFuehrungstext.setBounds(12, 12, 195, 23);

    		bankTextfeld = neuesTextfeld();
    		bankFlaecheJ.add(bankTextfeld);
    		bankTextfeld.setBounds(115, 9, 195, 23);
    		macheZuPflichtfeld(bankTextfeld);

    		blzFuehrungstext = new JLabel();
    		bankFlaecheJ.add(blzFuehrungstext);
    		blzFuehrungstext.setText("BLZ");
    		blzFuehrungstext.setBounds(12, 45, 195, 23);

    		blzTextfeld = neuesTextfeld();
    		bankFlaecheJ.add(blzTextfeld);
    		blzTextfeld.setBounds(115, 42, 195, 23);
    		macheZuPflichtfeld(blzTextfeld);

    		kontonrFuehrungstext = new JLabel();
    		bankFlaecheJ.add(kontonrFuehrungstext);
    		kontonrFuehrungstext.setText("Kontonummer");
    		kontonrFuehrungstext.setBounds(12, 73, 195, 23);
    	 	
    		kontonrTextfeld = neuesTextfeld();
    		bankFlaecheJ.add(kontonrTextfeld);
    		kontonrTextfeld.setBounds(115, 70, 195, 23);
    		macheZuPflichtfeld(kontonrTextfeld);
	    }
	    {
	    	kontaktFlaecheJ = new JPanel();
	    	add(kontaktFlaecheJ);
	    	kontaktFlaecheJ.setBackground(new Color(226,226,226));
	    	kontaktFlaecheJ.setBounds(24, 298, 397, 120);
	    	kontaktFlaecheJ.setLayout(null);

    		emailFuehrungstext = new JLabel();
    		kontaktFlaecheJ.add(emailFuehrungstext);
    		emailFuehrungstext.setText("E-Mail");
    		emailFuehrungstext.setBounds(12, 7, 76, 16);

    		emailTextfeld = neuesTextfeld();
    		kontaktFlaecheJ.add(emailTextfeld);
    		emailTextfeld.setBounds(115, 4, 198, 23);
    		macheZuPflichtfeld(emailTextfeld);
    		
    		telefonFuehrungstext = new JLabel();
    		kontaktFlaecheJ.add(telefonFuehrungstext);
    		telefonFuehrungstext.setText("Telefon");
    		telefonFuehrungstext.setBounds(12, 32, 76, 16);

    		telefonTextfeld = neuesTextfeld();
    		kontaktFlaecheJ.add(telefonTextfeld);
    		telefonTextfeld.setBounds(115, 29, 198, 23);
    		macheZuPflichtfeld(telefonTextfeld);

    		telefaxFuehrungstext = new JLabel();
    		kontaktFlaecheJ.add(telefaxFuehrungstext);
    		telefaxFuehrungstext.setText("Telefax");
    		telefaxFuehrungstext.setBounds(12, 88, 76, 16);

    		telefaxTextfeld = neuesTextfeld();
    		kontaktFlaecheJ.add(telefaxTextfeld);
    		telefaxTextfeld.setBounds(115, 85, 198, 23);
			mobilFuehrungstextJ = new JLabel();
			kontaktFlaecheJ.add(mobilFuehrungstextJ);
			mobilFuehrungstextJ.setText("Mobil");
			mobilFuehrungstextJ.setBounds(12, 60, 76, 16);

			kontaktFlaecheJ.add(mobilEingabefeldJ);
			mobilEingabefeldJ.setBounds(115, 57, 198, 23);
	    }

	    anzeigenListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    anzeigenListe.setModel(listenModel);

	    einRollfeld = new JScrollPane(anzeigenListe);
	    einRollfeld.setBounds(24, 618, 397, 100);
	    //Hinzufuegen des Rollfelds zum Fenster
	    add(einRollfeld);

	    setBounds(396, 544, 30, 30);
        //Knoepfe fuer die elementaren Operationen (OK,Uebernehmen,...)
		okDruckknopfJ.setText("OK");
		add(okDruckknopfJ);
		okDruckknopfJ.setBounds(26, 727, 115, 23);
		
		uebernehmenKnopfJ.setText("Uebernehmen");
		add(uebernehmenKnopfJ);
		uebernehmenKnopfJ.setBounds(146, 727, 115, 23);
		
		// Abhoerer
	    okDruckknopfJ.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				okAktion();
			}
	    });
	    uebernehmenKnopfJ.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				uebernehmenAktion();
			}
	    });
		loeschenLinkKnopf.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				loeschenAktion();
			}
	    });
		
		derKunde = einKunde;
		zeigen(derKunde);
	}
	
	//Fenster initialisieren und sichtbar machen
	public void zeigen(Kunde einKunde)
	{
	    zuruecksetzen();
	    
	    try
	    {  	
			if (einKunde == null)
			{
		        derKunde = anzeigenverwaltung.erstelleKunde();
				setSize(487, 761);
				
		    }
	        else//Es wurde ein Kunde zum Veraendern uebergeben
	        {
	            derKunde = einKunde;
	            
	            //name
	            anredeAuswahlfeldJ.setSelectedItem(derKunde.getName().getAnrede());
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
	            this.mobilEingabefeldJ.setText(derKunde.getKontakt().getMobil());
	            
	            //bank
		        blzTextfeld.setText(Integer.toString(derKunde.getBank().getBlz()));
	            bankTextfeld.setText(derKunde.getBank().getName());
	            kontonrTextfeld.setText(Integer.toString(derKunde.getBank().getKontonummer()));
	            
		        //agb
	            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	            agbTextfeld.setText(df.format(derKunde.getAgbBestaetigt()));
	            
	            for(Anzeige anz : derKunde.getAnzeigeListe())
	            {	
	            	listenModel.addElement(anz.getAnzeigename());
	            }
		    }
			 
	        //Alle Eingabeelemente auf die Attributwerte von derKunde setzen
	        this.kundenNrFeld.setText(Long.toString(derKunde.getNummer()));
        
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ein Fehler ist aufgetreten: " + e.getMessage(),
			        "Fehler", JOptionPane.OK_OPTION );
	    }
	}
	


    //Alle Daten uebernehmen und Fenster schliessen
	private void okAktion()
	{
	    if (schreibeKunde())//Konnten alle Daten uebernommen werden?
	    {
	    	try
	    	{
	    		anzeigenverwaltung.speichereKunde(derKunde);
		        getHauptfenster().zeigeKundenliste();
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Ein Fehler ist aufgetreten: " + e.getMessage(),
				        "Fehler", JOptionPane.OK_OPTION );
	    	}
	    }
	}
	
	//Alle Daten uebernehmen und Eingabeelemente zuruecksetzen
	private void uebernehmenAktion()
	{
	    if (schreibeKunde())
	    {
	    	try
	    	{
	    		anzeigenverwaltung.speichereKunde(derKunde);
	    		zeigen(null);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Ein Fehler ist aufgetreten: " + e.getMessage(),
				        "Fehler", JOptionPane.OK_OPTION );
	    	}
	    }
	}
		
	//Einen Link auf einen ausgewaehlten Anzeige loeschen
	private void loeschenAktion()
	{
  	    try
        {
            int Zeile = anzeigenListe.getSelectedIndex();
            if (Zeile > -1)
            {
                this.listenModel.removeElementAt(Zeile);                
                zuEntfernendeLinks.add((Anzeige) derKunde.getAnzeigeListe().get(Zeile));
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ein Fehler ist aufgetreten: " + e.getMessage(),
			        "Fehler", JOptionPane.OK_OPTION );
        }
	}
	
	//Alle Daten aus den Eingabeelementen uebernehmen.
	//Gibt true zurueck, falls erfolgreich ansonsten false.
	public boolean schreibeKunde()
	{	    
	    if (!pflichtTextfelderAusgefuellt())
	    {
	    	zeigePflichtfeldFehlerMitteilung();
	        return false;
	    }
	    
	    try
        {
	    	//nummer
	    	derKunde.setNummer(Integer.valueOf(kundenNrFeld.getText()));
	    	// name
	        NameT.Anrede eineAnrede = (NameT.Anrede)anredeAuswahlfeldJ.getSelectedItem();
	    	
	        NameT name = derKunde.getName();
	        name.setVorname(this.vornameTextfeldJ.getText());
	        name.setNachname(this.nachnameTextfeldJ.getText());
	        name.setAnrede(eineAnrede);
	        derKunde.setName(name);
	        // adresse
	        AdresseT adresse = derKunde.getAdresse();
	        adresse.setPlz(this.plzTextfeldJ.getText());
	        adresse.setOrt(this.ortTextfeldJ.getText());
	        AdresseT.Land land = (AdresseT.Land)this.landCombobox.getSelectedItem();
	        adresse.setLand(land);
	        adresse.setHausnummer(this.hausnrTextfeld.getText());
	        adresse.setStrasse(this.strasseTextfeldJ.getText());
	        derKunde.setAdresse(adresse);
	        // kontakt
	        KontaktT kontakt = derKunde.getKontakt();
	        kontakt.setEmail(this.emailTextfeld.getText());
	        kontakt.setTelefon(this.telefonTextfeld.getText());
	        kontakt.setFax(this.telefaxTextfeld.getText());
	        kontakt.setMobil(this.mobilEingabefeldJ.getText());
	        derKunde.setKontakt(kontakt);
	        //bank
	        BankT bank = derKunde.getBank();
	        bank.setBlz(Integer.valueOf(this.blzTextfeld.getText()));
	        bank.setName(this.bankTextfeld.getText());
	        bank.setKontonummer(Integer.valueOf(this.kontonrTextfeld.getText()));
	        derKunde.setBank(bank);
	        //agb
	        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	        Date date = df.parse(this.agbTextfeld.getText());
	        derKunde.setAgbBestaetigt(date);
	        
	        // zu entfernende Anzeigen
	        Iterator<Anzeige> iter = zuEntfernendeLinks.iterator();
	        while(iter.hasNext()) 
	        {
	        	Anzeige eineAnzeige = iter.next();
	        	derKunde.removeLinkAnzeige(eineAnzeige);
	        	anzeigenverwaltung.entferneAnzeige(eineAnzeige);
	        }        
	        return true;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ein Fehler ist aufgetreten: " + e.getMessage(),
			        "Fehler", JOptionPane.OK_OPTION );
	        return false;
	    }
	}
    
    //Alle Eingabeelemente zuruecksetzen
    public void zuruecksetzen()
    {
    	resetTextFelder();
    	anredeAuswahlfeldJ.setSelectedIndex(0);
	    this.landCombobox.setSelectedIndex(0);
	    listenModel.removeAllElements();
   }
}