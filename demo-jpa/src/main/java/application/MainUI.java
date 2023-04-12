package application;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Entity
public class MainUI {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("benutzer");
		EntityManager em = emf.createEntityManager();

		/* Bestehende Datenbankeinträge löschen */
		em.getTransaction().begin();
		Query delete = em.createQuery("delete from Benutzer");
		delete.executeUpdate();
		em.getTransaction().commit();

		/* Jetzt soll ein Objekt erzeugt ... */
		Benutzer neuerBenutzer = new Benutzer();
		neuerBenutzer.setBenutzernr(4711l);
		neuerBenutzer.setPseudonym("meier2");
		neuerBenutzer.setPasswort("geheim");

		/*
		 * ... und jetzt persistent gespeichert werden Zunächst wird eine neue
		 * Transaktion geöffnet. Anschließend wird das Objekt gespeichert. Am Ende wird
		 * die Transaktion abgeschlossen.
		 */
		em.getTransaction().begin();
		em.persist(neuerBenutzer);
		em.getTransaction().commit();

		/* Jetzt wird das Passwort geändert */
		neuerBenutzer.setPasswort("vergessen");

		/* und die Datenbank wird aktualisiert */
		em.getTransaction().begin();
		em.merge(neuerBenutzer);
		em.getTransaction().commit();

		/*
		 * Jetzt wird ein Benutzer über sein Pseudonym gesucht. Dazu wird JPQL
		 * verwendet. Als Ergebnis wird ein einzelnes Objekt erwartet, so dass
		 * getSingleResult() verwendet werden kann.
		 */
		neuerBenutzer = null;
		Query abfrage = em.createQuery("select b from Benutzer b where b.pseudonym = 'meier2'");

		// Funktioniert nicht!
//    Query abfrage = em.createQuery
//    ("select benutzernr, pseudonym, passwort from Benutzer where pseudonym = 'meier2'");

		neuerBenutzer = (Benutzer) abfrage.getSingleResult();

		System.out.println(neuerBenutzer.getPasswort());

		/*
		 * Am Ende sollte auch der EntityManager geschlossen werden
		 */
		em.close();

		/*
		 * Wenn man sicher ist, dass kein neuer EntityManager mehr gebraucht wird, kann
		 * auch die Factory geschlossen werden.
		 */
		emf.close();
	}

}