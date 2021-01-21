package de.se3.security.authentication;

import java.security.Principal;

/*
 * Implementierung eines eigenen Principals
 * Siehe Ausarbeitungen zum Seminar
 */

public class RolePrincipal implements Principal, java.io.Serializable {

    private String name;


    public RolePrincipal(String name) {
	if (name == null)
	    throw new NullPointerException("illegal null input");

	this.name = name;
    }

    public String getName() {
	return name;
    }

    public String toString() {
	return("SamplePrincipal:  " + name);
    }


    public boolean equals(Object o) {
	if (o == null)
	    return false;

        if (this == o)
            return true;
 
        if (!(o instanceof RolePrincipal))
            return false;
        RolePrincipal that = (RolePrincipal)o;

	if (this.getName().equals(that.getName()))
	    return true;
	return false;
    }
 
    public int hashCode() {
	return name.hashCode();
    }
}
