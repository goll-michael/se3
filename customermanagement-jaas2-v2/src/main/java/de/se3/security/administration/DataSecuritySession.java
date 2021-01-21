

package de.se3.security.administration;
/**
 * @author stephanberg@stephanberg.eu
 * Daten die zur SecuritySession gehoeren.
 */
public class DataSecuritySession {


        public boolean checkUser(String userlogin, String password, String roleName){

            User user = Users.getInstance().getUser(userlogin);
            Role role = Roles.getInstance().getRole(roleName);
            if(null == user || null == role ) return false;
            if(!user.getPassword().equals(password)) return false;
            if(!user.isRole(role)) return false;

            return true;
        }

        public String[] getRoles(){

            return Roles.getInstance().getRolesForLogin();

    }
}
