package server.schnittstelle.fachkonzept;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Klasse AdresseT
 * Die Klasse wird bei der Abbildung auf ein Datenbankschema in die Tabelle
 * der Klasse Kunde eingebettet.
 * 
 * Ist ebenfalls auf der Clientseite vorhanden und muss fuer die Kommunikation
 * von Server zu Client Serializable implementieren.
 *
 */
@Embeddable
public class AdresseT implements Serializable
{
	public enum Land {
		Deutschland, Frankreich, Spanien;
	}
	
	private static final long serialVersionUID = -3396209505203473738L;
	
	private String plz;
	private String ort;
	@Enumerated(EnumType.STRING)	
	private Land land = Land.Deutschland;
	private String strasse;
	private String hausnummer;
	
	public String getPlz() 
	{
		return plz;
	}
	public void setPlz(String plz) 
	{
		this.plz = plz;
	}
	public String getOrt() 
	{
		return ort;
	}
	public void setOrt(String ort) 
	{
		this.ort = ort;
	}
	public Land getLand() 
	{
		return land;
	}
	public void setLand(Land land) 
	{
		this.land = land;
	}
	public String getStrasse() 
	{
		return strasse;
	}
	public void setStrasse(String strasse) 
	{
		this.strasse = strasse;
	}
	public String getHausnummer() 
	{
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) 
	{
		this.hausnummer = hausnummer;
	}
}