package main;

//Oberklasse
public abstract class Sparkasse {
	/*
	 * Die Festlegung, welche Portfolioklasse genutzt werden soll, wird in der
	 * Unterklasse durchgefuehrt.
	 */
	protected abstract Portfolio erzeugePortfolioObjekt();

	public void getPortfolio() {
		Portfolio pf = erzeugePortfolioObjekt();
		pf.druckePortfolio();
	}
}