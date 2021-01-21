package main;

//Konkretes Kommando
class JalousieRunterKommando implements Command {
	private Jalousie meineJalousie;

	public JalousieRunterKommando(Jalousie meineJalousie) {
		this.meineJalousie = meineJalousie;
	}

	public void execute() {
		meineJalousie.herunterfahren();
	}
}

//Konkretes Kommando
class BeleuchtungAnKommando implements Command {
	private Beleuchtung meineBeleuchtung;

	public BeleuchtungAnKommando(Beleuchtung meineBeleuchtung) {
		this.meineBeleuchtung = meineBeleuchtung;
	}

	public void execute() {
		meineBeleuchtung.anschalten();
	}
}