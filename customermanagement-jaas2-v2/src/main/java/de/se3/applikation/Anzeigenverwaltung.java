package de.se3.applikation;

/** Container zum Verwalten von Anzeigen und Kunden **/

import java.util.*;

import de.se3.applikation.Anzeige;
import de.se3.applikation.Kunde;
import de.se3.datenhaltung.Datenbasis;
import de.se3.datenhaltung.ObjektDatei;
import de.se3.security.administration.Administration;
import de.se3.security.administration.Rights;
import de.se3.security.administration.Roles;
import de.se3.security.administration.Users;


public class Anzeigenverwaltung extends Observable 
{
  //Attribute
  private Datenbasis basis; 
  private ObjektDatei eineObjektDatei;

  //Singleton-Muster
  private static Anzeigenverwaltung 
      einObjektContainer = null;


  //Name der Datei, in der die Daten abgespeichert werden.
  private String datei = "Datenbasis.xml";
  
  //Konstruktor -private!
  private Anzeigenverwaltung()
  {
    // fuer BlueJ  
    Thread.currentThread().setContextClassLoader(getClass().getClassLoader()); 
    
    //Gespeicherte Daten einlesen
    //Falls noch keine Daten gespeichert wurden, kann keine    
    //Datei gelesen werden, es gibt dann eine Ausnahme
    eineObjektDatei = new ObjektDatei(datei);
    try
    {
        basis = (Datenbasis)eineObjektDatei.leseObjekt();
        if(basis == null)
        	basis = new Datenbasis();
    }
    catch (Exception e)
    {
        //wenn keine Daten gelesen werden konnten, muss eine
        //neue Datenbasis angelegt werden
        System.out.println
         ("Es wurde eine neue Datenbasis angelegt");
        basis = new Datenbasis();
    }
  }

  //Klassen-Operation, die die Objektreferenz liefert
  //Wenn Objekt noch nicht vorhanden, dann wird es erzeugt
  public static Anzeigenverwaltung getObjektreferenz()
  {
    if (einObjektContainer == null)
    {
        einObjektContainer = new Anzeigenverwaltung();
    }
    return einObjektContainer;
  }

  //Methoden fuer Aufgaben-------------------------------
  public void einfuegeAnzeige(Anzeige eineAnzeige)
  {
	  if(!basis.getMeineAnzeigen().contains(eineAnzeige)) 
	  {
		  basis.getMeineAnzeigen().add(eineAnzeige);//hinten anfuegen
		  setChanged();
		  notifyObservers();
	  }
  }
  
  public Iterator <Anzeige> iteratorAnzeigen()
  {
	  return basis.getMeineAnzeigen().iterator();
  }
  
  public int getAnzeigenzahl() 
  {
	  return basis.getMeineAnzeigen().size();
  }

  public int getNaechsteAnzeigenNr()
  {
		int max = 0;
		Iterator<Anzeige> iter = iteratorAnzeigen();
		while (iter.hasNext()) 
		{
			Anzeige anzeige = iter.next();
			max = Math.max(max, anzeige.getNummer());
		}
		return max + 1;
  }
  
  public int getKundenzahl() 
  {
	  return basis.getMeineKunden().size();
  }
   
  public int getNaechsteKundenNr()
  {
		int max = 0;
		Iterator<Kunde> iter = iteratorKunden();
		while (iter.hasNext()) 
		{
			Kunde kunde = iter.next();
			max = Math.max(max, kunde.getNummer());
		}
		return max + 1;
  }
	
  public Anzeige gibAnzeige(int position)
  {
    return basis.getMeineAnzeigen().get(position);
 }

  //Methoden fuer Kunden------------------------------
  public void einfuegeKunde(Kunde eineKunde)
  {
	  if(!basis.getMeineKunden().contains(eineKunde)) 
	  {
		  basis.getMeineKunden().add(eineKunde);//hinten anfuegenW
	  } 
	  setChanged();
	  notifyObservers();
  }

  public Iterator<Kunde> iteratorKunden()
  {
    return basis.getMeineKunden().iterator();
  }
    
  public Kunde gibKunde(int position)
  {
	  return basis.getMeineKunden().get(position);
  }

  
  //Liefert Vorgaenger des Objektes o. Existiert kein
  //Vorgaenger, so wird das letzte Element im Container
  //zurueckgegeben
  public Object liefereVorgaenger(Object o)
  {
	  ArrayList liste = getListe(o);
	  
      int position = liste.indexOf(o);
      if (position > 0)
          return liste.get(position-1);
      else if(liste.size() > 0) return liste.get(liste.size()-1);
      
      return o;
  }
  
  //Liefert Nachfolger des Objektes o. Existiert kein Nachfolger,
  //so wird das erste Element im Container zurueckgegeben.
  public Object liefereNachfolger(Object o)
  {
	  ArrayList liste = getListe(o);
	  int position = liste.indexOf(o);
      if (position < liste.size()-1)
          return liste.get(position+1);
      else if(liste.size() > 0) return  liste.get(0);
      
      return o;
  }
  
  // liefert Liste des Objekts (Kunden- oder Anzeigeliste)
 private ArrayList getListe(Object o) 
 {
	  ArrayList liste = null;
	  if(o.getClass() == Kunde.class) return basis.getMeineKunden();
	  else if(o.getClass() == Anzeige.class) return basis.getMeineAnzeigen();
	  else return null;
 }

 public void entferneAnzeige(Anzeige anzeige)
 {
	basis.getMeineAnzeigen().remove(anzeige);  
 }

 public void entferneKunde(Kunde einKunde) 
 {
    basis.getMeineKunden().remove(einKunde);
    
    // entferne auch alle Anzeigen zum Kunden
    ArrayList<Anzeige> zuEntfernend = new ArrayList<Anzeige>();
    Iterator <Anzeige> iter = iteratorAnzeigen();
    while(iter.hasNext()) 
    {
    	Anzeige anzeige = iter.next();
    	if(anzeige.getKunde() == einKunde) 
    		zuEntfernend.add(anzeige);
    }
    iter = zuEntfernend.iterator();
    while(iter.hasNext()) 
    	entferneAnzeige(iter.next());
    
    setChanged();
    notifyObservers();
 }

 
  //Methode zum Speichern der Daten ------------------
  public void endeAnwendung()
  {
    eineObjektDatei.speichereObjekt(basis);

    /* Speichert alle Einstellungen der Rechteverwaltung */
    Rights.getInstance().speichere();
    Roles.getInstance().speichere();
    Users.getInstance().speichere();
    Administration.getInstance().speichere();
    System.out.println("Datenbasis wurde gespeichert");
  }
  
  
}