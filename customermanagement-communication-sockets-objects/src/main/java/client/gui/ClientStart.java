package client.gui;

import javax.swing.SwingUtilities;

public class ClientStart 
{
	public ClientStart()
	{
    	SwingUtilities.invokeLater(new Runnable()
	    {
	            public void run()
	            {
					/*
					 * Benutzungsoberflaeche aufbauen.
					 * Erzeugen des Anwendungsfensters
					 */
	            	new Kundenfenster("Kundenverwaltung").setVisible(true);
	            }
	    });
	}
	
	public static void main(String[] args)
	{
		new ClientStart();
	}
}
