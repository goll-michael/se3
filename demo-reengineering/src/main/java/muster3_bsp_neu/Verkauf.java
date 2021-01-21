package muster3_bsp_neu;

public class Verkauf implements Preis {
	private double preis;

	public Verkauf(double preis) {
		this.preis = preis;
	}

	@Override
	public Preis naechsterStatus() {
		System.out.println("Verkaufspreis bereits berechnet.");
		return new Verkauf(preis);
	}

	@Override
	public void ausgebenPreis() {
		System.out.println("Der aktuelle Preis liegt bei " + preis + " Euro.");
	}
}
