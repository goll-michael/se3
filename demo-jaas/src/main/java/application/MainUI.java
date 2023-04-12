package application;

import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import secure.PermissionEdit;
import secure.PermissionResearch;

public class MainUI {

	/*
	 * Wird hier uebergeben von LoginFenster und stellt die Verbindung zwischen JAAS
	 * und der Anwendung, die JAAS benutzt, her.
	 */
	private static LoginContext loginContext = null;
	
	// Dies ist der Name der Login Konfiguration in der Datei login.config
	private static final String CUSTOM_LOGIN_CONFIG = "CustomLoginConfig";
	
	private static boolean permissionEdit = false;
	private static boolean permissionSearch = false;
	
	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new SecurityManager());
		
		ueberpruefeAuth("abc", "def");
		setzeErlaubnisse();
		testPermissions();
		
		ueberpruefeAuth("benutzer1", "passwort1");
		setzeErlaubnisse();
		testPermissions();
		
		ueberpruefeAuth("benutzer2", "passwort2");
		setzeErlaubnisse();
		testPermissions();
	}
	
	private static void testPermissions() {
		System.out.println(String.format("permissions [edit: %s, search: %s]\n#####\n", permissionEdit, permissionSearch));
	}
	
	
	private static void setzeErlaubnisse()
	{
		Subject subj = loginContext.getSubject();
		if(subj == null) {
			return;
		}
		
		System.out.println(subj.toString());
		
		Subject.doAsPrivileged(subj, new PrivilegedAction<Object>()
		{
			public Object run()
			{
				try
				{
					System.getSecurityManager().checkPermission(new PermissionEdit<Object>());
					permissionEdit = true;
				}
				catch(java.security.AccessControlException ace)
				{
					System.out.println("edit nicht erlaubt");
					permissionEdit = false;
				}
				return null;
			}
			
		}, null);
		
		Subject.doAsPrivileged(subj, new PrivilegedAction<Object>()
		{
			public Object run()
			{
				try
				{
					System.getSecurityManager().checkPermission(new PermissionResearch<Object>());
					permissionSearch = true;
				}
				catch(java.security.AccessControlException ace)
				{
					System.out.println("suche nicht erlaubt");
					permissionSearch = false;
				}
				return null;
			}
		}, null);
	}
	
	private static boolean ueberpruefeAuth(String name, String password) {
		try {
			SecureCallbackHandler callbackHandler = new SecureCallbackHandler();
			callbackHandler.setName(name);
			callbackHandler.setPassword(password.toCharArray());
			loginContext = new LoginContext(CUSTOM_LOGIN_CONFIG, callbackHandler);
			loginContext.login();
			return true; // nur dann hier, wenn keine Exception auftrat
		} catch (LoginException | SecurityException ex) {
			System.err.println("Kann LoginContext nicht aufbauen. " + ex.getMessage());
		}
		return false;
	}
}