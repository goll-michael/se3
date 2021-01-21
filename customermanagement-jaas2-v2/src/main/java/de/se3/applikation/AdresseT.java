package de.se3.applikation;

public class AdresseT 
{
	public enum Land { Deutschland, China, Tuerkei;}
	
	private String plz;
	private String ort;
	private Land land;
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