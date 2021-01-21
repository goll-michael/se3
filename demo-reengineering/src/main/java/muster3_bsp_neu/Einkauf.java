package muster3_bsp_neu;

public class Einkauf implements Preis {
	private double preis;

	public Einkauf(double preis) {
		this.preis = preis;
	}

	@Override
	public Preis naechsterStatus() {
		return new Selbstkosten(preis * 1.2);
	}

	@Override
	public void ausgebenPreis() {
		System.out.println("Der aktuelle Preis liegt bei " + preis + " Euro.");
	}
}
