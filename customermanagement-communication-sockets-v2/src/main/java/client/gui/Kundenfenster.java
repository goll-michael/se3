package client.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import client.SocketClient.Client;

/*
 Das Kundenfenster
 */
public class Kundenfenster extends JFrame
{
	private enum OperationsModus
	{
		NEUER_KUNDE, KUNDE_SUCHEN
	};

	// Attribute
	// Der Kunde, der gerade bearbeitet wird
	Client client = new Client();
	private Font meineSchrift = new Font("Dialog", Font.PLAIN, 12);
	// Oberflaechenelemente
	// Alle Fuehrungstexte
	private JLabel nameFuehrungstextJ = new JLabel();
	private JLabel kundennrFuehrungstextJ = new JLabel();
	private JLabel jServerLabel = new JLabel();
	private JLabel jPortLabel = new JLabel();
	// Alle Textfelder
	private JTextField kundenNrFeld = new JTextField();
	private JTextField nameTextfeldJ = new JTextField();
	private JTextField jStatus = new JTextField();
	private JTextField jServerPort = new JTextField();
	private JTextField jServerAdresse = new JTextField();
	// Alle Flaechen
	private JPanel nameFlaecheJ = new JPanel();
	// Alle Druckknoepfe
	private JButton speichernDruckknopfJ;
	private JButton suchKnopfJ;
	private JButton suchfunktionKnopfJ;
	private JButton neuerKundeKnopfJ;
	private JButton jVerbindenKnopf;

