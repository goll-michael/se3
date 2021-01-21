package de.se3.security;

import java.security.Permission;
import javax.security.auth.Subject;

/**
 *
 * @author sberg
 * Das Interface setzt das Entwurfmuster CheckPoint um. 
 */
public interface CheckPoint {

    public boolean check(Permission p, Subject s);
    
}
