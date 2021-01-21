package main;

//Client
public class Konfigurator {
	public static void main(String[] args) {
		// Mit Kommando-Muster
		Beleuchtung meinLicht = new Beleuchtung();
		// meinLicht wird dem BeleuchtungAnKommando zugeordnet
		BeleuchtungAnKommando meinLichtAn = new BeleuchtungAnKommando(meinLicht);
		BeleuchtungAusKommando meinLichtAus = new BeleuchtungAusKommando(meinLicht);
		// Zuordnung von meinLicht zu der Fernbedienung
		Fernbedienung meineFernbedienungKnopf1 = new Fernbedienung(meinLichtAn, meinLichtAus);
		meineFernbedienungKnopf1.ein();
		meineFernbedienungKnopf1.aus();

		Jalousie meineJalousie = new Jalousie();
		JalousieHochKommando meineJalousieHoch = new JalousieHochKommando(meineJalousie);
		JalousieRunterKommando meineJalousieRunter = new JalousieRunterKommando(meineJalousie);
		Fernbedienung meineFernbedienungKnopf2 = new Fernbedienung(meineJalousieHoch, meineJalousieRunter);
		meineFernbedienungKnopf2.ein();
		meineFernbedienungKnopf2.aus();

		// Ohne Kommando-Muster
		// (die Schnittstelle pro Gerät muss bekannt sein)
		meinLicht.anschalten();
		meinLicht.ausschalten();
		meineJalousie.herunterfahren();
		meineJalousie.hochfahren();
	}
}