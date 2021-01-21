package muster3_bsp_neu;

public class Selbstkosten implements Preis {
	private double preis;

	public Selbstkosten(double preis) {
		this.preis = preis;
	}

	@Override
	public Preis naechsterStatus() {
		return new Verkauf(preis * 1.05);
	}

	@Override
	public void ausgebenPreis() {
		System.out.println("Der aktuelle Preis liegt bei " + preis + " Euro.");
	}
}
