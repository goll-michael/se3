package de.se3.applikation;
public class Anzeige 
{
	private String rubrik;
	private String titel;
	private String beschreibung;
	private int preis;
	private Kunde kunde;
	private int nummer;
	
	// getter und setter
	public String getRubrik() 
	{
		return rubrik;
	}

	public void setRubrik(String rubrik) 
	{
		this.rubrik = rubrik;
	}

	public String getTitel() 
	{
		return titel;
	}

	public void setTitel(String titel) 
	{
		this.titel = titel;
	}

	public String getBeschreibung() 
	{
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) 
	{
		this.beschreibung = beschreibung;
	}

	public int getPreis() 
	{
		return preis;
	}

	public void setPreis(int preis) 
	{
		this.preis = preis;
	}

	public Kunde getKunde() 
	{
		return kunde;
	}

	public void setKunde(Kunde kunde) 
	{
		if(this.kunde != null) 
			this.kunde.removeLinkAnzeige(this);
		
		this.kunde = kunde;
		if(kunde != null)
			kunde.setLinkAnzeige(this); 
	}

	public void setKundeBeidseitig(Kunde kunde) 
	{
		if(this.kunde != null) 
		    this.kunde.removeLinkAnzeige(this);
		this.kunde = kunde;
		if(kunde != null)
			kunde.setLinkAnzeige(this); 
	}

	public void setNummer(int nummer) 
	{
		this.nummer = nummer;
	}

	public int getNummer() 
	{
		return nummer;
	}
}