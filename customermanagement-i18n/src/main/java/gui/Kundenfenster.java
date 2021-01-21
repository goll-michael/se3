package gui;

import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.swing.*;

import application.Kunde;
import application.KundenContainer;


//Das Kundenfenster
public class Kundenfenster extends JFrame
{
	/**
	 * Beim Serialisieren eines Objektes wird auch die serialVersionUID der zugehörigen
	 * Klasse mit in die Ausgabedatei geschrieben. Soll das Objekt später deserialisiert
	 * werden, so wird die in der Datei gespeicherte serialVersionUID mit der aktuellen
	 * serialVersionUID des geladenen .class-Files verglichen. Stimmen beide nicht
	 * überein, so gibt es eine Ausnahme des Typs InvalidClassException, und der
	 * Deserialisierungsvorgang bricht ab.
	 */
	private static final long serialVersionUID = 6964462259159010986L;

	private enum OperationsModus {NEUER_KUNDE, KUNDE_SUCHEN};
	
	//Der Kunde, der gerade bearbeitet wird
	private static Kunde einKunde;
	
	private Font meineSchrift = new Font("Dialog", Font.PLAIN, 12);

    /*
     * Alle Fuehrungstexte, erst im Konstruktor kann der Anzeigename gesetzt werden,
     * da dort erst ResourceBundle uebergeben wird
     */
	private JLabel kundennrFuehrungstextJ = new JLabel();
	private JLabel nameFuehrungstextJ = new JLabel();
	
    //Alle Textfelder
	private JTextField kundennrTextFeldJ = new JTextField();
	private JTextField nameTextFeldJ = new JTextField();
	
	//Alle Flaechen
	private JPanel nameFlaecheJ = new JPanel();
	
	/*
     * Alle Druckknoepfe, erst im Konstruktor kann der Anzeigename gesetzt werden,
     * da dort erst ResourceBundle uebergeben wird
     */
	private JButton neuerKundeKnopfJ = new JButton();
	private JButton suchfunktionKnopfJ = new JButton();
	private JButton suchenKnopfJ = new JButton();
	private JButton speichernKnopfJ = new JButton();
		
	//Fuer die Spracheinstellung
	private ResourceBundle ressourcenBuendel;
	
	public Kundenfenster(ResourceBundle ressourcenBuendel) 
	{
		super(ressourcenBuendel.getString("title"));		
        this.ressourcenBuendel = ressourcenBuendel;
	    
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
		kundennrFuehrungstextJ.setText(ressourcenBuendel.getString("customerNumber") + "  ");
		kundennrFuehrungstextJ.setFont(meineSchrift);
		kundennrFuehrungstextJ.setBounds(30, 63, 84, 24);
		
		//Fuehrungstext fuer Name
		nameFlaecheJ.add(nameFuehrungstextJ);
		nameFuehrungstextJ.setText(ressourcenBuendel.getString("name") + "  ");
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
		neuerKundeKnopfJ.setText(ressourcenBuendel.getString("newCustomer"));
		neuerKundeKnopfJ.setBounds(25, 12, 152, 30);
		
		//Knopf "Suchfunktion"
		getContentPane().add(suchfunktionKnopfJ);
		suchfunktionKnopfJ.setText(ressourcenBuendel.getString("searchFunction"));
		suchfunktionKnopfJ.setBounds(211, 12, 149, 30);
		
		//Knopf "Suchen"
		getContentPane().add(suchenKnopfJ);
		suchenKnopfJ.setText(ressourcenBuendel.getString("search"));
		suchenKnopfJ.setBounds(180, 63, 77, 28);
		
		//Knopf "Speichern"
		getContentPane().add(speichernKnopfJ);
		speichernKnopfJ.setText(ressourcenBuendel.getString("save"));
		speichernKnopfJ.setBounds(262, 62, 99, 29);
        
		//Aktionsabhoerer "Neuer Kunde"
		neuerKundeKnopfJ.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent arg0) 
			{
				wechsleZuModus(OperationsModus.NEUER_KUNDE);
			}
	    });
		
		//Aktionsabhoerer "Suchfunktion"
		suchfunktionKnopfJ.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent arg0) 
			{
				wechsleZuModus(OperationsModus.KUNDE_SUCHEN);
			}
	    });
	
		//Aktionsabhoerer "Suchen"
	    suchenKnopfJ.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent arg0) 
			{
				sucheKunde();
			}
	    });
		
		//Aktionsabhoerer "Speichern"
	    speichernKnopfJ.addActionListener(new ActionListener() 
	    {
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
				einKunde = null;
				nameTextFeldJ.setText("");
			}
	    });
	    
	    //Fensterabhoerer
	    this.addWindowListener(new WindowAdapter() 
	    {
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				KundenContainer.getObjektreferenz().endeAnwendung();
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
			einKunde = null;
			zuruecksetzen();
			return;
		}
	    String nummer = kundennrTextFeldJ.getText();
	    try
	    {
	    	int nr = Integer.valueOf(nummer);
	    	einKunde = KundenContainer.getObjektreferenz().getKundeZuNr(nr);
			if(einKunde != null)
			{
				nameTextFeldJ.setText (einKunde.getName());
				kundennrTextFeldJ.setEditable(true);
	            suchenKnopfJ.setVisible(true);
			}
			else
			{
				// Nachricht mit 1 (oder mehr) Parameter aufwaendiger zu bauen
				MessageFormat formatter = new MessageFormat("");
				formatter.setLocale(this.ressourcenBuendel.getLocale());
				formatter.applyPattern(ressourcenBuendel.getString("customerNotFoundMsg"));
				Object[] argumente = {nummer};
				String msg = formatter.format(argumente);
				JOptionPane.showMessageDialog(
					this,
					msg,
    		        ressourcenBuendel.getString("message"),
    		        JOptionPane.OK_OPTION
		        );
				zuruecksetzen();
			}
			return;
	    }
	    catch(Exception e) 
	    {
	    	JOptionPane.showMessageDialog(
    			this,
    			ressourcenBuendel.getString("invalidInput"),
		        ressourcenBuendel.getString("message"),
		        JOptionPane.OK_OPTION
	        );
	    	einKunde = null;
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
			einKunde = new Kunde();
	        int kundennr = KundenContainer.getObjektreferenz().getNaechsteKundenNr();
	        einKunde.setNummer(kundennr);
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
	        KundenContainer.getObjektreferenz().einfuegeKunde(einKunde);
	}
			
	/*
	 * Alle Daten aus den Eingabeelementen uebernehmen.
	 * Gibt true zurueck, falls erfolgreich, ansonsten false.
	 */
	private boolean schreibeKunde()
	{
		if(einKunde == null)
			return false;
		
	    String nummer = kundennrTextFeldJ.getText();
	    String name = nameTextFeldJ.getText();
	    
	    if (nummer.length()==0||name.length()==0)
	    {    	
	    	JOptionPane.showMessageDialog(
    			this,
    			ressourcenBuendel.getString("requiredFieldsNotFilledMsg"),
    			ressourcenBuendel.getString("message"),
    			JOptionPane.OK_OPTION
			);
	    	
	        return false;
	    }  	    
	    try
        {
	    	einKunde.setNummer(Integer.valueOf(nummer));
	    	einKunde.setName(name);
	        return true;
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	        return false;
	    }
	}
	    
    //Alle Eingabeelemente zuruecksetzen
    private void zuruecksetzen()
    {
    	kundennrTextFeldJ.setText("");
    	nameTextFeldJ.setText("");
   }
}