package main;

//Aufrufer des Kommandos (Invoker)
class Fernbedienung {
	private Command einKommando, ausKommando;

	public Fernbedienung(Command einKommando, Command ausKommando) {
		// Ein konkretes Kommando registriert sich
		// selbst beim Aufrufer
		this.einKommando = einKommando;
		this.ausKommando = ausKommando;
	}

	void ein() {
		// Der Aufrufer ruft das konkrete Kommando auf (call back),
		// das das Kommando beim Empfänger ausführt
		einKommando.execute();
	}

	void aus() {
		ausKommando.execute();
	}
}