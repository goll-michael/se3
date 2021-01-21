package muster1_bsp_alt;

public class Ausgabe {
	public static enum ART {
		TEXT, BILD
	};

	public void ausgeben(ART art) {
		switch (art) {
		case TEXT:
			System.out.println("SRH");
			break;
		case BILD:
			System.out.println("   ### #####   ##  ##");
			System.out.println(" ##    ##  ##  ##  ##");
			System.out.println("##     ##  ##  ##  ##");
			System.out.println("##     ## ##   ##  ##");
			System.out.println(" ##    ###     ######");
			System.out.println("   ##  ## #    ##  ##");
			System.out.println("   ##  ##  ##  ##  ##");
			System.out.println("  ##   ##  ##  ##  ##");
			System.out.println("###    ##  ##  ##  ##");
			break;
		default:
		}
	}
}
