package de.se3.security.administration;

import de.se3.security.administration.ui.UIMain;

/**
 *
 * @author sberg
 * Ruft das Verwaltungsmenue fuer die Berechtigungen auf.
 */
public class PermissionAdministrator {
/*
    public static void main(String[] args) {

        PermissionAdministrator pa = new PermissionAdministrator();
        pa.showPermissionAdministrator(true);

    }
*/
    
    public static void showPermissionAdministrator(boolean standAlone){

        UIMain uimain = new UIMain();
        uimain.show(standAlone);
    }

}
