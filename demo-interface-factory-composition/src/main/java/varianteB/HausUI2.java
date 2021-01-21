package varianteB;

public class HausUI2 {
	public static void main(String arg[]) {
		// Erzeugung
		Privathaus2 einHaus = new Privathaus2();

		// Nutzung
		einHaus.setBesitzer("Mueller");
		System.out.println(einHaus.getBesitzer());
		System.out.println(einHaus.gibPreis());

		// Erzeugung
		Geschaeftshaus2 nocheinHaus = new Geschaeftshaus2();

		// Nutzung
		nocheinHaus.setBesitzer("Meier");
		System.out.println(nocheinHaus.getBesitzer());
		System.out.println(nocheinHaus.gibPreis());
	}
}
