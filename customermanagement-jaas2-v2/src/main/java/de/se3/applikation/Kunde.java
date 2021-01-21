package de.se3.applikation;
import java.util.ArrayList;
import java.util.Date;


public class Kunde 
{
	private NameT name;
	private AdresseT adresse;
	private KontaktT kontakt;
	private BankT bank;
	private Date agbBestaetigt;
	private int nummer;
	private ArrayList<Anzeige> anzeigeListe = new ArrayList<Anzeige>();
	
	
	public Kunde() 
	{
	}

	public void setLinkAnzeige(Anzeige anzeige) 
	{
		if(!this.anzeigeListe.contains(anzeige)) 
		{
			this.anzeigeListe.add(anzeige);
		}
	}
	
	public void removeLinkAnzeige(Anzeige anzeige) 
	{
		this.anzeigeListe.remove(anzeige);
	}
	
	// getter und setter
	public NameT getName() 
	{
		return name;
	}
	public void setName(NameT name) 
	{
		this.name = name;
	}
	public AdresseT getAdresse() 
	{
		return adresse;
	}
	public void setAdresse(AdresseT adresse) 
	{
		this.adresse = adresse;
	}
	public KontaktT getKontakt() 
	{
		return kontakt;
	}
	public void setKontakt(KontaktT kontakt) 
	{
		this.kontakt = kontakt;
	}
	public BankT getBank() 
	{
		return bank;
	}
	public void setBank(BankT bank) 
	{
		this.bank = bank;
	}
	public Date getAgbBestaetigt() 
	{
		return agbBestaetigt;
	}
	public void setAgbBestaetigt(Date agbBestaetigt) 
	{
		this.agbBestaetigt = agbBestaetigt;
	}
	public ArrayList<Anzeige> getAnzeigen() 
	{
		return anzeigeListe;
	}
	public void setAnzeigen(ArrayList<Anzeige> anzeigen) 
	{
		this.anzeigeListe = anzeigen;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public int getNummer() {
		return nummer;
	}
	
	public String getAnzeigename() 
	{
		return name.getNachname() + ", " + name.getVorname();
	}
}