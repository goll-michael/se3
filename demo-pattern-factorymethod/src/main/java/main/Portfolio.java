package main;

import java.util.ArrayList;

//Oberklasse
public abstract class Portfolio {
	ArrayList<Produkt> produkte = new ArrayList<Produkt>();

	/*
	 * Die Festlegung, welche Produkte zum Portfolio gehoeren, wird in der
	 * Unterklasse durchgefuehrt.
	 */
	protected abstract void erstellePortfolio();

	public void einfuegeProdukt(Produkt einProdukt) {
		produkte.add(einProdukt);
	}

	public void druckePortfolio() {
		erstellePortfolio();
		for (Produkt produkt : produkte)
			System.out.println("Sparkasse " + produkt.getName() + " gewährt auf " + produkt.getArt() + " "
					+ produkt.getZinssatz() + "% Zinsen.");
	}
}
