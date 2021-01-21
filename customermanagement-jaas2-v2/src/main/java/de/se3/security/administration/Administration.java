

package de.se3.security.administration;

import de.se3.datenhaltung.ObjektDatei;

/**
 * @author stephanberg@stephanberg.eu
 *
 * Ueber diese Klasse wird verwaltet, ob die Rechtepruefung
 * aktiviert ist oder nicht. 
 */
public class Administration {

    private boolean permissionSystemEnabled;
    protected static ObjektDatei db = null;
    protected static Administration instance = null;

    public Administration(){
        permissionSystemEnabled = false;
    }

    public static Administration getInstance(){

        if(null == instance){
            db = new ObjektDatei("Admin.xml");
            try{
                instance = (Administration)db.leseObjekt();
            } catch (Exception e) {
                instance = new Administration();
            }
        }
        return instance;
    }

    public void speichere()
    {
        db.speichereObjekt(this);
    }

    public boolean isPermissionSystemEnabled() {
        return permissionSystemEnabled;
    }

    public void setPermissionSystemEnabled(boolean permissionSystemEnabled) {
        this.permissionSystemEnabled = permissionSystemEnabled;
    }



}
