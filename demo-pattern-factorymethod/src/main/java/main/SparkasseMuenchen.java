package main;

public class SparkasseMuenchen extends Sparkasse {
	protected Portfolio erzeugePortfolioObjekt() {
		return new PortfolioMuenchen();
	}
}