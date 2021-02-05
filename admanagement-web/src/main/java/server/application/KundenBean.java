package server.application;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import server.schnittstelle.fachkonzept.Kunde;

/**
 * Stateless Session Bean zum Speichern und Laden von Kunden-Objekten in die/aus der Datenbank
 */
@Stateless
public class KundenBean implements KundenBeanLocal {

	//Name der Persistenzdatei avWEB-Persistenz-ds.xml ohne den Suffix "-ds.xml"	
	@PersistenceContext(unitName = "amWEB-Persistenz")
	private EntityManager entityManager;	
	
	/**
	 * Löscht einen Kunden aus de Datenbank
	 */
	public void entferneKunde(Kunde einKunde)
	{
		entityManager.remove(entityManager.merge(einKunde));
		entityManager.flush();
	}

	/**
	 * Liefert das Kunden-Objekt zur übergebenen Kundennummer oder null,
	 * wenn kein Kunde mit der Nummer existiert
	 */
	public Kunde getKundeZuNr(long nummer)
	{
		try
		{
			return (Kunde) entityManager.find(Kunde.class, nummer);
		}
		catch(Exception e)
		{
			System.out.println("Ein Kunde mit dieser Nummer existiert nicht!");
		}
		return null;
	}

	/**
	 * Liefert eine Liste alle Kunden
	 */
	@SuppressWarnings("unchecked")
	public List<Kunde> getKundenListe()
	{
		Query query = entityManager.createQuery("select k from Kunde k order by k.name.nachname, k.name.vorname");
		return (List<Kunde>)query.getResultList();
	}

	/**
	 * Liefert die nächste freie Kundennummer
	 */
	public long getNaechsteKundenNr()
	{
		long max = 0;
		Query query = entityManager.createQuery("select k from Kunde k order by k.nummer desc");
		query.setMaxResults(1);
		Kunde kunde = null;
		try
		{
			kunde = (Kunde)query.getSingleResult();
		}
		catch(NoResultException e)
		{
			System.out.println("Bisher wurden keine Kunden in der Datenbank gespeichert!");
		}
		if(kunde != null)
			max = kunde.getNummer();
		return max + 1;
	}

	/**
	 * Speichert ein Kunden-Objekt in der Datenbank
	 */
	public void speichereKunde(Kunde einKunde) 
	{
		entityManager.merge(einKunde);
		entityManager.flush();
	}

}
