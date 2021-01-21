package server.persistence;
/* Programmname: Objektspeicherung
 * Datenhaltungs-Klasse: ObjektDatei
 * Aufgabe: Eine Objekt nach und von
 * XML serialisieren. 
 */

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

public class ObjektSpeicher implements Serializable
{
	//Name der Datei, in der die Kunden gespeichert werden sollen
	private String einDateiname = "Datenbasis.xml";
    
	public ObjektSpeicher()
	{
	}
    
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
	}
	
	public Object leseObjekt() throws Exception
    {
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(einDateiname)));
		Object result = d.readObject();
		d.close();
		return result;
	}
}

