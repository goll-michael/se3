package main;

public class Preisberechnung {
	private MWSTFunktion callBackObjekt = null;

	private void registriere(MWSTFunktion callBackObj) {
		callBackObjekt = callBackObj;
	}

	public double berechneBrutto(double netto) {
		return callBackObjekt.berechneMWSTvoll(netto);
	}

	public static void main(String[] args) {
		Preisberechnung brutto = new Preisberechnung();
		// Objekt erzeugen
		MWSTFunktion mwstf = new MWSTImpl();

		// Verweis auf das Objekt
		// mit der gewünschten Funktion setzen
		brutto.registriere(mwstf);

		// Aufruf der gewünschten Funktion
		// des registrierten Objekts
		System.out.println(brutto.berechneBrutto(200.0));
	}
}