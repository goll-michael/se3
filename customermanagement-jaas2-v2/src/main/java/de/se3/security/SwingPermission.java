

package de.se3.security;

import java.security.BasicPermission;

/**
 * @author stephanberg@stephanberg.eu
 * Bei der Klasse handelt es sich um die Implementierung der
 * Permission für Swing.
 * Eine Permission oder ein Recht besteht immer aus dem Objekt und
 * der Aktion um die es geht. Siehe Ausarbeitung Seminar.
 */
public class SwingPermission extends BasicPermission{

    protected String objectID;
    protected String action;

    public SwingPermission(String name, String action){
        super(name);
        this.objectID = name;
        this.action = action;
    }

    public String getObjectID(){
        return objectID;
    }

    public String getAction(){
        return action;
    }

}
