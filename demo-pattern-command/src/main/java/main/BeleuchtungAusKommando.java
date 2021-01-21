package main;

//Konkretes Kommando
class BeleuchtungAusKommando implements Command {
	private Beleuchtung meineBeleuchtung;

	public BeleuchtungAusKommando(Beleuchtung meineBeleuchtung) {
		this.meineBeleuchtung = meineBeleuchtung;
	}

	public void execute() {
		meineBeleuchtung.ausschalten();
	}
}