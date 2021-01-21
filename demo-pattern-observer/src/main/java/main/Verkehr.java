package main;

import java.util.Observable;

public class Verkehr extends Observable {
	private boolean stau = false;
	private int autobahnnr;

	public Verkehr(int autobahnnr) {
		this.autobahnnr = autobahnnr;
	}

	public void meldeStau() {
		if (Math.random() < 0.5f)
			stau = true;
		else
			stau = false;
		setChanged();
		notifyObservers(stau);
	}

	public int gibAutobahnnr() {
		return autobahnnr;
	}
}
