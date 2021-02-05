package server.application;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import server.schnittstelle.fachkonzept.Anzeige;

/**
 * Stateless Session Bean zum Speichern und Laden von Anzeigen-Objekten in die/aus der Datenbank
 */
@Stateless
public class AnzeigenBean implements AnzeigenBeanLocal
{

	//Name der Persistenzdatei avWEB-Persistenz-ds.xml ohne den Suffix "-ds.xml"
	@PersistenceContext(unitName = "amWEB-Persistenz")
	private EntityManager entityManager;	

	/**
	 * Methode zum Löschen einer Anzeige aus der Datenbank
	 */
	public void entferneAnzeige(Anzeige anzeige)
	{
		entityManager.remove(entityManager.merge(anzeige));	
		entityManager.flush();	
	}

	/**
	 * Methode zum Suchen einer Anzeige anhand ihrer Nummer. Liefert null,
	 * wenn keine Anzeige zur übergebenen Nummer existiert.
	 */
	public Anzeige getAnzeigeZuNr(long nummer)
	{
		try
		{
			return (Anzeige) entityManager.find(Anzeige.class, nummer);
		}
		catch(Exception e)
		{
			System.out.println("Ein Kunde mit dieser Nummer existiert nicht!");
		}
		return null;
	}

	/**
	 * Liefert eine Liste aller Anzeigen
	 */
	@SuppressWarnings("unchecked")
	public List<Anzeige> getAnzeigenListe()
	{
		Query query = entityManager.createQuery("select a from Anzeige a order by a.nummer asc");
		return (List<Anzeige>)query.getResultList();
	}

	/**
	 * Liefert die nächste freie Anzeigennummer
	 */
	public long getNaechsteAnzeigenNr()
	{
		long max = 0;
		Query query = entityManager.createQuery("select a from Anzeige a order by a.nummer desc");
		query.setMaxResults(1);
		Anzeige anzeige = null;
		try
		{
			anzeige = (Anzeige)query.getSingleResult();
		}
		catch(NoResultException e)
		{
			System.out.println("Bisher wurden keine Anzeigen in der Datenbank gespeichert!");
		}
		if(anzeige != null)
			max = anzeige.getNummer();
		return max + 1;
	}

	/**
	 * Speichert das übergebene Anzeigen-Objekt in der Datenbank
	 */
	public void speichereAnzeige(Anzeige eineAnzeige)
	{
		entityManager.merge(eineAnzeige);	
		entityManager.flush();		
	}

}
