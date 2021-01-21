

package de.se3.security;

import java.security.Permission;
import javax.security.auth.Subject;

import de.se3.security.administration.*;

/**
 * @author stephanberg@stephanberg.eu
 * Entsprechend dem Muster CheckPoint, wertet diese Klasse
 * Pruefungen fuer die erweiterten Swing-Elemente aus.
 */
public class CheckPointSwing implements CheckPoint{

    public boolean check(Permission sp, Subject s){

        return checkPermission((SwingPermission)sp,
                SecuritySession.getSecuritySession().getRole());
    }

        public boolean checkPermission(SwingPermission sp, String roleName){

            Role role = Roles.getInstance().getRole(roleName);

            Right right = Rights.getInstance().getRight(sp.getObjectID(), sp.getAction());
            if(null == right) return false;

            if(role.hasRight(right))
                return true;
            return false;

        }


}
