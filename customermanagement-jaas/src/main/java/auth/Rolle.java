/*
 * @(#)SamplePrincipal.java	1.4 00/01/11
 *
 * Copyright 2000-2002 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * -Redistributions of source code must retain the above copyright  
 * notice, this  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduct the above copyright 
 * notice, this list of conditions and the following disclaimer in 
 * the documentation and/or other materials provided with the 
 * distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any 
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND 
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY 
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY 
 * DAMAGES OR LIABILITIES  SUFFERED BY LICENSEE AS A RESULT OF  OR 
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THE SOFTWARE OR 
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE 
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, 
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER 
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF 
 * THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that Software is not designed, licensed or 
 * intended for use in the design, construction, operation or 
 * maintenance of any nuclear facility. 
 */

package auth;

import java.security.Principal;

public class Rolle implements Principal, java.io.Serializable
{
	/**
	 * Beim Serialisieren eines Objektes wird auch die serialVersionUID der zugehoerigen
	 * Klasse mit in die Ausgabedatei geschrieben. Soll das Objekt spaeter deserialisiert
	 * werden, so wird die in der Datei gespeicherte serialVersionUID mit der aktuellen
	 * serialVersionUID des geladenen .class-Files verglichen. Stimmen beide nicht
	 * ueberein, so gibt es eine Ausnahme des Typs InvalidClassException, und der
	 * Deserialisierungsvorgang bricht ab.
	 */
	private static final long serialVersionUID = 5024568011698477195L;
	
	public static final Rolle KUNDEN_RECHERCHEUR_ROLLE  = 
		new Rolle("Kunden-Rechercheur");
	public static final Rolle KUNDEN_BEARBEITER_ROLLE = 
		new Rolle("Kunden-Bearbeiter");
	
	private String name;

	public Rolle(String name)
	{
		if (name == null)
			throw new NullPointerException("null-Wert nicht erlaubt");
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public String toString()
	{
		return ("Rollenname =  " + name);
	}

	public boolean equals(Object o)
	{
		if (o == null)
			return false;

		if (this == o)
			return true;

		if (!(o instanceof Rolle))
			return false;
		Rolle that = (Rolle) o;

		if (this.getName().equals(that.getName()))
			return true;
		return false;
	}

	public int hashCode()
	{
		return name.hashCode();
	}
}