package server.schnittstelle.fachkonzept;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Target;


/**
 * Klasse Kunde
 * Enthält JPA-Annotationen für das objektrelationale Mapping
 * 
 * Ist ebenfalls auf der Clientseite vorhanden und muss fuer die Kommunikation
 * von Server zu Client Serializable implementieren.
 *
 */
@Entity
@Table(name = "kunden")
public class Kunde implements Serializable
{
	private static final long serialVersionUID = -7142710335529310889L;

	@Id
	@Column(name="kundennr")	
	private long nummer;	
	
	@Embedded
	@Target(NameT.class)
	private NameT name;
	
	@Embedded
	@Target(AdresseT.class)
	private AdresseT adresse;
	
	@Embedded
	@Target(KontaktT.class)
	private KontaktT kontakt;
	
	@Embedded
	@Target(BankT.class)
	private BankT bank;
	
	private Date agbBestaetigt;
	
	@OneToMany(mappedBy="kunde", cascade = CascadeType.REMOVE, targetEntity=Anzeige.class, fetch=FetchType.EAGER)		
	private List<Anzeige> anzeigeListe;
	
	
	public Kunde() 
	{
		anzeigeListe = new ArrayList<Anzeige>();
		name = new NameT();
		adresse = new AdresseT();
		bank = new BankT();
		kontakt = new KontaktT();
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
	
	// getter & setter
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
	
	public List<Anzeige> getAnzeigeListe() 
	{
		return anzeigeListe;
	}
	public void setAnzeigeListe(List<Anzeige> anzeigeListe) 
	{
		this.anzeigeListe = anzeigeListe;
	}

	public void setNummer(long nummer) {
		this.nummer = nummer;
	}
	
	public long getNummer() {
		return nummer;
	}
	
	public String getAnzeigename()
	{
		return name.getNachname() + ", " + name.getVorname();
	}
}