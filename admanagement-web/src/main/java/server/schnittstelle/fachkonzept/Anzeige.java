package server.schnittstelle.fachkonzept;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Klasse Anzeige
 * Enthält JPA-Annotationen für das objektrelationale Mapping
 * 
 * Ist ebenfalls auf der Clientseite vorhanden und muss fuer die Kommunikation
 * von Server zu Client Serializable implementieren.
 *
 */
@Entity
@Table(name = "anzeigen")
public class Anzeige implements Serializable
{
	private static final long serialVersionUID = -2566718324812101096L;

	@Id
	@Column(name="anzeigenr")	
	private long nummer;	
	
	private String rubrik;
	private String titel;
	private String beschreibung;
	private int preis;
	
	@ManyToOne(targetEntity=Kunde.class)
	private Kunde kunde;
	
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
	}

	public void setKundeBeidseitig(Kunde kunde)
	{
		if(this.kunde != null) 
		    this.kunde.removeLinkAnzeige(this);
		this.kunde = kunde;
		if(kunde != null)
			kunde.setLinkAnzeige(this); 
	}

	public void setNummer(long nummer) 
	{
		this.nummer = nummer;
	}

	public long getNummer() 
	{
		return nummer;
	}
	
	public String getAnzeigename()
	{
		return nummer + " - " + titel;
	}
}