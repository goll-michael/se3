
package de.se3.security;

import javax.swing.SwingUtilities;

import de.se3.gui.AnzeigeKundeGUI;

   /**
    Startpunkt der gesamten Anwendung.
    */

public class Start implements SingleAccessPointCaller{

    public static void main(String[] args) {

        new Start();
    }

    public Start(){

        SingleAccessPoint.getInstance().loginAndStart(this);
    }


    public void start(){

        if(SecuritySession.getSecuritySession().isLoggedIn()){

            SwingUtilities.invokeLater(new Runnable()
	     {
	            public void run()
	            {
	            	AnzeigeKundeGUI dasAnwendungsfenster = new AnzeigeKundeGUI("Anzeigen- und Inserentenverwaltung");
	            	dasAnwendungsfenster.initGUI();
	            }
	        });
        }
    }
}



