package muster5_allg_alt;

public class Client {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		RealesObjekt obj = null;

		// Objekt ist null, Methode wird nicht ausgefuehrt
		if (obj != null)
			obj.ausfuehren();

		obj = new RealesObjekt();

		// Objekt ist nicht null, Methode wird ausgefuehrt
		if (obj != null)
			obj.ausfuehren();
	}

}
