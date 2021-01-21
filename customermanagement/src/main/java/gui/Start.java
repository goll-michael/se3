package gui;

import javax.swing.SwingUtilities;

public class Start {
	public Start() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				/*
				 * Benutzungsoberflaeche aufbauen. Erzeugen des Anwendungsfensters
				 */
				new Kundenfenster("Kundenverwaltung").setVisible(true);
			}
		});
	}

	public static void main(String[] args) {
		new Start();
	}
}