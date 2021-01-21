package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginFenster extends JDialog implements CallbackHandler {
	/**
	 * Beim Serialisieren eines Objektes wird auch die serialVersionUID der
	 * zugehoerigen Klasse mit in die Ausgabedatei geschrieben. Soll das Objekt
	 * spaeter deserialisiert werden, so wird die in der Datei gespeicherte
	 * serialVersionUID mit der aktuellen serialVersionUID des geladenen
	 * .class-Files verglichen. Stimmen beide nicht ueberein, so gibt es eine
	 * Ausnahme des Typs InvalidClassException, und der Deserialisierungsvorgang
	 * bricht ab.
	 */
	private static final long serialVersionUID = 8150111838190281206L;

	// Dies ist der Name der Login Konfiguration in der Datei login.config
	private static final String KVLOGIN_KONFIG = "KVLoginKonfig";

	private Font meineSchrift = new Font("Dialog", Font.PLAIN, 12);

	// Alle Fuehrungstexte
	private JLabel benutzernameFuehrungstextJ = new JLabel("Benutzername");
	private JLabel passwortFuehrungstextJ = new JLabel("Passwort");

	// Alle Textfelder
	private JTextField benutzernameTextFeldJ = new JTextField();
	private JPasswordField passwortPasswortFeldJ = new JPasswordField();

	// Flaeche und Rahmen
	private Border nameRahmen;
	private JPanel nameFlaecheJ = new JPanel();

	// Alle Druckknoepfe
	private JButton okKnopfJ = new JButton("OK");
	private JButton abbrechenKnopfJ = new JButton("Abbrechen");

	/*
	 * Wird hier uebergeben von LoginFenster und stellt die Verbindung zwischen JAAS
	 * und der Anwendung, die JAAS benutzt, her.
	 */
	private LoginContext loginContext = null;

	public LoginFenster() {
		// Setze Sicherheits-Manager
		System.setSecurityManager(new SecurityManager());

		// Oberflaeche initialisieren
		initGUI();
	}

	private void initGUI() {
		setTitle("Geben Sie Ihre Benutzerdaten ein");
		setModal(true);
		setSize(356, 196);

		// Layout und Hintergrund des obersten Containers setzen
		getContentPane().setLayout(null);
		getContentPane().setBackground(java.awt.Color.lightGray);

		// Rahmen einstellen
		nameRahmen = BorderFactory.createTitledBorder("Benutzer-Angaben");

		// Flaeche einstellen
		getContentPane().add(nameFlaecheJ);
		nameFlaecheJ.setBorder(nameRahmen);
		nameFlaecheJ.setLayout(null);
		nameFlaecheJ.setBackground(new java.awt.Color(226, 226, 226));
		nameFlaecheJ.setBounds(12, 12, 317, 93);

		// Fuehrungstext fuer Benutzername
		nameFlaecheJ.add(benutzernameFuehrungstextJ);
		benutzernameFuehrungstextJ.setFont(meineSchrift);
		benutzernameFuehrungstextJ.setBounds(24, 24, 91, 26);

		// Fuehrungstext fuer Passwort
		nameFlaecheJ.add(passwortFuehrungstextJ);
		passwortFuehrungstextJ.setFont(meineSchrift);
		passwortFuehrungstextJ.setBounds(24, 58, 91, 26);

		// Textfeld fuer Benutzername
		nameFlaecheJ.add(benutzernameTextFeldJ);
		benutzernameTextFeldJ.setText("");
		benutzernameTextFeldJ.setBackground(new java.awt.Color(255, 255, 159));
		benutzernameTextFeldJ.setBounds(120, 26, 188, 24);

		// Passwortfeld fuer Passwort
		nameFlaecheJ.add(passwortPasswortFeldJ);
		passwortPasswortFeldJ.setText("");
		passwortPasswortFeldJ.setEchoChar('*');
		passwortPasswortFeldJ.setBackground(new java.awt.Color(255, 255, 159));
		passwortPasswortFeldJ.setBounds(120, 60, 188, 24);

		// Knopf "OK"
		getContentPane().add(okKnopfJ);
		okKnopfJ.setBackground(java.awt.Color.lightGray);
		okKnopfJ.setBounds(12, 127, 131, 30);

		// Knopf "Abbrechen"
		getContentPane().add(abbrechenKnopfJ);
		abbrechenKnopfJ.setBackground(java.awt.Color.lightGray);
		abbrechenKnopfJ.setBounds(198, 127, 131, 30);

		// Aktionsabhoerer "OK"
		okKnopfJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				okAktion();
			}
		});

		// Aktionsabhoerer "Abbrechen"
		abbrechenKnopfJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abbrechenAktion();
			}
		});

		// Standardaktion beim Schliessen
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	private void okAktion() {
		if (ueberpruefeAuth()) {
			setVisible(false);
			try {
				new Kundenfenster("Kundenverwaltung", loginContext).setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Fuehrt die JAAS-Authentifizierung durch
	private boolean ueberpruefeAuth() {
		try {
			loginContext = new LoginContext(KVLOGIN_KONFIG, this);
			loginContext.login();
			return true; // nur dann hier, wenn keine Exception auftrat
		} catch (LoginException le) {
			JOptionPane.showMessageDialog(null, "Kann LoginContext nicht aufbauen. " + le.getMessage(), "Fehler",
					JOptionPane.ERROR_MESSAGE);
		} catch (SecurityException se) {
			JOptionPane.showMessageDialog(null, "Kann LoginContext nicht aufbauen. " + se.getMessage(), "Fehler",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	private void abbrechenAktion() {
		setVisible(false);
		this.dispose();
	}

	public LoginContext getLoginContext() {
		return loginContext;
	}

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof TextOutputCallback) {
				// Zeige dem Typ gemaess die Nachricht an
				TextOutputCallback toc = (TextOutputCallback) callbacks[i];
				switch (toc.getMessageType()) {
				case TextOutputCallback.INFORMATION:
					JOptionPane.showMessageDialog(null, toc.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
					break;
				case TextOutputCallback.ERROR:
					JOptionPane.showMessageDialog(null, toc.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
					break;
				case TextOutputCallback.WARNING:
					JOptionPane.showMessageDialog(null, toc.getMessage(), "Warnung", JOptionPane.WARNING_MESSAGE);
					break;
				default:
					throw new IOException("Nachrichtentyp nicht unterstuetzt: " + toc.getMessageType());
				}
			} else if (callbacks[i] instanceof NameCallback) {
				// Setze den Namen des Benutzers
				NameCallback nc = (NameCallback) callbacks[i];
				nc.setName(benutzernameTextFeldJ.getText());
			} else if (callbacks[i] instanceof PasswordCallback) {
				// Setze Passwort des Benutzers
				PasswordCallback pc = (PasswordCallback) callbacks[i];
				pc.setPassword(passwortPasswortFeldJ.getPassword());
			} else {
				throw new UnsupportedCallbackException(callbacks[i], "Unidentifizierbares Callback");
			}
		}
	}
}
