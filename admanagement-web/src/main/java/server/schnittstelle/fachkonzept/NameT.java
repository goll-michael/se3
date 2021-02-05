package server.schnittstelle.fachkonzept;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Klasse NameT
 * Die Klasse wird bei der Abbildung auf ein Datenbankschema in die Tabelle
 * der Klasse Kunde eingebettet. 
 * 
 * Ist ebenfalls auf der Clientseite vorhanden und muss fuer die Kommunikation
 * von Server zu Client Serializable implementieren.
 *
 */
@Embeddable
public class NameT implements Serializable
{
	private static final long serialVersionUID = 951142628975676158L;
	
	public enum Anrede {
		undefiniert, HERR, FRAU;
	}

	@Enumerated(EnumType.STRING)	
	private Anrede anrede = Anrede.undefiniert;
	private String vorname;
	private String nachname;
	
	public Anrede getAnrede() 
	{
		return anrede;
	}
	public void setAnrede(Anrede anrede) 
	{
		this.anrede = anrede;
	}
	public String getVorname() 
	{
		return vorname;
	}
	public void setVorname(String vorname) 
	{
		this.vorname = vorname;
	}
	public String getNachname() 
	{
		return nachname;
	}
	public void setNachname(String nachname) 
	{
		this.nachname = nachname;
	}
}

