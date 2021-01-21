

package de.se3.security.administration;

import java.security.Permission;
import javax.security.auth.Subject;

import de.se3.security.CheckPoint;
import de.se3.security.SwingPermission;

/**
 * @author stephanberg@stephanberg.eu
 * Diese Klasse dient zur Erleichterung der Konfiguration.
 * Alle Rechte die angefragt werden, werden hiermit automatisch
 * in die Liste der zuweisenbaren Rechte aufgenommen. Auf diese Weise
 * koennen neue Komponenten beliebig dem Programm ergaenzt werden.
 * Die benoetigten Rechte registrieren sich selbst und sind nachdem
 * das Element erstmalig in der Anwedung instanziert wurde, zuweisbar.
 */
public class RightsGenerator implements CheckPoint{

    Rights rights = null;

    public RightsGenerator(){
        rights = Rights.getInstance();
    }


    public boolean check(Permission p, Subject s) {

        if(p instanceof SwingPermission)
            if(!isRightExisting((SwingPermission)p))
                createNewRight((SwingPermission)p);

        return true;
    }

    public boolean createNewRight(final SwingPermission p){

        if(isRightExisting(p))
            return false;

        rights.getList().add(new Right(p.getObjectID(), p.getAction()));
        return true;
    }

    private boolean isRightExisting(final SwingPermission p){

        return rights.isRightExisting(new Right(p.getObjectID(), p.getAction()));

    }


}
