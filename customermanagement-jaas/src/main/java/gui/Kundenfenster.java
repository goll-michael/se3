package gui;

 
import java.awt.*;
import java.awt.event.*;
import java.security.PrivilegedAction;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.swing.*;

import application.Kunde;
import application.KundenContainer;
import auth.ErlaubnisBearbeiten;
import auth.ErlaubnisSuchen;

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
	private static final long serialVersionUID = 4263385453867879254L;

	private enum OperationsModus {NEUER_KUNDE, KUNDE_SUCHEN};
		
	//Der Kunde, der gerade bearbeitet wird
	private static Kunde einKunde;
	
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

	//Erlaubnisse
	private boolean sucheErmoeglicht;
	private boolean editErmoeglicht;

	/*
	 * Wird hier uebergeben von LoginFenster und stellt die Verbindung
	 * zwischen JAAS und der Anwendung, die JAAS benutzt, her.
	 */
	private final LoginContext loginContext;
	
	public Kundenfenster(String fenstertitel, LoginContext loginContext)
	{
		super(fenstertitel);
		
		this.loginContext = loginContext;
		
		//Erlaubnisse werden gesetzt
		setzeErlaubnisse();
		
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
		nameTextFeldJ.setEditable(editErmoeglicht);
		
		//Knopf "Neuer Kunde"
		getContentPane().add(neuerKundeKnopfJ);
		neuerKundeKnopfJ.setBounds(25, 12, 152, 30);
		neuerKundeKnopfJ.setEnabled(editErmoeglicht);
		
		//Knopf "Suchfunktion"
		getContentPane().add(suchfunktionKnopfJ);
		suchfunktionKnopfJ.setBounds(211, 12, 149, 30);
		suchfunktionKnopfJ.setEnabled(sucheErmoeglicht);
		
		//Knopf "Suchen"
		getContentPane().add(suchenKnopfJ);
		suchenKnopfJ.setBounds(180, 63, 77, 28);
		suchenKnopfJ.setEnabled(sucheErmoeglicht);
		
		//Knopf "Speichern"
		getContentPane().add(speichernKnopfJ);
		speichernKnopfJ.setBounds(262, 62, 99, 29);
		speichernKnopfJ.setEnabled(editErmoeglicht);
		
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
	    
	    if (editErmoeglicht)
	    	wechsleZuModus(OperationsModus.NEUER_KUNDE);
	    else if (sucheErmoeglicht)
	    	wechsleZuModus(OperationsModus.KUNDE_SUCHEN);
	    else 
	    {
			JOptionPane.showMessageDialog(
				null,
				"Sie haben keine Rechte zum Editieren oder Suchen.",
				"Fehler",
				JOptionPane.ERROR_MESSAGE
			);
	    }
	}
	
	//Erlaubnisse werden mithilfe des SecurityManager ermittelt, sollte zu Beginn aufgerufen werden
	private void setzeErlaubnisse()
	{
		Subject subj = loginContext.getSubject();
		
		System.out.println("subj"+subj.toString());
		
		//Bearbeiten erlaubt?
		Subject.doAsPrivileged(subj, new PrivilegedAction<Object>()
		{
			public Object run()
			{
				try
				{
					System.getSecurityManager().checkPermission(new ErlaubnisBearbeiten<Object>());
					editErmoeglicht = true;
				}
				catch(java.security.AccessControlException ace)
				{
					System.out.println("edit nicht erlaubt");
					editErmoeglicht = false;
				}
				return null;
			}
			
		}, null);
		
		//Suchen erlaubt?
		Subject.doAsPrivileged(subj, new PrivilegedAction<Object>()
		{
			public Object run()
			{
				try
				{
					System.getSecurityManager().checkPermission(new ErlaubnisSuchen<Object>());
					sucheErmoeglicht = true;
				}
				catch(java.security.AccessControlException ace)
				{
					System.out.println("suche nicht erlaubt");
					sucheErmoeglicht = false;
				}
				return null;
			}
		}, null);
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
	    	einKunde = null;
			zuruecksetzen();
	    }		
	}
	
	private void wechsleZuModus(OperationsModus opModus) 
	{
		switch(opModus) 
		{
		case KUNDE_SUCHEN:
			System.out.println("suchen");
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
			suchfunktionKnopfJ.setEnabled(sucheErmoeglicht);
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
    			"Es sind Mussfelder nicht ausgefuellt!",
		        "Mitteilung",
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