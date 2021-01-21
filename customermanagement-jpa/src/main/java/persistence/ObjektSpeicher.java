package persistence;
/* Programmname: Objektspeicherung
 * Datenhaltungs-Klasse: ObjektDatei
 * Aufgabe: Eine Objekt nach und von
 * XML serialisieren. 
 */

import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import application.Kunde;

public class ObjektSpeicher
{
	//Attribute
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("kunde");
	private EntityManager em = emf.createEntityManager();
		
	private ArrayList<Kunde> meineKunden;
	
	public ObjektSpeicher()
	{
	}
    
	/**
	 * @SuppressWarnings("unchecked") unterdrueckt bestimmte Compiler-Warnungen.
	 */
	@SuppressWarnings("unchecked")
	public void speichereObjekt(Object einObjekt)
	{
		meineKunden = (ArrayList<Kunde>) einObjekt;
		
		Iterator<Kunde> iter = meineKunden.iterator();
		while (iter.hasNext()) 
		{
			Kunde kunde = iter.next();
			em.getTransaction().begin();
			em.persist(kunde);
			em.getTransaction().commit();
		}	  
		em.close();
		emf.close();
	}
	
	/**
	 * @SuppressWarnings("unchecked") unterdrueckt bestimmte Compiler-Warnungen.
	 */
	@SuppressWarnings("unchecked")
	public Object leseObjekt() throws Exception
    {
		/*
		 * Gespeicherte Daten einlesen.
		 * Falls noch keine Daten gespeichert wurden, kann kein
		 * Datenbankeintrag gelesen werden, es gibt dann eine Ausnahme
		 */ 
		try
		{
			Query query = em.createQuery("select k from Kunde k order by k.nummer");
			  
			//ArrayList<Kunde> wird erwartet
			meineKunden = new ArrayList<Kunde>();
			Iterator<Kunde> iter = query.getResultList().iterator();
			while (iter.hasNext()) 
			{
				Kunde kunde = (Kunde) iter.next();
				meineKunden.add(kunde);
			}
			
	        if(meineKunden == null)
	        	meineKunden = new ArrayList<Kunde>();
		}
		catch (Exception e)
		{
			/*
			 * Wenn keine Daten gelesen werden konnten, muss eine
			 * neue Datenbasis angelegt werden
			 */
			System.out.println("Es wurde eine neue Datenbasis angelegt");
			meineKunden = new ArrayList<Kunde>();
		}
		return meineKunden;
	}
}

