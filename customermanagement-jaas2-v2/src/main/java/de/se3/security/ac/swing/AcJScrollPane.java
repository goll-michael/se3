

package de.se3.security.ac.swing;

import javax.swing.JScrollPane;

/**
 * @author stephanberg@stephanberg.eu
 *
 * Die Klasse erbt von seiner Swing-Komponente. Die entsprechenden Methoden
 * wurden um eine Berechtigungspruefung erweitert. Entsprechend dem typischen
 * Verhalten bei fehlenden Berechtigungen bei der Verwendung des SecurityManagers,
 * wird bei fehlender Berechtigung eine Exception geworfen.
 */
public class AcJScrollPane extends JScrollPane {

    protected String identity;

    public AcJScrollPane(String identity, AcJTable tab){
       super(tab);
       this.identity = identity;

    }


}
