package main;

//Konkretes Kommando
class JalousieHochKommando implements Command {
	private Jalousie meineJalousie;

	public JalousieHochKommando(Jalousie meineJalousie) {
		this.meineJalousie = meineJalousie;
	}

	public void execute() {
		meineJalousie.hochfahren();
	}
}