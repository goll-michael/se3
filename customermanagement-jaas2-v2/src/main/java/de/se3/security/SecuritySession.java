

package de.se3.security;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;

import de.se3.security.administration.DataSecuritySession;
import de.se3.security.authentication.RBCallbackHandler;

/**
 * @author stephanberg@stephanberg.eu
 * Diese Klasse stellt die jeweilige SecuritySession da. Sie ist neben der
 * gesamten Verwaltung für das Laden der JAAS-Konfiguration und der Policy
 * zustaendig. Entsprechende Konfigurationsdateien müssen entweder per Parameter
 * uebergeben oder hier fest eingestellt werden.
 */
public class SecuritySession {



    private static SecuritySession instance = null;
    private static DataSecuritySession dss = new DataSecuritySession();
    public static LoginContext lc = null;
    private String role;

    protected SecuritySession(){
        
//	    System.setProperty("java.security.auth.login.config", "/config/sample_jaas.config");
//        System.setProperty("security.policy", "/config/sample_jaas.policy");
//        System.setProperty("java.security.debug", "all");
        System.setSecurityManager(new ReferenceMonitor());

	    try{
                 lc = new LoginContext("Sample", new RBCallbackHandler());
            } catch (Exception e) {
                System.out.print(e.getCause());
            }
    }

    public void setLoginContext(LoginContext lc){
        this.lc = lc;
    }

    public LoginContext getLoginContext(){
        return lc;
    }

    public boolean isLoggedIn(){

        if(null == lc.getSubject() || lc.getSubject().getPrincipals().isEmpty())
            return false;
        return true;
    }

    public void login(){
        try{

            lc.login();

        } catch (Exception e) {
            System.out.print(e.getClass());
        }

        if(isLoggedIn()){
           SingleAccessPoint.getInstance().checked();
        } else {
           SingleAccessPoint.getInstance().failed();
        }

    }

    public void logout(){
        try{
            lc.logout();
        } catch (Exception e) {
            System.out.print(e.getClass());
        }
    }

    public static SecuritySession getSecuritySession(){

          if(null == instance)
            instance = new SecuritySession();
          return instance;
    }

    public Subject getSubject(){

        return lc.getSubject();
    }

    public Boolean isAllowedToLogin(String role, String password, String userlogin){
        return dss.checkUser(userlogin, password, role);
    }

    public static String[] getAllPossibleRoles(){

        return dss.getRoles();
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }
}
