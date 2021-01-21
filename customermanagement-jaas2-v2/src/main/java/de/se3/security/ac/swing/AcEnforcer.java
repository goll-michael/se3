package de.se3.security.ac.swing;

import de.se3.security.ReferenceMonitor;
import de.se3.security.SecuritySession;
import de.se3.security.SwingPermission;
import de.se3.security.SwingSecurityException;

/**
 *
 * @author sberg
 * Methoden die um eine Rechtepruefung erweitert wurden.
 */
public class AcEnforcer 
{
	public static boolean getEnabled(boolean b, String identity)
	{
		boolean retval = false;
	    SecurityManager sm = System.getSecurityManager();
	    if(null != sm && sm instanceof ReferenceMonitor){
	        SwingPermission perm = new SwingPermission(identity, "enable");
	        try{
	
	        ((ReferenceMonitor)sm).checkPermission
	                (perm, SecuritySession.getSecuritySession().getSubject());
	        retval  = b;
	        } catch (SwingSecurityException sse){
	            retval = false;
	        } catch (SecurityException se){
	
	        }
	    }
	    return retval;
	}
	public static boolean getVisible(boolean b, String identity)
	{
		boolean retval = false;
	    SecurityManager sm = System.getSecurityManager();
	    if(null != sm && sm instanceof ReferenceMonitor){
	        SwingPermission perm = new SwingPermission(identity, "visible");
	        try{
	
	        ((ReferenceMonitor)sm).checkPermission
	                (perm, SecuritySession.getSecuritySession().getSubject());
	            retval = b;
	        } catch (SwingSecurityException sse){
	            retval = false;
	        } catch (SecurityException se){
	
	        }
	    }
	    return retval;
	}
}
