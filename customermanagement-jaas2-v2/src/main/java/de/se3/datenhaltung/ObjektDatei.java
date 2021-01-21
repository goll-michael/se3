package de.se3.datenhaltung;
/* Programmname: Objektspeicherung
 * Datenhaltungs-Klasse: ObjektDatei
 * Aufgabe: Eine Objekt nach und von
 * XML serialisieren. 
 */

import java.io.*;
import java.beans.*;

public class ObjektDatei
{
    private String einDateiname;
    
        
    //Konstruktor
    public ObjektDatei(String einDateiname)
    {
        this.einDateiname = einDateiname;
    }
    //Methoden
    public void speichereObjekt(Object einObjekt)
    {
        try
	    {
			XMLEncoder e = 
			    new XMLEncoder(new BufferedOutputStream(new FileOutputStream(einDateiname)));
			e.writeObject(einObjekt);
			e.close();
	    }
        catch (FileNotFoundException e)
	    { 
			System.out.println("Fehler in speichereObjekt: " + e);
	    }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public Object leseObjekt() throws Exception
    {
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(einDateiname)));
		Object result = d.readObject();
		d.close();
		return result;
    }
}

