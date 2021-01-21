
package de.se3.security.administration;
import java.util.LinkedList;
/**
 * @author stephanberg@stephanberg.eu
 * Diese Klasse dient zur Unterstuetzung bei der Verwaltung der
 * Rolle bzw. der zugehoerigen Rechte. Sie enstammt noch der urspruenglichen
 * Datenhaltung per Datenbank. 
 */
public class RoleRights {

        public String[][] getActiveRights(String roleName){

        if(null == roleName || roleName.isEmpty())
            return null;

        Role role = Roles.getInstance().getRole(roleName);
        LinkedList<Right> rights = role.getRights();

        if(rights.size() == 0){
            return null;
        }

        String[][] result = new String[rights.size()][2];
        int row = 0;
        for(Right right : rights){
                result[row][0] = right.getObject();
                result[row++][1] = right.getAction();
        }

        return result;
    }

    public String[] getPossibleUserRights(String roleName){

        LinkedList<String> posRights = new LinkedList<String>();

        LinkedList<Right> rights = Rights.getInstance().getList();
        Role role = Roles.getInstance().getRole(roleName);
        for(Right right: rights){
            if(!role.hasRight(right))
                posRights.add(right.getObject()+right.getAction());
        }
        String[] result = new String[posRights.size()];
        int row =  0;
        for(String rightName : posRights){
            result[row++] = rightName;
        }

        return result;

    }

    public Right[] getPossibleUserRightsRole(String roleName){

        LinkedList<Right> posRights = new LinkedList<Right>();

        LinkedList<Right> rights = Rights.getInstance().getList();
        Role role = Roles.getInstance().getRole(roleName);
        for(Right right: rights){
            if(!role.hasRight(right))
                posRights.add(right);
        }
        Right[] result = new Right[posRights.size()];
        int row =  0;
        for(Right rightName : posRights){
            result[row++] = rightName;
        }

        return result;

    }

    public static boolean insertRoleRight(final String roleName, final Right rightToInsert){

        Role role = Roles.getInstance().getRole(roleName);
        LinkedList<Right> rights = Rights.getInstance().getList();
        for(Right right : rights){
            if(rightToInsert.equals(right))
                return role.addRight(right);
        }
        return false;
    }
}
