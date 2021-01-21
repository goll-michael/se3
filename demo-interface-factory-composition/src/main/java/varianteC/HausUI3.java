package varianteC;

public class HausUI3 {
	public static void main(String arg[]) {
		ImmobilieCreate eineFabrik = new ImmobilieFactory();

		// Erzeugung
		ImmobilieIUse einHaus = eineFabrik.erzeugeImmobilie('P');

		// Nutzung
		einHaus.setBesitzer("Mueller");
		System.out.println(einHaus.getBesitzer());
		System.out.println(einHaus.gibPreis());

		// Erzeugung
		ImmobilieIUse nocheinHaus = eineFabrik.erzeugeImmobilie('G');

		// Nutzung
		nocheinHaus.setBesitzer("Meier");
		System.out.println(nocheinHaus.getBesitzer());
		System.out.println(nocheinHaus.gibPreis());
	}
}