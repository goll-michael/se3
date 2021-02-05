package server.schnittstelle.fachkonzept;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Klasse KontaktT
 * Die Klasse wird bei der Abbildung auf ein Datenbankschema in die Tabelle
 * der Klasse Kunde eingebettet. 
 * 
 * Ist ebenfalls auf der Clientseite vorhanden und muss fuer die Kommunikation
 * von Server zu Client Serializable implementieren.
 *
 */
//@Resource
@Embeddable
public class KontaktT implements Serializable
{
	private static final long serialVersionUID = -504279008826979103L;
	
	private String email;
	private String telefon;
	private String mobil;
	private String fax;   // optional
	
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getTelefon() 
	{
		return telefon;
	}
	public void setTelefon(String telefon) 
	{
		this.telefon = telefon;
	}
	public String getFax() 
	{
		return fax;
	}
	public void setFax(String fax) 
	{
		this.fax = fax;
	}
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	public String getMobil() {
		return mobil;
	}
}
