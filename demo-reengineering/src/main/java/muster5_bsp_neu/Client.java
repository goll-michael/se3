package muster5_bsp_neu;

public class Client {
	public static void main(String[] args) {
		AbstraktesDatum datum = new NullDatum();

		// Objekt ist vom Typ NullObjekt, Methodenrumpf leer
		datum.ausgeben();

		datum = new RealesDatum();

		// Objekt ist vom Typ RealesObjekt, Methode wird ausgefuehrt
		datum.ausgeben();
	}
}
