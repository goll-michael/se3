package client.gui;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;
import juddi.Abfrage;
import client.application.WebserviceClient;


/**
 * Unterfenster für die Bearbeitung einer Anzeige
 */
public class UnterfensterAnzeige extends Unterfenster 
{
	private static final long serialVersionUID = 1L;

	// Attribute
	private Map<String,String> services;
	
	// Oberflaechenelemente
	// Alle Flaechen
	private JPanel anzeigenFlaecheJ = new JPanel();
	private JLabel kundennrFuehrungstext = new JLabel();
	private JLabel titelFuehrungstext = new JLabel();
	private JLabel beschreibungFuehrungstext = new JLabel();
	private JLabel preisFuehrungstext = new JLabel();
	private JLabel servicesFuehrungstext = new JLabel();
	
	// Alle Textfelder
	private JTextField kundennrTextfeldJ    = neuesTextfeld();
	private JTextField titelTextfeldJ = neuesTextfeld();
	private JTextField preisTextfeldJ = neuesTextfeld();
	private JTextField rubrikTextfeldJ = neuesTextfeld();
	
	// Alle Textbereiche
	private JTextArea beschreibungTextbereichJ = new JTextArea();

    // Alle Tabellen
  	private JList serviceListe = new JList();	
    private DefaultListModel listenModel = new DefaultListModel();
    
    //Alle Rollfelder
    private JScrollPane einRollfeld;    
    
	// Alle Druckknoepfe
	private JButton okDruckknopfJ = new JButton();
	private JLabel rubrikFuehrungstext;
	
	// Alle Rahmen
	private Border anzeigenRahmen;

	// Alle Tabellen

	// privater Konstruktor
	public UnterfensterAnzeige() 
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
		anzeigenFlaecheJ.setBounds(12, 12, 426, 420);

		// Die Fuehrungstexte setzen und einfuegen
		kundennrFuehrungstext.setText("KundenNr");
		anzeigenFlaecheJ.add(kundennrFuehrungstext);
		kundennrFuehrungstext.setBounds(24, 24, 78, 26);

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
		anzeigenFlaecheJ.add(kundennrTextfeldJ);
		macheZuPflichtfeld(kundennrTextfeldJ);
		kundennrTextfeldJ.setBounds(108, 24, 95, 24);
		
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
			anzeigenFlaecheJ.add(rubrikTextfeldJ);
			rubrikTextfeldJ.setText("");
			macheZuPflichtfeld(rubrikTextfeldJ);
			rubrikTextfeldJ.setBounds(108, 61, 232, 24);
			anzeigenFlaecheJ.add(servicesFuehrungstext);
		}
		preisTextfeldJ.setBounds(108, 231, 89, 24);
		macheZuPflichtfeld(preisTextfeldJ);
	
		// Fuehrungstext ueber Tabelle
		servicesFuehrungstext.setBounds(24, 261, 232, 30);
		servicesFuehrungstext.setText("Verfügbare Dienste");
		try
		{
			if(services != null) services.clear();
			
			Abfrage abfrage = new Abfrage();
			services = abfrage.sucheServices("Zeitung");
			for(String serviceName: services.keySet())
			{
				listenModel.addElement(serviceName);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		
	    serviceListe.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    serviceListe.setModel(listenModel);

	    einRollfeld = new JScrollPane(serviceListe);
	    einRollfeld.setBounds(24, 285, 367, 100);
	    //Hinzufuegen des Rollfelds zum Fenster
	    anzeigenFlaecheJ.add(einRollfeld);		

		// Knoepfe fuer die elementaren Operationen (OK,Uebernehmen,...)
		okDruckknopfJ.setText("Senden");
		add(okDruckknopfJ);
		okDruckknopfJ.setBounds(17, 435, 88, 30);
		okDruckknopfJ.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				okAktion();
			}
		});
		
		zeigen();
	}

	public void zeigen() 
    {
		zuruecksetzen();	
		
		// Das Anzeigefenster sichtbar machen
		setVisible(true);
	}
	
	private void okAktion()
	{
    	try
    	{
    		if(serviceListe.getSelectedIndices().length == 0)
    		{
            	JOptionPane.showMessageDialog(
        			this,
        			"Bitte wählen Sie mindestens einen Service aus!",
        			"Fehler",
        			JOptionPane.OK_OPTION
    			);	
    		}
    		else
    		{
	    		String kundennummer = kundennrTextfeldJ.getText();
	    		String rubrik = rubrikTextfeldJ.getText();
	    		String titel = titelTextfeldJ.getText();
	    		String beschreibung = beschreibungTextbereichJ.getText();
	    		String preis = preisTextfeldJ.getText();
	    		
	    		List<String> serviceNamen = new ArrayList<String>();
	    		serviceNamen.addAll(services.keySet());
	    		
	    		for(int index: serviceListe.getSelectedIndices())
	    		{
	    			String serviceAdresse = services.get(serviceNamen.get(index));
	        		WebserviceClient webserviceClient = new WebserviceClient(serviceAdresse);
	        		webserviceClient.erstelleAnzeige(kundennummer, rubrik, titel, beschreibung, preis);	
	    		}
    		}
    	}
    	catch(Exception e)
    	{
        	JOptionPane.showMessageDialog(
    			this,
    			"Beim Senden der Anzeige ist ein Fehler aufgetreten!" + e.getMessage(),
    			"Fehler",
    			JOptionPane.OK_OPTION
			);		
    		e.printStackTrace();
    	}
    	JOptionPane.showMessageDialog(
			this,
			"Die Anzeige wurde erfolgreich an die gewählten Zeitungen übertragen!",
			"Übertragung erfolgreich",
			JOptionPane.OK_OPTION
		);	
    	zuruecksetzen();
    	serviceListe.setSelectedIndex(-1);
	}

	// Alle Eingabefelder zuruecksetzen
	public void zuruecksetzen() 
	{
		resetTextFelder();
		beschreibungTextbereichJ.setText("");
	}
}