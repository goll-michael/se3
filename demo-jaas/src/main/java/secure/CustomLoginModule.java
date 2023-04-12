package secure;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class CustomLoginModule implements LoginModule 
{
	//Daten von Benutzer 1
	private final static String BENUTZER1 = "benutzer1";
	private final static String PASSWORT1 = "passwort1";
	
	//Daten von Benutzer 2
	private final static String BENUTZER2 = "benutzer2";
	private final static String PASSWORT2 = "passwort2";
	
	private Subject benutzer;
	private CallbackHandler callbackHandler;

	//Authentifizierungs-Status
	private boolean authErfolgreich = false;
	private boolean commitErfolgreich = false;

	//Benutzername und Passwort
	private String benutzername;
	private String passwort;

	public void initialize(Subject benutzer, CallbackHandler callbackHandler,
		Map<java.lang.String, ?> sharedState, Map<java.lang.String, ?> options) 
	{
		this.benutzer = benutzer;
		this.callbackHandler = callbackHandler;
	}


	public boolean login() throws LoginException 
	{
		//Frage nach Benutzername und Passwort
		if (callbackHandler == null) {
			throw new LoginException("Fehler: Kein CallbackHandler verfügbar " +
				"zum Abfragen von Authentifizierungs-Info vom Benutzer");
		}
	
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("user name: ");
		callbacks[1] = new PasswordCallback("password: ", false);
	 
		try 
		{
			callbackHandler.handle(callbacks);
			benutzername = ((NameCallback)callbacks[0]).getName();
			char[] tmpPasswort = ((PasswordCallback)callbacks[1]).getPassword();
			if (tmpPasswort == null)
			{
				//Behandle NULL Passwort wie ein leeres Passwort
				tmpPasswort = new char[0];
			}
			passwort = new String(tmpPasswort);
			((PasswordCallback)callbacks[1]).clearPassword();
	 
		}
		catch (java.io.IOException ioe) 
		{
			throw new LoginException(ioe.toString());
		} 
		catch (UnsupportedCallbackException uce) 
		{
			throw new LoginException("Fehler: " + uce.getCallback().toString() +
				" nicht verfügbar zum Abfragen von Authentifizierungs-Info " +
				"vom Benutzer");
		}
	
		//Verifiziere benutzername/passwort
		boolean benutzernameKorrekt = false;
		if (benutzername.equals(BENUTZER1) || benutzername.equals(BENUTZER2) )
			benutzernameKorrekt = true;
		
		
		if (benutzernameKorrekt && 
			((BENUTZER1.equals(benutzername) && PASSWORT1.equals(passwort)) || 
			(BENUTZER2.equals(benutzername) && PASSWORT2.equals(passwort)))) 
		{
			//Authentifizierung erfolgreich!
			authErfolgreich = true;
			return true;
		}
		else 
		{
			//Authentifizierung gescheitert - setze Zustand zurueck
			authErfolgreich = false;
			benutzername = null;
			passwort = null;
			if (!benutzernameKorrekt) 
				throw new FailedLoginException("Benutzername falsch"); 
			else 
				throw new FailedLoginException("Passwort falsch");
		}
	}

	public boolean commit() throws LoginException 
	{
		if (authErfolgreich == false) 
		    return false;
		else
		{
			if(benutzername.equals(BENUTZER1)) //Benutzer 1 ist Editor
			{
				if (!benutzer.getPrincipals().contains(Role.EDITOR_ROLE))
					benutzer.getPrincipals().add(Role.EDITOR_ROLE);
			}
			else if(benutzername.equals(BENUTZER2)) //Benutzer 2 ist 'nur' Researcher
			{
				if (!benutzer.getPrincipals().contains(Role.RESEARCHER_ROLE))
					benutzer.getPrincipals().add(Role.RESEARCHER_ROLE);
			}
		
			//Setze Zustand zurueck
			benutzername = null;
			passwort = null;
	
			commitErfolgreich = true;
			return true;
		}
	}

	public boolean abort() throws LoginException 
	{
		if (authErfolgreich == false) 
			return false;
		else if (authErfolgreich == true && commitErfolgreich == false) {
			//Login erfolgreich aber Authentisierung gescheitert
			authErfolgreich = false;
			benutzername = null;
			if (passwort != null) {
				passwort = null;
			}
		}
		else 
		{
			//Authentisierung und commit erfolgreich, aber commit von jemand anders gescheitert
			logout();
		}
		return true;
	}

	public boolean logout() throws LoginException 
	{
		benutzer.getPrincipals().clear();
		authErfolgreich = false;
		authErfolgreich = commitErfolgreich;
		benutzername = null;
		if (passwort != null) 
		    passwort = null;
		return true;
	}
}