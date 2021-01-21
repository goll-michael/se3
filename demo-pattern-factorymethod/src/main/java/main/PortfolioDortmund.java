package main;

public class PortfolioDortmund extends Portfolio {
	@Override
	protected void erstellePortfolio() {
		einfuegeProdukt(new Produkt("Dortmund", "Kredit > 50.000 Euro", 5.25));
		einfuegeProdukt(new Produkt("Dortmund", "Kredit <= 50.000 Euro", 6.0));
		einfuegeProdukt(new Produkt("Dortmund", "Festgeld", 0.5));
	}
}