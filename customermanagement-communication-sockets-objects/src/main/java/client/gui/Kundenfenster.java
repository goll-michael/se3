package client.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import client.application.ServerProxy;

//Das Kundenfenster
public class Kundenfenster extends JFrame
{
	/**
	 * Beim Serialisieren eines Objektes wird auch die serialVersionUID der zugehoerigen
	 * Klasse mit in die Ausgabedatei geschrieben. Soll das Objekt spaeter deserialisiert
	 * werden, so wird die in der Datei gespeicherte serialVersionUID mit der aktuellen
	 * serialVersionUID des geladenen .class-Files verglichen. Stimmen beide nicht
	 * ueberein, so gibt es eine Ausnahme des Typs InvalidClassException, und der
	 * Deserialisierungsvorgang bricht ab.
	 */
	private static final long serialVersionUID = -7652791675920389173L;

	private enum OperationsModus {NEUER_KUNDE, KUNDE_SUCHEN};
	
	private Font meineSchrift = new Font("Dialog", Font.PLAIN, 12);

    //Alle Fuehrungstexte
	private JLabel kundennrFuehrungstextJ = new JLabel("Kundennr.");
	private JLabel nameFuehrungstextJ = new JLabel("Name");
	
    //Alle Textfelder
	private JTextField kundennrTextFeldJ = new JTextField();
	private JTextField nameTextFeldJ = new JTextField();
	
	//Alle Flaechen
	private JPanel nameFlaecheJ = new JPanel();
	
    //Alle Druckknoepfe
	private JButton neuerKundeKnopfJ = new JButton("Neuer Kunde");
	private JButton suchfunktionKnopfJ = new JButton("Suchfunktion");
	private JButton suchenKnopfJ = new JButton("Suchen");
	private JButton speichernKnopfJ = new JButton("Speichern");
	
	public Kundenfenster(String fenstertitel) 
	{
		super(fenstertitel);
		
	    //Layout und Hintergrund des obersten Containers setzen
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.lightGray);

		//Flaeche einstellen
		getContentPane().add(nameFlaecheJ);
		nameFlaecheJ.setLayout(null);
		nameFlaecheJ.setBackground(new java.awt.Color(226,226,226));
		nameFlaecheJ.setBounds(25, 100, 334, 60);
		
		//Fuehrungstext fuer Kundennr.
		getContentPane().add(kundennrFuehrungstextJ);
		kundennrFuehrungstextJ.setFont(meineSchrift);
		kundennrFuehrungstextJ.setBounds(30, 63, 84, 24);
		
		//Fuehrungstext fuer Name
		nameFlaecheJ.add(nameFuehrungstextJ);
		nameFuehrungstextJ.setFont(meineSchrift);
		nameFuehrungstextJ.setBounds(6, 19, 72, 24);
		
        //Textfeld fuer Kundennr.
		getContentPane().add(kundennrTextFeldJ);
		kundennrTextFeldJ.setBackground(new Color(255,255,159));
		kundennrTextFeldJ.setBounds(101, 65, 73, 24);
		kundennrTextFeldJ.setEditable(false);
				
		//Textfeld fuer Name
		nameFlaecheJ.add(nameTextFeldJ);
		nameTextFeldJ.setBackground(Color.white);
		nameTextFeldJ.setBounds(75, 21, 240, 24);
		nameTextFeldJ.setEditable(true);

		//Knopf "Neuer Kunde"
		getContentPane().add(neuerKundeKnopfJ);
		neuerKundeKnopfJ.setBounds(25, 12, 152, 30);
		
		//Knopf "Suchfunktion"
		getContentPane().add(suchfunktionKnopfJ);
		suchfunktionKnopfJ.setBounds(211, 12, 149, 30);
		
		//Knopf "Suchen"
		getContentPane().add(suchenKnopfJ);
		suchenKnopfJ.setBounds(180, 63, 77, 28);
		
		//Knopf "Speichern"
		getContentPane().add(speichernKnopfJ);
		speichernKnopfJ.setBounds(262, 62, 99, 29);
		
