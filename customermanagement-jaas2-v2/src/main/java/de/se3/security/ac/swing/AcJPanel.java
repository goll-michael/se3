

package de.se3.security.ac.swing;

import javax.swing.JPanel;

/**
 * @author stephanberg@stephanberg.eu
 *
 * Die Klasse erbt von seiner Swing-Komponente. Die entsprechenden Methoden
 * wurden um eine Berechtigungspruefung erweitert. Entsprechend dem typischen
 * Verhalten bei fehlenden Berechtigungen bei der Verwendung des SecurityManagers,
 * wird bei fehlender Berechtigung eine Exception geworfen.
 */
public class AcJPanel extends JPanel 
{

protected String identity;

    public AcJPanel (String identity){
       this.identity = identity;
       this.setToolTipText(identity);
       this.setVisible(true);
       this.setEnabled(true);
    }


    public void setEnabled(boolean b){
        
        super.setEnabled(AcEnforcer.getEnabled(b, identity));
    }

    public void setVisible(boolean b){

        super.setVisible(AcEnforcer.getVisible(b, identity));
    }
}
