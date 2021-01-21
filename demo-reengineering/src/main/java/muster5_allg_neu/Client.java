package muster5_allg_neu;

public class Client {
	public static void main(String[] args) {
		AbstraktesObjekt obj = new NullObjekt();

		// Objekt ist vom Typ NullObjekt, Methodenrumpf leer
		obj.ausfuehren();

		obj = new RealesObjekt();

		// Objekt ist vom Typ RealesObjekt, Methode wird ausgefuehrt
		obj.ausfuehren();
	}
}
