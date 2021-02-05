package server.schnittstelle.fachkonzept;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Klasse BankT
 * Die Klasse wird bei der Abbildung auf ein Datenbankschema in die Tabelle
 * der Klasse Kunde eingebettet. 
 * 
 * Ist ebenfalls auf der Clientseite vorhanden und muss fuer die Kommunikation
 * von Server zu Client Serializable implementieren.
 *
 */
@Embeddable
public class BankT implements Serializable
{
	private static final long serialVersionUID = -5035204240494441817L;
	
	private int kontonummer;
	private int blz;
	private String name;	
	
	public int getKontonummer() 
	{
		return kontonummer;
	}
	public void setKontonummer(int kontonummer) 
	{
		this.kontonummer = kontonummer;
	}
	public int getBlz() 
	{
		return blz;
	}
	public void setBlz(int blz) 
	{
		this.blz = blz;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
}
