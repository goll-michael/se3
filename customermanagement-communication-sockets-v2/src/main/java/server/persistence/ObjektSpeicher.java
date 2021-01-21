package server.persistence;

/* Programmname: Objektspeicherung
 * Datenhaltungs-Klasse: ObjektDatei
 * Aufgabe: Eine Objekt nach und von
 * XML serialisieren. 
 */
import java.io.*;
import java.beans.*;

public class ObjektSpeicher
{
	private String einDateiname;

	// Konstruktor
	public ObjektSpeicher(String einDateiname)
	{
		this.einDateiname = einDateiname;
	}

	// Methoden
	public void speichereObjekt(Object einObjekt)
	{
		try
		{
			XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(einDateiname)));
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
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(einDateiname)));
		Object result = d.readObject();
		d.close();
		return result;
	}
}
