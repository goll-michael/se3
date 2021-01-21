package main;

public class SparkasseDortmund extends Sparkasse {
	protected Portfolio erzeugePortfolioObjekt() {
		return new PortfolioDortmund();
	}
}