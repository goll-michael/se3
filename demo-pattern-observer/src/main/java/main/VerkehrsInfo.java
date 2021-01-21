package main;

public class VerkehrsInfo {
	public static void main(String[] args) {
		System.out.println("--- Start ---");
		// 2 Handys erzeugen
		Handy helmutHandy = new Handy("Helmut");
		Handy frankHandy = new Handy("Frank");
		// Überwachungsmelder anlegen
		Verkehr autobahn1 = new Verkehr(1);
		Verkehr autobahn2 = new Verkehr(2);
		// Handys als Beobachter registrieren
		autobahn1.addObserver(helmutHandy);
		autobahn2.addObserver(helmutHandy);
		autobahn1.addObserver(frankHandy);
		// Anzahl der Beobachter abfragen
		System.out.println("Anzahl Beobachter - Autobahn 1: " + autobahn1.countObservers());
		System.out.println("Anzahl Beobachter - Autobahn 2: " + autobahn2.countObservers());
		// Staumeldung abfragen, unabhängig von den Handys
		autobahn1.meldeStau();
		autobahn2.meldeStau();
		// 1 Beobachter abmelden
		autobahn2.deleteObserver(helmutHandy);
		// Anzahl der Beobachter abfragen
		System.out.println("Anzahl Beobachter - Autobahn 1: " + autobahn1.countObservers());
		System.out.println("Anzahl Beobachter - Autobahn 2: " + autobahn2.countObservers());
		// Staumeldung abfragen, unabhängig von den Handys
		autobahn1.meldeStau();
		autobahn2.meldeStau();
		System.out.println("--- Ende ---");
	}
}