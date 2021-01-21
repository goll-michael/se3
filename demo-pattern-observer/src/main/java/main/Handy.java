package main;

import java.util.Observable;
import java.util.Observer;

public class Handy implements Observer {
	private String besitzer;

	public Handy(String besitzer) {
		this.besitzer = besitzer;
	}

	public void update(Observable o, Object obj) {
		Verkehr einVerkehr = (Verkehr) o;
		System.out.println(
				"Handy von " + besitzer + " meldet Stau auf der Autobahn " + einVerkehr.gibAutobahnnr() + ": " + obj);
	}
}