		//Aktionsabhoerer "Neuer Kunde"
		neuerKundeKnopfJ.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				wechsleZuModus(OperationsModus.NEUER_KUNDE);
			}
	    });
		
		//Aktionsabhoerer "Suchfunktion"
		suchfunktionKnopfJ.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				wechsleZuModus(OperationsModus.KUNDE_SUCHEN);
			}
	    });
	
		//Aktionsabhoerer "Suchen"
	    suchenKnopfJ.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				sucheKunde();
			}
	    });
		
		//Aktionsabhoerer "Speichern"
	    speichernKnopfJ.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				speichernAktion();
			}
	    });
	    
	    //Tastenabhoerer fuer "Kundennr." (beliebige Tasteneingabe)
	    kundennrTextFeldJ.addKeyListener(new KeyAdapter() 
	    {
			@Override
			public void keyTyped(KeyEvent arg0) 
			{
				nameTextFeldJ.setText("");
			}
	    });
	    
	    //Fensterabhoerer
	    this.addWindowListener(new WindowAdapter() 
	    {
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				ServerProxy.getObjektreferenz().endeAnwendung();
				System.exit(0);
			}
	    	
	    });
	    
	    //Groesse und Groessenveraenderung des obersten Containers setzen
	    setSize(381,199);
	    setResizable(false);
	    
	    //Erster Start, daher in den Modus "NEUER_KUNDE" wechseln
	    wechsleZuModus(OperationsModus.NEUER_KUNDE);
	}
	
	private void sucheKunde() 
	{
		if(kundennrTextFeldJ.getText().isEmpty()) 
		{
			zuruecksetzen();
			return;
		}
	    String nummer = kundennrTextFeldJ.getText();
	    try
	    {
	    	int nr = Integer.valueOf(nummer);
	    	String name = ServerProxy.getObjektreferenz().getKundeZuNr(nr);
			if(!(name.equals("")))
			{
				nameTextFeldJ.setText (name);
				kundennrTextFeldJ.setEditable(true);
	            suchenKnopfJ.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(
					Kundenfenster.this,
					"Kunde mit Nr. " + nummer + " konnte nicht gefunden werden.",
					"Mitteilung",
					JOptionPane.OK_OPTION
		        );
				zuruecksetzen();
			}
			return;
	    }
	    catch(Exception e) 
	    {
	    	JOptionPane.showMessageDialog(
    			Kundenfenster.this,
    			"Fehlerhafte Eingabe!",
		        "Mitteilung",
		        JOptionPane.OK_OPTION
	        );
			zuruecksetzen();
	    }		
	}
	
	private void wechsleZuModus(OperationsModus opModus) 
	{
		switch(opModus) 
		{
		case KUNDE_SUCHEN:
			suchenKnopfJ.setVisible(true);
			kundennrTextFeldJ.setText("");
			kundennrTextFeldJ.setEditable(true);
			suchfunktionKnopfJ.setEnabled(false);
			break;
		case NEUER_KUNDE:
			suchenKnopfJ.setVisible(false);
			kundennrTextFeldJ.setEditable(false);
	        int kundennr = ServerProxy.getObjektreferenz().getNaechsteKundenNr();
	        kundennrTextFeldJ.setText(Integer.toString(kundennr));
			suchfunktionKnopfJ.setEnabled(true);
			break;
		}
		nameTextFeldJ.setText("");
	}
	
    //Alle Daten uebernehmen 
	private void speichernAktion()
	{
	    if (schreibeKunde())//Konnten alle Daten uebernommen werden?
	    {
			try {
				ServerProxy.getObjektreferenz().einfuegeKunde(
					nameTextFeldJ.getText(),
					Integer.valueOf(kundennrTextFeldJ.getText())
				);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(Kundenfenster.this, e.getLocalizedMessage(), "Mitteilung", JOptionPane.OK_OPTION);
			}
	    }
	}
			
	/*
	 * Alle Daten aus den Eingabeelementen uebernehmen.
	 * Gibt true zurueck, falls erfolgreich, ansonsten false.
	 */
	public boolean schreibeKunde()
	{		
	    String nummer = kundennrTextFeldJ.getText();
	    String name = nameTextFeldJ.getText();
	    
	    if (nummer.length()==0||name.length()==0)
	    {
	    	JOptionPane.showMessageDialog(
    			this,
    			"Es sind Mussfelder nicht ausgefuellt!",
		        "Mitteilung",
		        JOptionPane.OK_OPTION
	        );
	        return false;
	    }  	    
	    try
        {
	    	@SuppressWarnings("unused")
			int nr = Integer.valueOf(nummer);
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
    	kundennrTextFeldJ.setText("");
    	nameTextFeldJ.setText("");
   }
}