	// --- privater Konstruktor (Singleton-Pattern) ---
	private Kundenfenster(String fenstertitel)
	{
		super(fenstertitel);
		// Layout, Hintergrund und Groesse des obersten Containers setzen
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.lightGray);
		// Textfelder und Fuehrungstexte
		getContentPane().add(kundenNrFeld);
		kundenNrFeld.setBackground(new Color(255, 255, 159));
		kundenNrFeld.setBounds(101, 65, 73, 24);
		kundenNrFeld.setEditable(false);
		nameFlaecheJ.setLayout(null);
		getContentPane().add(nameFlaecheJ);
		nameFlaecheJ.setBackground(new java.awt.Color(226, 226, 226));
		nameFlaecheJ.setBounds(25, 100, 334, 60);
		nameFlaecheJ.add(nameTextfeldJ);
		nameTextfeldJ.setBackground(Color.white);
		nameTextfeldJ.setBounds(75, 21, 240, 24);
		nameFuehrungstextJ.setText("Name");
		nameFlaecheJ.add(nameFuehrungstextJ);
		nameFuehrungstextJ.setFont(meineSchrift);
		nameFuehrungstextJ.setBounds(6, 19, 72, 24);
		kundennrFuehrungstextJ.setText("Kundennr.");
		getContentPane().add(kundennrFuehrungstextJ);
		kundennrFuehrungstextJ.setFont(meineSchrift);
		kundennrFuehrungstextJ.setBounds(30, 63, 84, 24);
		getContentPane().add(jServerLabel);
		jServerLabel.setText("Servername:");
		jServerLabel.setBounds(69, 174, 78, 19);
		getContentPane().add(jServerAdresse);
		jServerAdresse.setText(client.defaultZielServer);
		jServerAdresse.setBounds(147, 173, 98, 21);
		getContentPane().add(jPortLabel);
		jPortLabel.setText("Port:");
		jPortLabel.setBounds(257, 174, 35, 19);
		getContentPane().add(jServerPort);
		jServerPort.setText(client.defaultZielPort);
		jServerPort.setBounds(304, 172, 55, 23);
		getContentPane().add(jStatus);
		jStatus.setText("nicht Verbunden");
		jStatus.setBounds(257, 200, 101, 26);
		jStatus.setEditable(false);
		// Knoepfe fuer die elementaren Operationen (OK,Uebernehmen,...)
		speichernDruckknopfJ = new JButton();
		speichernDruckknopfJ.setText("Speichern");
		getContentPane().add(speichernDruckknopfJ);
		speichernDruckknopfJ.setBounds(262, 62, 99, 29);
		suchKnopfJ = new JButton();
		getContentPane().add(suchKnopfJ);
		suchKnopfJ.setText("Suchen");
		suchKnopfJ.setBounds(180, 63, 77, 28);
		jVerbindenKnopf = new JButton();
		getContentPane().add(jVerbindenKnopf);
		jVerbindenKnopf.setText("Verbinden");
		jVerbindenKnopf.setBounds(147, 200, 99, 25);
		neuerKundeKnopfJ = new JButton();
		getContentPane().add(neuerKundeKnopfJ);
		neuerKundeKnopfJ.setText("Neuer Kunde");
		neuerKundeKnopfJ.setBounds(25, 12, 152, 30);
		neuerKundeKnopfJ.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				wechsleZuModus(OperationsModus.NEUER_KUNDE);
			}
		});
		suchfunktionKnopfJ = new JButton();
		getContentPane().add(suchfunktionKnopfJ);
		suchfunktionKnopfJ.setText("Suchfunktion");
		suchfunktionKnopfJ.setBounds(211, 12, 149, 30);
		suchfunktionKnopfJ.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				wechsleZuModus(OperationsModus.KUNDE_SUCHEN);
			}
		});
		// Abhoerer
		jVerbindenKnopf.addActionListener(new ActionListener() // zum Server
				// verbinden
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						String port = jServerPort.getText();
						String server = jServerAdresse.getText();
						try
						{
							int serverport = Integer.valueOf(port);
							client.verbindungStarten(server, serverport);
							{
								jStatus.setText("Verbunden");
								jVerbindenKnopf.setVisible(false);
							}
						}
						catch (Exception e)
						{
							System.out.print(e);
							jStatus.setText("Nicht Verbunden");
						}
					}
				});
		speichernDruckknopfJ.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				speichernAktion();
			}
		});
		suchKnopfJ.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				sucheKunde();
			}
		});
		kundenNrFeld.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent arg0)
			{
				client.setzeKundeAufNull();// derKunde = null;
				nameTextfeldJ.setText("");
			}
		});
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent arg0)
			{
				client.endeAnwendung(); // ObjektContainer.getObjektreferenz().endeAnwendung();
				System.exit(0);
			}
		});
		this.setSize(389, 290);
		setResizable(false);
		wechsleZuModus(OperationsModus.NEUER_KUNDE);
	}

	private void sucheKunde()
	{
		if (kundenNrFeld.getText().isEmpty())
		{
			client.setzeKundeAufNull(); // derKunde = null;
			zuruecksetzen();
			return;
		}
		String nummer = kundenNrFeld.getText();
		try
		{
			int nr = Integer.valueOf(nummer);
			String kundenName = client.getKundeZuNr(nr); // derKunde =
			// ObjektContainer.getObjektreferenz().getKundeZuNr(nr);
			if (kundenName != null) // if(derKunde != null)
			{
				nameTextfeldJ.setText(kundenName); // nameTextfeldJ.setText
				// (derKunde.getName());
				kundenNrFeld.setEditable(true);
				suchKnopfJ.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(Kundenfenster.this,
						"Kunde mit Nr. " + nummer
								+ " konnte nicht gefunden werden.",
						"Mitteilung", JOptionPane.OK_OPTION);
				zuruecksetzen();
			}
			return;
		}
		catch (Exception e)
		{
			JOptionPane
					.showMessageDialog(Kundenfenster.this,
							"Fehlerhafte Eingabe!", "Mitteilung",
							JOptionPane.OK_OPTION);
			client.setzeKundeAufNull(); // derKunde = null;
			zuruecksetzen();
		}
	}

	private void wechsleZuModus(OperationsModus opModus)
	{
		switch (opModus)
		{
		case KUNDE_SUCHEN:
			suchKnopfJ.setVisible(true);
			kundenNrFeld.setText("");
			kundenNrFeld.setEditable(true);
			suchfunktionKnopfJ.setEnabled(false);
			break;
		case NEUER_KUNDE:
			speichernDruckknopfJ.setVisible(true);
			suchKnopfJ.setVisible(false);
			kundenNrFeld.setEditable(false);
			client.newKunde(); // derKunde = new Kunde();
			int kundennr = client.getNaechsteKundenNr(); // int kundennr =
			// ObjektContainer.getObjektreferenz().getNaechsteKundenNr();
			client.setNummer(kundennr); // derKunde.setNummer(kundennr);
			kundenNrFeld.setText(Integer.toString(kundennr));
			suchfunktionKnopfJ.setEnabled(true);
			break;
		}
		nameTextfeldJ.setText("");
	}

	// Klassenoperation
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				// Benutzungsoberflaeche aufbauen
				// Erzeugen des Anwendungsfensters
				new Kundenfenster("Kundenverwaltung").setVisible(true);
			}
		});
	}

	// Alle Daten uebernehmen
	private void speichernAktion()
	{
		if (schreibeKunde())// Konnten alle Daten uebernommen werden?
		{
			client.einfuegeKunde(); // ObjektContainer.getObjektreferenz().einfuegeKunde(derKunde);
		}
	}

	// Alle Daten aus den Eingabeelementen uebernehmen.
	// Gibt true zurueck, falls erfolgreich ansonsten false.
	public boolean schreibeKunde()
	{
		if (client.derKundeGleichGleichNull())
		{
			System.out.print("false");
			return false;
		}
		String nummer = kundenNrFeld.getText();
		String name = nameTextfeldJ.getText();
		if (nummer.length() == 0 || name.length() == 0)
		{
			JOptionPane.showMessageDialog(this,
					"Es sind Mussfelder nicht ausgefuellt!", "Mitteilung",
					JOptionPane.OK_OPTION);
			return false;
		}
		try
		{
			client.setNummer(Integer.valueOf(nummer));
			client.setName(name);
			return true;
		}
		catch (Exception e)
		{
			System.out.print("fehler");
			e.printStackTrace();
			return false;
		}
	}

	// Alle Eingabeelemente zuruecksetzen
	public void zuruecksetzen()
	{
		kundenNrFeld.setText("");
		nameTextfeldJ.setText("");
	}
}