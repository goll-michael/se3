package varianteA;

public class HausUI {
	public static void main(String arg[]) {
		// Erzeugung
		Immobilie einHaus = new Privathaus();

		// Nutzung
		einHaus.setBesitzer("Mueller");
		System.out.println(einHaus.getBesitzer());
		System.out.println(einHaus.gibPreis());

		// Erzeugung
		Immobilie nocheinHaus = new Geschaeftshaus();

		// Nutzung
		nocheinHaus.setBesitzer("Meier");
		System.out.println(nocheinHaus.getBesitzer());
		System.out.println(nocheinHaus.gibPreis());
	}
}
