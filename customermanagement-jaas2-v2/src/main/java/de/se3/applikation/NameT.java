package de.se3.applikation;

public class NameT 
{
	public enum Anrede { HERR, FRAU;}
	private Anrede anrede = Anrede.HERR;
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

