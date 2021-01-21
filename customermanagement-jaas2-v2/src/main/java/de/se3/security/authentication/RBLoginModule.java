

package de.se3.security.authentication;

import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import de.se3.security.SecuritySession;

/**
 * @author stephanberg@stephanberg.eu
 * Implementierung des LoginModuls.
 * Siehe Ausarbeitungen zum Seminar
 */
public class RBLoginModule implements LoginModule{

    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;

    private boolean debug = false;

    private boolean succeeded = false;
    private boolean commitSucceeded = false;

    private String username;
    private String role;
    private String password;

    private RolePrincipal userPrincipal;


    public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {

	this.subject = subject;
	this.callbackHandler = callbackHandler;
	this.sharedState = sharedState;
	this.options = options;
	debug = "true".equalsIgnoreCase((String)options.get("debug"));
    }

    public boolean login() throws LoginException {

	if (callbackHandler == null)
	    throw new LoginException("Error: no CallbackHandler available " +
			"to garner authentication information from the user");

	Callback[] callbacks = new Callback[1];
	callbacks[0] = new RBCallback();

	try {

	    callbackHandler.handle(callbacks);

	    username = ((RBCallback)callbacks[0]).getUser();
            role = ((RBCallback)callbacks[0]).getRole();
	    password = ((RBCallback)callbacks[0]).getPassword();

            System.out.println("Rolle " + role);
            System.out.println("Password " + password);
            System.out.println("Benutzername " + username);


            if(SecuritySession.getSecuritySession().isAllowedToLogin(role, password, username)){
                succeeded = true;
            } else {
                succeeded = false;
            }
            
	} catch (java.io.IOException ioe) {
	    throw new LoginException(ioe.toString());

        } catch (UnsupportedCallbackException uce) {

	    throw new LoginException("Error: " + uce.getCallback().toString() +
		" not available to garner authentication information " +
		"from the user");
	} 
	    return true;
    }
    
    public boolean commit() throws LoginException {
	if (succeeded == false) {
	    return false;
	} else {

	    userPrincipal = new RolePrincipal(username);
            SecuritySession.getSecuritySession().setRole(role);

	    username = null;
	    password = null;
            role = null;
            subject.getPrincipals().add(userPrincipal);
	    commitSucceeded = true;
	    return true;
	}
    }

    public boolean abort() throws LoginException {

	if (succeeded == false) {
	    return false;
	} else if (succeeded == true && commitSucceeded == false) {
	    succeeded = false;
	    username = null;

	    userPrincipal = null;
	} else {
	    logout();
	}
	return true;
    }


    public boolean logout() throws LoginException {

	subject.getPrincipals().remove(userPrincipal);
	succeeded = false;
	succeeded = commitSucceeded;
	username = null;

	userPrincipal = null;

	return true;
    }

}
