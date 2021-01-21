package main;

//Produkt eines Portfolios
public class Produkt {
	private String name;
	private String art;
	private double zinssatz;

	public Produkt(String name, String art, double zinssatz) {
		this.name = name;
		this.art = art;
		this.zinssatz = zinssatz;
	}

	public String getName() {
		return name;
	}

	public String getArt() {
		return art;
	}

	public double getZinssatz() {
		return zinssatz;
	}
}