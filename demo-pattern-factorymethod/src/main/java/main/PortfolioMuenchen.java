package main;

public class PortfolioMuenchen extends Portfolio {
	@Override
	protected void erstellePortfolio() {
		einfuegeProdukt(new Produkt("München", "Kredit", 5.0));
		einfuegeProdukt(new Produkt("München", "Festgeld", 0.75));
	}
}