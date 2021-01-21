package de.se3.security;


import java.security.*;
import javax.security.auth.Subject;

import de.se3.security.administration.Administration;
import de.se3.security.administration.RightsGenerator;

/**
 *
 * @author stephanberg@stephanberg.eu
 * Der ReferenceMonitor ist eine angepasste Version des "normalen" SecurityManagers.
 * Er wurde erweitert um die Moeglichkeit Permission auszuwerten ohne diese
 * an den AccessController zu delegieren.
 * Die Methoden urspruenglichen Methoden checkPermission sind auskommentiert,
 * um sich die Versorgung der entsprechenden Konfigurationsdateien sparen zu
 * können. 
 */
public class ReferenceMonitor extends java.lang.SecurityManager{

    private static int requestCounter = 0;
    private CheckPoint[] cps = null;


    public ReferenceMonitor(){

        cps = new CheckPoint[2];
        cps[0] = new CheckPointSwing();
        cps[1] = new RightsGenerator();
    }

    public void checkPermission(Permission perm) {
//        java.security.AccessController.checkPermission(perm);
    }

    public void checkPermission(Permission perm, Object context) {
//        if (context instanceof AccessControlContext) {
//            ((AccessControlContext)context).checkPermission(perm);
//        } else {
//            throw new SecurityException();
//        }
    }


    public void checkPermission(SwingPermission perm, Subject subj){

        if(null == perm){
            throw new SecurityException("Permission darf nicht null sein.");
        }

        if(null == subj){
            throw new SecurityException("Subjekt darf nicht null sein.");
        }

        System.out.println("Anfrage: " + requestCounter++);

        for (Principal item: subj.getPrincipals()) {

            System.out.println("Principal: " + item.toString());

        }

        System.out.println("Aktion: " + perm.getAction());
        System.out.println("Objekt: " + perm.getObjectID());

        /*
         * In der unkonfigurierten Version sind noch keine Rechte vergeben.
         * Deshalb ist die gesamte Rechteprüfung deaktiviert. Trotzdem werden
         * einlaufende Rechte registiert.
         */
        if(!Administration.getInstance().isPermissionSystemEnabled()){
            if(!cps[1].check(perm, subj)){
                   throw  new SwingSecurityException();
            }
            return;
        }
        
        for(CheckPoint cp : cps){
            if(!cp.check(perm, subj)){
                   throw  new SwingSecurityException();
            }        }        
 }
}
