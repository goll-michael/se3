package main;

public class PortfolioMuenchen extends Portfolio {
	@Override
	protected void erstellePortfolio() {
		einfuegeProdukt(new Produkt("M�nchen", "Kredit", 5.0));
		einfuegeProdukt(new Produkt("M�nchen", "Festgeld", 0.75));
	}
}